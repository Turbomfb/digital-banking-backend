/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetRecurringDepositAccountingMappings {
	private RecurringDepositAccountingMappingsAccount savingsReferenceAccount;
	private RecurringDepositAccountingMappingsAccount incomeFromFeeAccount;
	private RecurringDepositAccountingMappingsAccount incomeFromPenaltyAccount;
	private RecurringDepositAccountingMappingsAccount interestOnSavingsAccount;
	private RecurringDepositAccountingMappingsAccount savingsControlAccount;
	private RecurringDepositAccountingMappingsAccount transfersInSuspenseAccount;
}
