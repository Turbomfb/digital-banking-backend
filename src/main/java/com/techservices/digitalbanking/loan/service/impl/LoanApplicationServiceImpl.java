/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.loan.service.impl;

import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.GenericApiResponse;
import com.techservices.digitalbanking.core.domain.dto.LoanDto;
import com.techservices.digitalbanking.core.eBanking.model.request.FilterDto;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.service.ExternalLoanService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.loan.domain.response.LoanDashboardResponse;
import com.techservices.digitalbanking.loan.domain.response.LoanOfferResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.eBanking.model.request.LoanRescheduleRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoanTemplateResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanRescheduleResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.LoanTransactionResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansLoanIdTransactionsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostLoansResponse;
import com.techservices.digitalbanking.core.eBanking.service.LoanService;
import com.techservices.digitalbanking.loan.domain.request.LoanApplicationRequest;
import com.techservices.digitalbanking.loan.domain.request.LoanRepaymentRequest;
import com.techservices.digitalbanking.loan.service.LoanApplicationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private final LoanService loanService;
	private final CustomerService customerService;
	private final ExternalLoanService externalLoanService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public PostLoansResponse calculateLoanScheduleOrSubmitLoanApplication(LoanApplicationRequest loanApplicationRequest,
			String command) {
		return loanService.calculateLoanScheduleOrSubmitLoanApplication(loanApplicationRequest, command);
	}

	@Override
	public GetLoansLoanIdResponse retrieveLoanById(Long loanId, Boolean staffInSelectedOfficeOnly, String associations,
												   String exclude, String fields, Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		GetLoansLoanIdResponse loanIdResponse = loanService.retrieveLoanById(loanId, staffInSelectedOfficeOnly, associations, exclude, fields);
		if (loanIdResponse != null && Objects.equals(loanIdResponse.getClientId(), customer.getExternalId())) {
			return loanIdResponse;
		}
		else {
			throw new ValidationException("validation.msg.loan.not.found",
					"Loan with ID " + loanId + " not found for customer with ID " + customerId);
		}
	}

	public BasePageResponse<LoanDto> retrieveAllLoans(Long limit, String accountNo, Long customerId) {
		String externalId = customerService.getCustomerById(customerId).getExternalId();
		FilterDto filterDto = new FilterDto().customerId(externalId).accountNumber(accountNo).size(limit);
		return BasePageResponse.instance(loanService.retrieveAllCustomerLoans(filterDto));
	}

	@Override
	public PostLoansLoanIdResponse processLoanCommand(Long loanId, PostLoansLoanIdRequest postLoansLoanIdRequest,
			@Valid String command) {
		return loanService.processLoanCommand(loanId, postLoansLoanIdRequest, command);
	}

	@Override
	public GenericApiResponse repayLoan(Long loanId, @Valid LoanRepaymentRequest loanRepaymentRequest,
										String command, Long customerId) {
		this.retrieveLoanById(loanId, null, null, null, null, customerId);
		if (StringUtils.isBlank(loanRepaymentRequest.getTransactionPin())){
			throw new ValidationException("validation.msg.loan.repayment.transaction.pin.required",
					"Transaction PIN is required for loan repayment");
		}
		Customer customer = customerService.getCustomerById(customerId);
		if (!passwordEncoder.matches(loanRepaymentRequest.getTransactionPin(), customer.getTransactionPin())){
			throw new ValidationException("validation.msg.loan.repayment.transaction.pin.invalid",
					"Invalid Transaction PIN provided for loan repayment");
		}
		PostLoansLoanIdTransactionsResponse response = loanService.repayLoan(loanId, loanRepaymentRequest, command);
		if (response != null && response.getResourceId() != null) {
			return new GenericApiResponse("success", "Loan repayment successful");
		}
		log.info("Loan repayment failed {}", response);
		return new GenericApiResponse("error", "Loan repayment failed");
	}

	@Override
	public GetLoanTemplateResponse retrieveLoanTemplate(@Valid String templateType, @Valid Long clientId,
			@Valid Long productId) {
		return loanService.retrieveLoanTemplate(templateType, clientId, productId);
	}

	@Override
	public FineractPageResponse<LoanTransactionResponse> retrieveLoanTransactions(Long loanId, Long customerId) {
		this.retrieveLoanById(loanId, null, null, null, null, customerId);
		return loanService.retrieveLoanTransactions(loanId);
	}


	@Override
	public LoanTransactionResponse retrieveLoanTransactionDetails(Long loanId, Long transactionId, Long customerId) {
		this.retrieveLoanById(loanId, null, null, null, null, customerId);
		return loanService.retrieveLoanTransactionDetails(loanId, transactionId);
	}

	@Override
	public BasePageResponse<LoanOfferResponse> retrieveCustomerLoanOffers(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<LoanOfferResponse> loanOffers;
        try {
            loanOffers = this.externalLoanService.retrieveCustomerLoanOffers(customer.getPhoneNumber());
        } catch (Exception e) {
            log.error("Error retrieving loan offers for customer {}: {}", customerId, e.getMessage());
            throw new ValidationException("validation.msg.loan.offers.retrieval.failed",
                    "Customer is not pre-qualified for loan offers.");
        }
        return BasePageResponse.instance(loanOffers);
    }

	@Override
	public GenericApiResponse processLoanApplication(Long customerId, LoanApplicationRequest loanApplicationRequest) {
		Customer customer = customerService.getCustomerById(customerId);
		loanApplicationRequest.setAcceptOffer("Y");
		loanApplicationRequest.setMsisdn(customer.getPhoneNumber());
		return this.externalLoanService.processLoanApplication(loanApplicationRequest);
	}

	@Override
	public LoanDashboardResponse retrieveCustomerLoanDashboard(Long customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		FilterDto filterDto = new FilterDto().customerId(customer.getExternalId());
		List<LoanDto> customerLoans = loanService.retrieveAllCustomerLoans(filterDto);

		return LoanDashboardResponse.builder()
				.activeLoanBalance(getActiveLoanBalance(customerLoans))
				.totalExpectedRepayment(getTotalExpectedRepayment(customerLoans))
				.totalRepaid(getTotalRepayment(customerLoans))
				.activeLoanCount(getActiveLoanCount(customerLoans))
				.pendingLoanCount(getPendingLoanCount(customerLoans))
				.liquidatedLoanCount(getLiquidatedLoanCount(customerLoans))
				.loans(customerLoans.stream()
						.filter(loan -> loan.getStatus() != null &&
								(loan.getStatus().equalsIgnoreCase("Active") ||
										loan.getStatus().equalsIgnoreCase("Pending") ||
										loan.getStatus().equalsIgnoreCase("Liquidated")))
						.toList()
				)
				.build();
	}

	public BigDecimal getActiveLoanBalance(List<LoanDto> customerLoans) {
		return customerLoans.stream()
				.filter(this::isActive)
				.map(loan -> Optional.ofNullable(loan.getOutstandingBalance()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalExpectedRepayment(List<LoanDto> customerLoans) {
		return customerLoans.stream()
				.filter(this::isActive)
				.map(loan -> Optional.ofNullable(loan.getTotalExpectedRepayment()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getTotalRepayment(List<LoanDto> customerLoans) {
		return customerLoans.stream()
				.filter(this::isActive)
				.map(loan -> Optional.ofNullable(loan.getTotalPaid()).orElse(BigDecimal.ZERO))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public Long getActiveLoanCount(List<LoanDto> customerLoans) {
		return customerLoans.stream()
				.filter(this::isActive)
				.count();
	}

	public Long getPendingLoanCount(List<LoanDto> customerLoans) {
		return customerLoans.stream()
				.filter(loan -> loan.getStatus() != null &&
						(loan.getStatus().equalsIgnoreCase("Pending") ||
								loan.getStatus().equalsIgnoreCase("WaitingForDisbursal")))
				.count();
	}

	public Long getLiquidatedLoanCount(List<LoanDto> customerLoans) {
		return customerLoans.stream()
				.filter(loan -> loan.getStatus() != null &&
						loan.getStatus().equalsIgnoreCase("Closed"))
				.count();
	}

	private boolean isActive(LoanDto loan) {
		return loan.getStatus() != null && loan.getStatus().equalsIgnoreCase("Active");
	}


	@Override
	public LoanRescheduleResponse createALoanRescheduleRequest(LoanRescheduleRequest loanRescheduleRequest) {
		return loanService.createALoanRescheduleRequest(loanRescheduleRequest);
	}

	@Override
	public PostLoansLoanIdResponse processLoanRescheduleCommand(PostLoansLoanIdRequest postLoansLoanIdRequest,
			String requestId, String command) {
		return loanService.processLoanRescheduleCommand(postLoansLoanIdRequest, requestId, command);
	}
}
