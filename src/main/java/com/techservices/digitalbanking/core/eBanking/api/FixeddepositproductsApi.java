/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techservices.digitalbanking.core.eBanking.model.request.PostFixedDepositProductsRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.DeleteFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositProductsProductIdResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.GetFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostFixedDepositProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutFixedDepositProductsProductIdRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.PutFixedDepositProductsProductIdResponse;

import jakarta.validation.Valid;

public interface FixeddepositproductsApi {

	/**
	 * POST /fixeddepositproducts : Create a Fixed Deposit Product Creates a Fixed
	 * Deposit Product Mandatory Fields: name, shortName, description, currencyCode,
	 * digitsAfterDecimal,inMultiplesOf, interestCompoundingPeriodType,
	 * interestCalculationType, interestCalculationDaysInYearType, minDepositTerm,
	 * minDepositTermTypeId, accountingRule Optional Fields: lockinPeriodFrequency,
	 * lockinPeriodFrequencyType, maxDepositTerm, maxDepositTermTypeId,
	 * inMultiplesOfDepositTerm, inMultiplesOfDepositTermTypeId,
	 * preClosurePenalApplicable, preClosurePenalInterest,
	 * preClosurePenalInterestOnTypeId, feeToIncomeAccountMappings,
	 * penaltyToIncomeAccountMappings, charges, charts, , withHoldTax, taxGroupId
	 * Mandatory Fields for Cash based accounting (accountingRule &#x3D; 2):
	 * savingsReferenceAccountId, savingsControlAccountId,
	 * interestOnSavingsAccountId, incomeFromFeeAccountId,
	 * transfersInSuspenseAccountId, incomeFromPenaltyAccountId
	 *
	 * @param postFixedDepositProductsRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PostMapping(value = "/fixeddepositproducts", produces = {"application/json"}, consumes = "application/json")
	PostFixedDepositProductsResponse createProduct(
			@Valid @RequestBody PostFixedDepositProductsRequest postFixedDepositProductsRequest);

	/**
	 * DELETE /fixeddepositproducts/{productId} : Delete a Fixed Deposit Product
	 * Deletes a Fixed Deposit Product
	 *
	 * @param productId
	 *            productId (required)
	 * @return OK (status code 200)
	 */
	@DeleteMapping(value = "/fixeddepositproducts/{productId}", produces = {"application/json"})
	DeleteFixedDepositProductsProductIdResponse delete(@PathVariable("productId") Long productId);

	/**
	 * GET /fixeddepositproducts : List Fixed Deposit Products Lists Fixed Deposit
	 * Products Example Requests: fixeddepositproducts
	 * fixeddepositproducts?fields&#x3D;name
	 *
	 * @return OK (status code 200)
	 */
	@GetMapping(value = "/fixeddepositproducts", produces = {"application/json"})
	List<GetFixedDepositProductsResponse> retrieveAll39();

	/**
	 * GET /fixeddepositproducts/{productId} : Retrieve a Fixed Deposit Product
	 * Retrieves a Fixed Deposit Product Example Requests: fixeddepositproducts/1
	 * fixeddepositproducts/1?template&#x3D;true
	 * fixeddepositproducts/1?fields&#x3D;name,description
	 *
	 * @param productId
	 *            productId (required)
	 * @return OK (status code 200)
	 */
	@GetMapping("/fixeddepositproducts/{productId}")
	GetFixedDepositProductsProductIdResponse retrieveOne(@PathVariable("productId") Long productId);

	/**
	 * GET /fixeddepositproducts/template
	 *
	 * @return default response (status code 200)
	 */
	@GetMapping(value = "/fixeddepositproducts/template", produces = {"application/json"})
	Object retrieveTemplate();

	/**
	 * PUT /fixeddepositproducts/{productId} : Update a Fixed Deposit Product
	 * Updates a Fixed Deposit Product
	 *
	 * @param productId
	 *            productId (required)
	 * @param putFixedDepositProductsProductIdRequest
	 *            (required)
	 * @return OK (status code 200)
	 */
	@PutMapping(value = "/fixeddepositproducts/{productId}", produces = {
			"application/json"}, consumes = "application/json")
	PutFixedDepositProductsProductIdResponse updateProduct(@PathVariable("productId") Long productId,
			@Valid @RequestBody PutFixedDepositProductsProductIdRequest putFixedDepositProductsProductIdRequest);
}
