/* (C)2024 */
package com.techservices.digitalbanking.customer.domian.dto.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientKycRequest {
	private String validId;

	private String bvn;

	private String passportPhoto;

	private String utilityBill;

	private String certificateOfRegistration;

	private String businessOwnerId;

	private String incorporationDocuments;

	private String taxIdentificationNumber;

	private String bvnOfDirectorsOrOwners;

	private String bvnOfSignatoriesOrDirectors;

	private String boardResolutionForCorporateAccounts;

	private BigDecimal maximumBalance;

	private BigDecimal maximumDailyDepositLimit;

	private BigDecimal maximumSingleDepositLimit;

	private BigDecimal maximumDailyWithdrawalLimit;

	private BigDecimal maximumSingleWithdrawalLimit;
}
