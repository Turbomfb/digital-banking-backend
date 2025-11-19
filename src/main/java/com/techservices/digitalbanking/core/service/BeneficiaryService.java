/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techservices.digitalbanking.core.domain.data.model.Beneficiary;
import com.techservices.digitalbanking.core.domain.data.repository.BeneficiaryRepository;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.request.AddBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.request.DeleteBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.request.NotificationRequestDto;
import com.techservices.digitalbanking.core.domain.dto.request.OtpDto;
import com.techservices.digitalbanking.core.domain.dto.request.UpdateBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryListResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryResponse;
import com.techservices.digitalbanking.core.domain.enums.OtpType;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.redis.service.RedisService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import com.techservices.digitalbanking.customer.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.CommandUtil.GENERATE_OTP_COMMAND;
import static com.techservices.digitalbanking.core.util.CommandUtil.VERIFY_OTP_COMMAND;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeneficiaryService {

	private final BeneficiaryRepository beneficiaryRepository;
	private final RedisService redisService;
	private final CustomerService customerService;
	private final CustomerRepository customerRepository;

	@Transactional
	public BeneficiaryResponse addBeneficiary(AddBeneficiaryRequest request, Long customerId) {

		log.info("Adding beneficiary for customer: {}, accountNumber: {}, bankCode: {}", customerId,
				request.getAccountNumber(), request.getBankCode());

		Beneficiary beneficiary = new Beneficiary();
		beneficiary.setCustomerId(customerId);
		beneficiary.setAccountName(request.getAccountName());
		beneficiary.setAccountNumber(request.getAccountNumber());
		beneficiary.setBankName(request.getBankName());
		beneficiary.setBankCode(request.getBankCode());
		beneficiary.setNickname(request.getNickname());
		beneficiary.setIsVerified(true);
		beneficiary.setIsActive(true);
		beneficiary.setCreatedAt(LocalDateTime.now());

		Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
		log.info("Beneficiary added successfully: {}", savedBeneficiary.getId());

		return BeneficiaryResponse.from(savedBeneficiary);
	}

	private Customer validateAddBeneficiary(AddBeneficiaryRequest request, Long customerId) {

		request.validate();

		Customer customer = customerService.getCustomerById(customerId);

		if (beneficiaryRepository.existsByCustomerIdAndAccountNumberAndBankCode(customerId, request.getAccountNumber(),
				request.getBankCode())) {
			throw new ValidationException("beneficiary.already.exists", "This beneficiary already exists in your list");
		}

		if (StringUtils.isNotBlank(request.getNickname())
				&& beneficiaryRepository.existsByCustomerIdAndNickname(customerId, request.getNickname())) {
			throw new ValidationException("nickname.already.exists",
					"This nickname is already used for another beneficiary");
		}
		return customer;
	}

	@Transactional(readOnly = true)
	public BeneficiaryListResponse getAllBeneficiaries(Long customerId) {

		log.info("Fetching all beneficiaries for customer: {}", customerId);

		if (!customerRepository.existsById(customerId)) {
			throw new ValidationException("customer.not.found", "Customer not found");
		}

		List<Beneficiary> beneficiaries = beneficiaryRepository
				.findByCustomerIdAndIsActiveTrueOrderByLastUsedAtDesc(customerId);

		List<BeneficiaryResponse> beneficiaryResponses = beneficiaries.stream().map(BeneficiaryResponse::from)
				.collect(Collectors.toList());

		long activeCount = beneficiaryRepository.countActiveByCustomerId(customerId);

		log.info("Found {} active beneficiaries for customer: {}", beneficiaryResponses.size(), customerId);

		return new BeneficiaryListResponse(beneficiaryResponses, beneficiaryResponses.size(), (int) activeCount);
	}

	@Transactional(readOnly = true)
	public BeneficiaryListResponse searchBeneficiaries(Long customerId, String searchTerm) {

		log.info("Searching beneficiaries for customer: {}, searchTerm: {}", customerId, searchTerm);

		if (!customerRepository.existsById(customerId)) {
			throw new ValidationException("customer.not.found", "Customer not found");
		}

		if (StringUtils.isBlank(searchTerm)) {
			return getAllBeneficiaries(customerId);
		}

		List<Beneficiary> beneficiaries = beneficiaryRepository.searchBeneficiaries(customerId, searchTerm);

		List<BeneficiaryResponse> beneficiaryResponses = beneficiaries.stream().map(BeneficiaryResponse::from)
				.collect(Collectors.toList());

		log.info("Found {} beneficiaries matching search term for customer: {}", beneficiaryResponses.size(),
				customerId);

		return new BeneficiaryListResponse(beneficiaryResponses, beneficiaryResponses.size(),
				beneficiaryResponses.stream().filter(BeneficiaryResponse::getIsActive).count());
	}

	@Transactional(readOnly = true)
	public BeneficiaryListResponse getFrequentBeneficiaries(Long customerId) {

		log.info("Fetching frequent beneficiaries for customer: {}", customerId);

		if (!customerRepository.existsById(customerId)) {
			throw new ValidationException("customer.not.found", "Customer not found");
		}

		List<Beneficiary> beneficiaries = beneficiaryRepository.findFrequentBeneficiaries(customerId);

		List<BeneficiaryResponse> beneficiaryResponses = beneficiaries.stream().limit(10).map(BeneficiaryResponse::from)
				.collect(Collectors.toList());

		log.info("Found {} frequent beneficiaries for customer: {}", beneficiaryResponses.size(), customerId);

		return new BeneficiaryListResponse(beneficiaryResponses, beneficiaryResponses.size(),
				beneficiaryResponses.size());
	}

	@Transactional(readOnly = true)
	public BeneficiaryResponse getBeneficiaryById(Long beneficiaryId, Long customerId) {

		log.info("Fetching beneficiary: {} for customer: {}", beneficiaryId, customerId);

		Beneficiary beneficiary = findBeneficiaryById(beneficiaryId, customerId);

		return BeneficiaryResponse.from(beneficiary);
	}

	@Transactional
	public BeneficiaryResponse updateBeneficiary(Long beneficiaryId, UpdateBeneficiaryRequest request,
			Long customerId) {

		log.info("Updating beneficiary: {} for customer: {}", beneficiaryId, customerId);

		request.validateGenerate();

		Beneficiary beneficiary = findBeneficiaryById(beneficiaryId, customerId);
		if (request.getNickname() != null) {
			validateNickname(request, customerId, beneficiaryId);
			beneficiary.setNickname(request.getNickname());
		}

		beneficiary.setLastModifiedAt(LocalDateTime.now());
		Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);

		log.info("Beneficiary updated successfully: {}", beneficiaryId);

		return BeneficiaryResponse.from(updatedBeneficiary);
	}

	@Transactional
	public GenericApiResponse updateBeneficiary(UpdateBeneficiaryRequest request, Long customerId, String command) {

		Long beneficiaryId = request.getBeneficiaryId();
		log.info("Updating beneficiary: {} for customer: {}", beneficiaryId, customerId);
		if (GENERATE_OTP_COMMAND.equals(command)) {
			Customer customer = customerService.getCustomerById(customerId);

			request.validateGenerate();

			findBeneficiaryById(beneficiaryId, customerId);

			if (request.getNickname() != null) {
				validateNickname(request, customerId, beneficiaryId);
			}
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(customer.getPhoneNumber(),
					customer.getEmailAddress());
			OtpDto otpDto = this.redisService.generateOtpRequest(request, OtpType.DELETE_BENEFICIARY,
					notificationRequestDto, null);
      return new GenericApiResponse(otpDto.getUniqueId(), customer.getPhoneNumber(), customer.getEmailAddress(), true);
		} else if (VERIFY_OTP_COMMAND.equals(command)) {
			request.validateVerification();
			String uniqueId = request.getUniqueId();
			String otp = request.getOtp();
			UpdateBeneficiaryRequest savedRequest = (UpdateBeneficiaryRequest) this.redisService
					.validateOtpWithoutDeletingRecord(uniqueId, otp, OtpType.UPDATE_BENEFICIARY).getData();
			Long savedBeneficiaryId = savedRequest.getBeneficiaryId();
			this.updateBeneficiary(savedBeneficiaryId, savedRequest, customerId);
			return new GenericApiResponse("Beneficiary has been updated successfully", "success");
		} else {
			throw new ValidationException("Invalid command");
		}
	}

	private void validateNickname(UpdateBeneficiaryRequest request, Long customerId, Long beneficiaryId) {

		if (beneficiaryRepository.existsByCustomerIdAndNickname(customerId, request.getNickname())) {
			Optional<Beneficiary> existing = beneficiaryRepository.findByCustomerIdAndNickname(customerId,
					request.getNickname());
			if (existing.isPresent() && !existing.get().getId().equals(beneficiaryId)) {
				throw new ValidationException("nickname.already.exists",
						"This nickname is already used for another beneficiary");
			}
		}
	}

	@Transactional
	public GenericApiResponse deleteBeneficiary(Long customerId, @Valid String command,
			DeleteBeneficiaryRequest deleteBeneficiaryRequest) {

		if (GENERATE_OTP_COMMAND.equals(command)) {
      Long beneficiaryId = deleteBeneficiaryRequest.getBeneficiaryId();
      log.info("Deleting beneficiary: {} for customer: {}", beneficiaryId, customerId);
      findBeneficiaryById(beneficiaryId, customerId);
      deleteBeneficiaryRequest.validateGenerate();
			Customer customer = customerService.getCustomerById(customerId);
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(customer.getPhoneNumber(),
					customer.getEmailAddress());
			OtpDto otpDto = this.redisService.generateOtpRequest(deleteBeneficiaryRequest, OtpType.DELETE_BENEFICIARY,
					notificationRequestDto, null);
      return new GenericApiResponse(otpDto.getUniqueId(), customer.getPhoneNumber(), customer.getEmailAddress(), true);
		} else if (VERIFY_OTP_COMMAND.equals(command)) {
			deleteBeneficiaryRequest.validateVerification();
			String uniqueId = deleteBeneficiaryRequest.getUniqueId();
			String otp = deleteBeneficiaryRequest.getOtp();
			DeleteBeneficiaryRequest savedRequest = (DeleteBeneficiaryRequest) this.redisService
					.validateOtpWithoutDeletingRecord(uniqueId, otp, OtpType.DELETE_BENEFICIARY).getData();
			Long savedBeneficiaryId = savedRequest.getBeneficiaryId();
			this.hardDeleteBeneficiary(savedBeneficiaryId, customerId);
			return new GenericApiResponse("Beneficiary has been deleted successfully", "success");
		} else {
			throw new ValidationException("Invalid command");
		}
	}

	private Beneficiary findBeneficiaryById(Long beneficiaryId, Long customerId) {

		return beneficiaryRepository.findByIdAndCustomerId(beneficiaryId, customerId)
				.orElseThrow(() -> new ValidationException("beneficiary.not.found", "Beneficiary not found"));
	}

	@Transactional
	public void hardDeleteBeneficiary(Long beneficiaryId, Long customerId) {

		log.info("Hard deleting beneficiary: {} for customer: {}", beneficiaryId, customerId);

		Beneficiary beneficiary = findBeneficiaryById(beneficiaryId, customerId);

		beneficiaryRepository.delete(beneficiary);

		log.info("Beneficiary hard deleted successfully: {}", beneficiaryId);
	}

	@Transactional
	public void markBeneficiaryAsUsed(String accountNumber, String bankCode, Long customerId) {

		log.info("Marking beneficiary as used - accountNumber: {}, bankCode: {}, customer: {}", accountNumber, bankCode,
				customerId);

		beneficiaryRepository.findByCustomerIdAndAccountNumberAndBankCode(customerId, accountNumber, bankCode)
				.ifPresent(beneficiary -> {
					beneficiary.incrementUsageCount();
					beneficiaryRepository.save(beneficiary);
					log.info("Beneficiary usage count updated: {}", beneficiary.getId());
				});
	}

	public void markBeneficiaryAsUsed(String accountNumber, String bankNipCode, String accountName, String bankName,
			boolean addToBeneficiary, Long customerId) {

		if (addToBeneficiary) {
			this.addBeneficiary(accountNumber, bankNipCode, accountName, bankName, customerId, null);
		}
		this.markBeneficiaryAsUsed(accountNumber, bankName, customerId);
	}

	private void addBeneficiary(String accountNumber, String bankNipCode, String accountName, String bankName,
			Long customerId, String nickname) {

		AddBeneficiaryRequest request = new AddBeneficiaryRequest();
		request.setAccountNumber(accountNumber);
		request.setAccountName(accountName);
		request.setBankName(bankName);
		request.setBankCode(bankNipCode);
		request.setNickname(nickname);
		validateAddBeneficiary(request, customerId);
		this.addBeneficiary(request, customerId);
	}

	public GenericApiResponse addBeneficiary(AddBeneficiaryRequest request, Long customerId, @Valid String command) {

		if (GENERATE_OTP_COMMAND.equals(command)) {
			Customer customer = validateAddBeneficiary(request, customerId);
      request.validate();
			NotificationRequestDto notificationRequestDto = new NotificationRequestDto(customer.getPhoneNumber(),
					customer.getEmailAddress());
			OtpDto otpDto = this.redisService.generateOtpRequest(request, OtpType.ADD_BENEFICIARY,
					notificationRequestDto, null);
			return new GenericApiResponse(otpDto.getUniqueId(), customer.getPhoneNumber(), customer.getEmailAddress(), true);
		} else if (VERIFY_OTP_COMMAND.equals(command)) {
			AddBeneficiaryRequest beneficiaryRequest = (AddBeneficiaryRequest) this.redisService
					.validateOtpWithoutDeletingRecord(request.getUniqueId(), request.getOtp(), OtpType.ADD_BENEFICIARY)
					.getData();
			this.addBeneficiary(beneficiaryRequest.getAccountNumber(), beneficiaryRequest.getBankCode(),
					beneficiaryRequest.getAccountName(), beneficiaryRequest.getBankName(), customerId,
					beneficiaryRequest.getNickname());
			return new GenericApiResponse("Beneficiary has been added successfully", "success");
		} else {
			throw new ValidationException("Invalid command");
		}
	}
}
