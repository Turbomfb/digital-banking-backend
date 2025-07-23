package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetLoanProductsProductIdResponse {

	// Identifiers and Basic Information
	private Integer id;
	private String name;
	private String shortName;
	private String status;

	// Principal and Amounts
	private Double principal;
	private Double minPrincipal;
	private Double maxPrincipal;
	private BigDecimal fixedPrincipalPercentagePerInstallment;
	private Integer principalThresholdForLastInstalment;

	// Interest and Rates
	private Double annualInterestRate;
	private Double interestRatePerPeriod;
	private GetLoanProductsInterestTemplateType interestType;
	private GetLoanProductsInterestRateFrequencyType interestRateFrequencyType;
	private GetLoansProductsInterestCalculationPeriodType interestCalculationPeriodType;
	@Valid
	private List<Integer> interestRateVariationsForBorrowerCycle = new ArrayList<>();

	// Repayment Details
	private Integer numberOfRepayments;
	private Integer repaymentEvery;
	private GetLoanProductsRepaymentFrequencyType repaymentFrequencyType;
	@Valid
	private List<Integer> numberOfRepaymentVariationsForBorrowerCycle = new ArrayList<>();

	// Accounting and Charges
	private GetLoanAccountingMappings accountingMappings;
	private GetLoanProductsAccountingRule accountingRule;
	@Valid
	private List<Integer> charges = new ArrayList<>();
	private GetLoanProductsCurrency currency;
	@Valid
	private Set<@Valid GetLoanFeeToIncomeAccountMappings> feeToIncomeAccountMappings = new LinkedHashSet<>();

	// Disbursement and Multi-Loan Details
	private Boolean multiDisburseLoan;
	private Integer maxTrancheCount;
	private Boolean includeInBorrowerCycle;
	private Boolean useBorrowerCycle;

	// Mappings and Variations
	@Valid
	private Set<@Valid GetLoanProductsPrincipalVariationsForBorrowerCycle> productsPrincipalVariationsForBorrowerCycle = new LinkedHashSet<>();
	@Valid
	private Set<@Valid GetLoanPaymentChannelToFundSourceMappings> paymentChannelToFundSourceMappings = new LinkedHashSet<>();

	// Other Details
	private Double outstandingLoanBalance;
	private Integer overdueDaysForNPA;
	private Integer transactionProcessingStrategyId;
	private String transactionProcessingStrategyName;

}
