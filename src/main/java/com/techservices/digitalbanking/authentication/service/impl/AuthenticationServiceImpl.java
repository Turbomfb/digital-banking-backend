/* (C)2025 */
package com.techservices.digitalbanking.authentication.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.techservices.digitalbanking.customer.domian.dto.request.CreateCustomerRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.authentication.domain.data.model.UserLoginActivity;
import com.techservices.digitalbanking.authentication.domain.data.repository.UserLoginActivityRepository;
import com.techservices.digitalbanking.authentication.domain.request.AuthenticationRequest;
import com.techservices.digitalbanking.authentication.domain.request.PasswordMgtRequest;
import com.techservices.digitalbanking.authentication.domain.response.AuthenticationResponse;
import com.techservices.digitalbanking.authentication.service.AuthenticationService;
import com.techservices.digitalbanking.authentication.util.UserLoginActivityUtil;
import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.configuration.security.JwtUtil;
import com.techservices.digitalbanking.core.domain.data.model.AppUser;
import com.techservices.digitalbanking.core.domain.data.model.CustomerStatusAudit;
import com.techservices.digitalbanking.core.domain.data.repository.CustomerStatusAuditRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.enums.AlertType;
import com.techservices.digitalbanking.core.domain.enums.CustomerStatusAuditType;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.UnAuthenticatedUserException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.core.service.NotificationService;
import com.techservices.digitalbanking.core.util.NotificationUtil;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.domian.dto.request.CustomerTransactionPinRequest;
import com.techservices.digitalbanking.customer.domian.dto.response.CustomerDtoResponse;
import com.techservices.digitalbanking.customer.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.CHANGE_PASSWORD_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.DateUtil.getFormattedCurrentDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final long DEFAULT_INVITATION_TOKEN_EXPIRATION_MS = 3600000; // 1 hour

	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final CustomerService customerService;
	private final RedisService redisService;
	private final UserLoginActivityRepository userLoginActivityRepository;
	private final CustomerRepository customerRepository;
	private final NotificationService notificationService;
	private final NotificationUtil notificationUtil;
	private final CustomerStatusAuditRepository customerStatusAuditRepository;

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest postAuthenticationRequest, UserType customerType,
			String userAgent, HttpServletRequest request) {

		Map<String, Object> claims = new HashMap<>();
		Customer foundCustomer = customerService.getCustomerByEmailOrPhoneNumber(
				postAuthenticationRequest.getEmailAddress(), postAuthenticationRequest.getPhoneNumber(), customerType);

		assert foundCustomer != null;
		log.info("Found customer with email address {}", foundCustomer.getEmailAddress());
		if (!passwordEncoder.matches(postAuthenticationRequest.getPassword(), foundCustomer.getPassword())) {
			throw new UnAuthenticatedUserException("Invalid.credentials.provided", "Invalid username or password");
		}

		Optional<CustomerStatusAudit> customerStatusAudit = customerStatusAuditRepository
				.findByCustomerIdAndTypeAndIsActive(foundCustomer.getId(), CustomerStatusAuditType.ACCOUNT_CLOSURE,
						true);
		if (customerStatusAudit.isPresent()) {
			throw new UnAuthenticatedUserException("account.is.closed",
					"Your account has been closed and access is no longer available. please contact our support team.");
		}

		claims.put("customerId", foundCustomer.getId());
		claims.put("email", foundCustomer.getEmailAddress());
		claims.put("userType", foundCustomer.getUserType());
		claims.put("isActive", foundCustomer.isActive());

		String accessToken = generateToken(foundCustomer.getEmailAddress(), claims);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		authenticationResponse.setAccessToken(accessToken);
		authenticationResponse.setId(foundCustomer.getId());
		authenticationResponse.setFirstname(foundCustomer.getFirstname());
		authenticationResponse.setLastname(foundCustomer.getLastname());
		authenticationResponse.setEmailAddress(foundCustomer.getEmailAddress());
		authenticationResponse.setUserType(foundCustomer.getUserType());

		AppUser appUser = new AppUser(foundCustomer.getId(), foundCustomer.getEmailAddress(), accessToken, true,
				foundCustomer.isActive(), foundCustomer.getUserType(), null);
		SecurityContextHolder.getContext().setAuthentication(appUser);
		foundCustomer.setAuthenticated(true);

		try {
			this.processUserLoginActivity(userAgent, request, foundCustomer, authenticationResponse);
			String loginMessage = notificationUtil.getLoginNotificationTemplate(
					foundCustomer.getFirstname() + " " + foundCustomer.getLastname(), getFormattedCurrentDateTime());
			notificationService.notifyUser(foundCustomer, loginMessage, AlertType.LOGIN);
		} catch (Exception e) {
			log.error("Error processing user login activity: {}", e.getMessage());
		}
		customerRepository.save(foundCustomer);
		return authenticationResponse;
	}

	public boolean isTokenValid(String token) {

		return jwtUtil.isTokenValid(token);
	}

	private void processUserLoginActivity(String userAgent, HttpServletRequest request, Customer foundCustomer,
			AuthenticationResponse authenticationResponse) {

		log.info("userAgent: {}", userAgent);
		log.info("Processing headers: sec-ch-ua={}, sec-ch-ua-mobile={}, sec-ch-ua-platform={}",
				request.getHeader("sec-ch-ua"), request.getHeader("sec-ch-ua-mobile"),
				request.getHeader("sec-ch-ua-platform"));

		String deviceName = UserLoginActivityUtil.extractDeviceNameFromHeaders(request, userAgent);
		String source = UserLoginActivityUtil.extractSourceFromHeaders(request, userAgent);

		String ip = UserLoginActivityUtil.extractClientIp(request);
		String location = UserLoginActivityUtil.getLocationFromRequest(ip, request);

		UserLoginActivity activity;
		Optional<UserLoginActivity> foundActivity = userLoginActivityRepository
				.findByCustomerIdAndDeviceNameAndSource(foundCustomer.getId(), deviceName, source);

		if (foundActivity.isPresent()) {
			activity = foundActivity.get();
			activity.setUpdatedAt(LocalDateTime.now());
		} else {
			activity = new UserLoginActivity();
			activity.setCustomerId(foundCustomer.getId());
			activity.setDeviceName(deviceName);
			activity.setSource(source);
			activity.setCreatedAt(LocalDateTime.now());
			activity.setUpdatedAt(LocalDateTime.now());
		}

		activity.setLocation(location);

		log.info("user activity: {}", activity);
		userLoginActivityRepository.save(activity);
	}

	@Override
	public GenericApiResponse createPassword(PasswordMgtRequest passwordMgtRequest) {
			if (StringUtils.isNotBlank(passwordMgtRequest.getPassword())) {
        CreateCustomerRequest createCustomerRequest = redisService.retrieveData(passwordMgtRequest.getCustomerId(), CreateCustomerRequest.class);
        if (createCustomerRequest != null && createCustomerRequest.isOtpValidated()) {
          createCustomerRequest.setPassword(passwordMgtRequest.getPassword());
          CustomerDtoResponse savedCustomer = customerService.completeCustomerRegistration(createCustomerRequest);
          redisService.delete(passwordMgtRequest.getCustomerId());
          return new GenericApiResponse("Password created successfully", "success", savedCustomer);
        } else {
          throw new ValidationException("password.creation.failed", "Kindly reinitiate the Otp process");
        }
			} else {
				throw new ValidationException("Invalid.credentials.provided", "password cannot be blank");
			}
	}

	@Override
	public GenericApiResponse forgotPassword(PasswordMgtRequest passwordMgtRequest, String command,
			UserType customerType) {

		if (StringUtils.equals(GENERATE_OTP_COMMAND, command)) {
			Customer foundCustomer = customerService.getCustomerByEmailOrPhoneNumber(
					passwordMgtRequest.getEmailAddress(), passwordMgtRequest.getPhoneNumber(), customerType);
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(
					passwordMgtRequest.getPhoneNumber(), passwordMgtRequest.getEmailAddress());
			passwordMgtRequest.setCustomerId(String.valueOf(foundCustomer.getId()));
			OtpDto otpDto = this.redisService.generateOtpRequest(passwordMgtRequest, OtpType.FORGOT_PASSWORD,
					notificationRequestDto, null);
			return new GenericApiResponse(otpDto.getUniqueId(), passwordMgtRequest.getPhoneNumber(),
					passwordMgtRequest.getEmailAddress(), true);
		} else if (StringUtils.equals(VERIFY_OTP_COMMAND, command)) {
			if (StringUtils.isBlank(passwordMgtRequest.getOtp())) {
				throw new ValidationException("Invalid.data.provided", "otp must be provided");
			}
			if (StringUtils.isBlank(passwordMgtRequest.getUniqueId())) {
				throw new ValidationException("Invalid.data.provided", "uniqueId must be provided");
			}

			OtpDto otpDto = redisService.validateOtpWithoutDeletingRecord(passwordMgtRequest.getUniqueId(),
					passwordMgtRequest.getOtp(), OtpType.FORGOT_PASSWORD);
			if (otpDto == null) {
				throw new ValidationException("otp.expired", "OTP has expired or does not exist.");
			}
			return new GenericApiResponse("OTP validated successfully. Kindly proceed to change password", "success",
					null);
		} else if (StringUtils.equals(CHANGE_PASSWORD_COMMAND, command)) {
			if (StringUtils.isBlank(passwordMgtRequest.getUniqueId())) {
				throw new ValidationException("Invalid.data.provided", "uniqueId must be provided");
			}
			if (StringUtils.isBlank(passwordMgtRequest.getPassword())) {
				throw new ValidationException("Invalid.data.provided", "password must be provided");
			}
			OtpDto otpDto = redisService.validateOtpWithoutOtp(passwordMgtRequest.getUniqueId());
			String password = passwordEncoder.encode(passwordMgtRequest.getPassword());
			PasswordMgtRequest passwordRequest = (PasswordMgtRequest) otpDto.getData();
			Customer foundCustomer = customerService.getCustomerById(
          Long.valueOf(passwordRequest.getCustomerId()));
			foundCustomer.setPassword(password);
			customerService.updateCustomer(null, foundCustomer.getId(), foundCustomer, true);
			return new GenericApiResponse("Password has been changed successfully", "success", null);
		} else {
			throw new ValidationException("Invalid.command", "Invalid command: " + command);
		}
	}

	@Override
	public BasePageResponse<UserLoginActivity> retrieveUserLoginActivities(Long customerId) {

		return BasePageResponse.instance(this.userLoginActivityRepository.findAllByCustomerId(customerId));
	}

	@Override
	public GenericApiResponse changePassword(PasswordMgtRequest passwordMgtRequest) {

		Customer foundCustomer = customerService.getCustomerById(
        Long.valueOf(passwordMgtRequest.getCustomerId()));
		if (passwordEncoder.matches(passwordMgtRequest.getPassword(), foundCustomer.getPassword())) {
			if (StringUtils.isBlank(passwordMgtRequest.getNewPassword())) {
				throw new ValidationException("Invalid.data.provided", "new password cannot be blank");
			}
			foundCustomer.setPassword(passwordEncoder.encode(passwordMgtRequest.getNewPassword()));
			customerRepository.save(foundCustomer);
			return new GenericApiResponse("Password changed successfully", "success", null);
		}
		throw new ValidationException("incorrect.password", "Incorrect password provided. Please try again.");
	}

	@Override
	public GenericApiResponse changeTransactionPin(CustomerTransactionPinRequest pinRequest) {

		Customer foundCustomer = customerService.getCustomerById(pinRequest.getCustomerId());
		if (passwordEncoder.matches(pinRequest.getPin(), foundCustomer.getTransactionPin())) {
			if (StringUtils.isBlank(pinRequest.getNewPin())) {
				throw new ValidationException("Invalid.data.provided", "new pin cannot be blank");
			}
			foundCustomer.setTransactionPin(passwordEncoder.encode(pinRequest.getNewPin()));
			customerRepository.save(foundCustomer);
			return new GenericApiResponse("Transaction pin changed successfully", "success", null);
		}
		throw new ValidationException("incorrect.pin", "Incorrect transaction pin provided. Please try again.");
	}

	@Override
	public void logout(Long customerId) {

		Customer foundCustomer = customerService.getCustomerById(customerId);
		foundCustomer.setAuthenticated(false);
		customerRepository.save(foundCustomer);
	}

	private String generateToken(String username, Map<String, Object> claims) {

		return jwtUtil.generateToken(username, DEFAULT_INVITATION_TOKEN_EXPIRATION_MS, claims);
	}
}
