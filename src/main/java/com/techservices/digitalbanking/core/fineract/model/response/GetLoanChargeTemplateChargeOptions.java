/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import lombok.Getter;
import lombok.Setter;

/** GetLoanChargeTemplateChargeOptions */
@Getter
@Setter
public class GetLoanChargeTemplateChargeOptions {

	private Boolean active;

	private Double amount;

	private GetLoanChargeTemplateChargeAppliesTo chargeAppliesTo;

	private GetLoanChargeCalculationType chargeCalculationType;

	private GetLoanChargeTemplateChargeTimeType chargeTimeType;

	private GetLoanChargeCurrency currency;

	private Integer id;

	private String name;

	private Boolean penalty;
}
