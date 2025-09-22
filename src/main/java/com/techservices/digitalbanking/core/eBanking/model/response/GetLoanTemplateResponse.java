/* Developed by MKAN Engineering (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLoanTemplateResponse {

	public Long clientId;
	public String clientName;
	public Long clientOfficeId;
	public Long loanProductId;
	public String loanProductName;
	public String loanProductDescription;
	public Currency currency;
	public BigDecimal principal;
	public Long termFrequency;
	public FrequencyType termPeriodFrequencyType;
	public Long numberOfRepayments;
	public Long repaymentEvery;
	public FrequencyType repaymentFrequencyType;
	public BigDecimal interestRatePerPeriod;
	public FrequencyType interestRateFrequencyType;
	public BigDecimal annualInterestRate;
	public AmortizationType amortizationType;
	public InterestType interestType;
	public InterestCalculationPeriodType interestCalculationPeriodType;
	public Long transactionProcessingStrategyId;
	public Timeline timeline;
	public DaysInType daysInMonthType;
	public DaysInType daysInYearType;
	public boolean isInterestRecalculationEnabled;
	public InterestRecalculationData interestRecalculationData;
	public List<Charge> charges;
	public List<ProductOption> productOptions;
	public List<LoanOfficerOption> loanOfficerOptions;
	public List<LoanPurposeOption> loanPurposeOptions;
	public List<FrequencyTypeOption> termFrequencyTypeOptions;
	public List<FrequencyTypeOption> repaymentFrequencyTypeOptions;
	public List<FrequencyTypeOption> interestRateFrequencyTypeOptions;
	public List<AmortizationTypeOption> amortizationTypeOptions;
	public List<InterestTypeOption> interestTypeOptions;
	public List<InterestCalculationPeriodTypeOption> interestCalculationPeriodTypeOptions;
	public List<TransactionProcessingStrategyOption> transactionProcessingStrategyOptions;
	public List<ChargeOption> chargeOptions;
	public List<LoanCollateralOption> loanCollateralOptions;
	public List<AccountLinkingOption> accountLinkingOptions;
	public List<DataTable> datatables;
	public boolean isLoanProductLinkedToFloatingRate;
	public boolean isFloatingInterestRate;
	public boolean isTopup;
	public boolean fraud;
	public boolean isRatesEnabled;

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class Currency {
		public String code;
		public String name;
		public Long decimalPlaces;
		public String displaySymbol;
		public String nameCode;
		public String displayLabel;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class FrequencyType {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class AmortizationType {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class InterestType {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class InterestCalculationPeriodType {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class Timeline {
		public List<Integer> expectedDisbursementDate;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class DaysInType {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class InterestRecalculationData {
		public InterestRecalculationCompoundingType interestRecalculationCompoundingType;
		public RecalculationFrequencyType recalculationCompoundingFrequencyType;
		public RescheduleStrategyType rescheduleStrategyType;
		public RecalculationFrequencyType recalculationRestFrequencyType;

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class InterestRecalculationCompoundingType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class RecalculationFrequencyType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class RescheduleStrategyType {
			public Long id;
			public String code;
			public String value;
		}
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class Charge {
		public Long id;
		public String name;
		public boolean active;
		public boolean penalty;
		public Currency currency;
		public BigDecimal amount;
		public ChargeTimeType chargeTimeType;
		public ChargeAppliesTo chargeAppliesTo;
		public ChargeCalculationType chargeCalculationType;

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ChargeTimeType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ChargeAppliesTo {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ChargeCalculationType {
			public Long id;
			public String code;
			public String value;
		}
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class ProductOption {
		public Long id;
		public String name;
		public boolean includeInBorrowerCycle;
		public boolean useBorrowerCycle;
		public boolean isLinkedToFloatingInterestRates;
		public boolean isFloatingInterestRateCalculationAllowed;
		public boolean allowVariableInstallments;
		public boolean isInterestRecalculationEnabled;
		public boolean canDefineInstallmentAmount;
		public List<Object> principalVariationsForBorrowerCycle;
		public List<Object> interestRateVariationsForBorrowerCycle;
		public List<Object> numberOfRepaymentVariationsForBorrowerCycle;
		public boolean canUseForTopup;
		public boolean enableAccrualActivityPosting;
		public boolean isRatesEnabled;
		public List<AdvancedPaymentAllocationTransactionType> advancedPaymentAllocationTransactionTypes;
		public List<AdvancedPaymentAllocationFutureInstallmentAllocationRule> advancedPaymentAllocationFutureInstallmentAllocationRules;
		public List<AdvancedPaymentAllocationType> advancedPaymentAllocationTypes;
		public List<CreditAllocationTransactionType> creditAllocationTransactionTypes;
		public List<CreditAllocationAllocationType> creditAllocationAllocationTypes;
		public boolean multiDisburseLoan;
		public boolean disallowExpectedDisbursements;
		public boolean allowApprovedDisbursedAmountsOverApplied;
		public boolean holdGuaranteeFunds;
		public boolean accountMovesOutOfNPAOnlyOnArrearsCompletion;
		public boolean syncExpectedWithDisbursementDate;
		public boolean isEqualAmortization;
		public boolean enableDownPayment;
		public boolean enableAutoRepaymentForDownPayment;
		public boolean enableInstallmentLevelDelinquency;

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class AdvancedPaymentAllocationTransactionType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class AdvancedPaymentAllocationFutureInstallmentAllocationRule {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class AdvancedPaymentAllocationType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class CreditAllocationTransactionType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class CreditAllocationAllocationType {
			public Long id;
			public String code;
			public String value;
		}
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class LoanOfficerOption {
		public Long id;
		public String firstname;
		public String lastname;
		public String displayName;
		public Long officeId;
		public String officeName;
		public boolean isLoanOfficer;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class LoanPurposeOption {
		public Long id;
		public String name;
		public Long position;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class FrequencyTypeOption {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class AmortizationTypeOption {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class InterestTypeOption {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class InterestCalculationPeriodTypeOption {
		public Long id;
		public String code;
		public String value;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class TransactionProcessingStrategyOption {
		public Long id;
		public String code;
		public String name;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class ChargeOption {
		public Long id;
		public String name;
		public boolean active;
		public boolean penalty;
		public Currency currency;
		public BigDecimal amount;
		public ChargeTimeType chargeTimeType;
		public ChargeAppliesTo chargeAppliesTo;
		public ChargeCalculationType chargeCalculationType;

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ChargeTimeType {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ChargeAppliesTo {
			public Long id;
			public String code;
			public String value;
		}

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ChargeCalculationType {
			public Long id;
			public String code;
			public String value;
		}
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class LoanCollateralOption {
		public Long id;
		public String name;
		public Long position;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class AccountLinkingOption {
		public Long id;
		public String accountNo;
		public Long clientId;
		public String clientName;
		public Long productId;
		public String productName;
		public Long fieldOfficerId;
		public Currency currency;
	}

	@RequiredArgsConstructor
	@Setter
	@Getter
	public static class DataTable {
		public String applicationTableName;
		public String registeredTableName;
		public List<ColumnHeaderData> columnHeaderData;

		@RequiredArgsConstructor
		@Setter
		@Getter
		public static class ColumnHeaderData {
			public String columnName;
			public String columnType;
			public int columnLength;
			public String columnDisplayType;
			public boolean isColumnNullable;
			public boolean isColumnPrimaryKey;
			public List<String> columnValues;
		}
	}
}
