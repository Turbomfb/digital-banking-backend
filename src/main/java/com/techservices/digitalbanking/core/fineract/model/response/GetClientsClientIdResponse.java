/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.techservices.digitalbanking.customer.domian.dto.CustomerTierData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techservices.digitalbanking.core.fineract.model.request.GetClientKycData;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientsClientIdResponse {

	private Long id;

	private Long officeId;

	private String officeName;

	private String accountNo;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate activationDate;

	private Boolean active;

	private String displayName;

	private String firstname;

	private String fullname;

	private String lastname;

	private String emailAddress;

	private String mobileNo;

	private Long savingsProductId;

	private String savingsProductName;

	private EnumOptionData status;

	private GetClientsTimeline timeline;
	private ClientTierData clientTierData;

	private String nationality;
	private String meansOfIdentification;
	private boolean proofOfAddress;
	private Long savingsAccountId;
	private String savingsAccountNo;
	private LegalForm legalForm;
	private BigDecimal balance;
	private CustomerTierData kycTier;

	private GetDataTablesResponse settlementAccount;



	@Getter
	@Setter
	public static class LegalForm {
		private long id;
		private String code;
		private String value;
	}
}
