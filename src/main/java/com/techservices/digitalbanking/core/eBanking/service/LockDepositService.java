/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.service;

import com.techservices.digitalbanking.core.domain.dto.ProductDto;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import org.springframework.stereotype.Service;

import com.techservices.digitalbanking.core.eBanking.api.LockDepositAccountsApiClient;
import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositAccountsAccountIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositAccountsRequest;
import com.techservices.digitalbanking.investment.domain.request.FixedDepositApplicationRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;

import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_DATE_FORMAT;
import static com.techservices.digitalbanking.core.util.DateUtil.DEFAULT_LOCALE;
import static com.techservices.digitalbanking.core.util.DateUtil.getCurrentDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LockDepositService {
	private final LockDepositAccountsApiClient lockDepositAccountsApiClient;

	public PostSavingsAccountsResponse submitApplication(FixedDepositApplicationRequest fixedDepositApplicationRequest,
			@Valid boolean activate) {
		GetFixedDepositProductsProductIdResponse product = lockDepositAccountsApiClient.retrieveAProduct(fixedDepositApplicationRequest.getProductId());
		PostFixedDepositAccountsRequest postFixedDepositAccountsRequest = new PostFixedDepositAccountsRequest();
		postFixedDepositAccountsRequest.setDepositAmount(fixedDepositApplicationRequest.getDepositAmount());
		postFixedDepositAccountsRequest.setDepositPeriod(fixedDepositApplicationRequest.getDepositPeriod());
		postFixedDepositAccountsRequest.setDepositPeriodFrequencyId(product.getMinDepositTermType().getId());
		postFixedDepositAccountsRequest.setClientId(fixedDepositApplicationRequest.getClientId());
		postFixedDepositAccountsRequest.setProductId(fixedDepositApplicationRequest.getProductId());
		postFixedDepositAccountsRequest.setLocale(DEFAULT_LOCALE);
		postFixedDepositAccountsRequest.setDateFormat(DEFAULT_DATE_FORMAT);
		postFixedDepositAccountsRequest.setSubmittedOnDate(getCurrentDate());
		postFixedDepositAccountsRequest.setAccountNo(fixedDepositApplicationRequest.getAccountNo());
		postFixedDepositAccountsRequest.setAllocationName(fixedDepositApplicationRequest.getAllocationName());
		postFixedDepositAccountsRequest.setLinkAccountId(fixedDepositApplicationRequest.getLinkAccountId());
		PostSavingsAccountsResponse postSavingsAccountsResponse = lockDepositAccountsApiClient
				.submitApplication(postFixedDepositAccountsRequest);
		if (activate) {
			processInvestmentApproval(new PostFixedDepositAccountsAccountIdRequest());
			processInvestmentActivation(new PostFixedDepositAccountsAccountIdRequest());
		}
		return postSavingsAccountsResponse;
	}

	public ProductDto retrieveALockProduct() {
		return lockDepositAccountsApiClient.retrieveALockProduct();
	}

	private void processInvestmentActivation(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setActivatedOnDate(getCurrentDate());
	}

	private void processInvestmentRejection(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setRejectedOnDate(getCurrentDate());
	}

	public void processInvestmentApproval(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		populateDefaultFields(postFixedDepositAccountsAccountIdRequest);
		postFixedDepositAccountsAccountIdRequest.setApprovedOnDate(getCurrentDate());
	}

	private static void populateDefaultFields(
			PostFixedDepositAccountsAccountIdRequest postFixedDepositAccountsAccountIdRequest) {
		postFixedDepositAccountsAccountIdRequest.setLocale(DEFAULT_LOCALE);
		postFixedDepositAccountsAccountIdRequest.setDateFormat(DEFAULT_DATE_FORMAT);
	}

	public GetFixedDepositAccountsResponse retrieveAllInvestments(Boolean paged, Integer offset, Integer limit,
			String orderBy, String sortOrder) {
		List<GetFixedDepositAccountsAccountIdResponse> fixedDepositAccountsAccountIdResponseList = lockDepositAccountsApiClient.retrieveAll38(paged, offset, limit, orderBy, sortOrder);
		GetFixedDepositAccountsResponse getFixedDepositAccountsResponse = new GetFixedDepositAccountsResponse();
		getFixedDepositAccountsResponse.setPageItems(new HashSet<>(fixedDepositAccountsAccountIdResponseList));
		getFixedDepositAccountsResponse.setTotalFilteredRecords(fixedDepositAccountsAccountIdResponseList.size());
		return getFixedDepositAccountsResponse;
	}

	public GetFixedDepositAccountsAccountIdResponse retrieveInvestmentById(String id) {
		return lockDepositAccountsApiClient.retrieveOne(id);
	}

	public Object retrieveTemplate(Long clientId, Long productId) {
		return lockDepositAccountsApiClient.retrieveAccountTemplate(clientId, null, productId, null);
	}

	public GetFixedDepositTransactionResponse retrieveInvestmentTransactionById(Long investmentId, Long transactionId) {
		return lockDepositAccountsApiClient.retrieveOneTransaction(investmentId, transactionId);
	}

	public List<GetFixedDepositTransactionResponse> retrieveAllInvestmentTransactions(String investmentId) {
		GetFixedDepositAccountsAccountIdResponse fixedDepositAccount = lockDepositAccountsApiClient.retrieveOne(investmentId);
		return fixedDepositAccount.getTransactions();
	}

}
