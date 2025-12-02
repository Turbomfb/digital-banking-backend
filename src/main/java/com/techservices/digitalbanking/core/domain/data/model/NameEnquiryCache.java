/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.techservices.digitalbanking.walletaccount.domain.response.NameEnquiryResponse;

import com.techservices.digitalbanking.walletaccount.domain.response.NameEnquiryResponse.NameEnquiryResponseData;
import com.techservices.digitalbanking.walletaccount.domain.response.NameEnquiryResponse.NameEnquiryResponseData.NameEnquiryResponseDataBankDetail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.techservices.digitalbanking.core.util.AppUtil.SUCCESS;

@Entity
@Table(name = "name_enquiry_cache", uniqueConstraints = @UniqueConstraint(columnNames = {"account_number",
		"bank_code"}))
@Setter
@Getter
@ToString
public class NameEnquiryCache {

	@Id
	private String id;

	@Column(name = "account_number", length = 20, nullable = false)
	private String accountNumber;

	@Column(name = "bank_code", length = 20, nullable = false)
	private String bankCode;

	@Column(name = "account_name", length = 255)
	private String accountName;

	@Column(name = "bank_name", length = 255)
	private String bankName;

	@Column(name = "account_currency", length = 10)
	private String accountCurrency;

	@Column(name = "account_type", length = 50)
	private String accountType;

	@Column(length = 50)
	private String status;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "last_modified_at")
	private LocalDateTime lastModifiedAt;

	/** Parse NameEnquiryResponse into NameEnquiryCache entity */
	public static NameEnquiryCache parse(NameEnquiryResponse response, String accountNumber, String bankCode) {

		NameEnquiryCache cache = new NameEnquiryCache();
		cache.setId(UUID.randomUUID().toString());
		cache.setAccountNumber(accountNumber);
		cache.setBankCode(bankCode);
		cache.setStatus(response.getStatus());
		cache.setCreatedAt(LocalDateTime.now());

		if (response.getData() != null && response.getData().getBankDetails() != null) {
			NameEnquiryResponseDataBankDetail bankDetail = response.getData().getBankDetails();
			cache.setAccountName(bankDetail.getAccountName());
			cache.setBankName(bankDetail.getBankName());
			cache.setAccountCurrency(bankDetail.getAccountCurrency());
			cache.setAccountType(bankDetail.getAccountType());
		}

		return cache;
	}

	/** Convert cached entity back to NameEnquiryResponse */
	public NameEnquiryResponse toResponse() {

		NameEnquiryResponse response = new NameEnquiryResponse();
		response.setSuccess(true);
		response.setStatus(SUCCESS);

		NameEnquiryResponse.NameEnquiryResponseData data = new NameEnquiryResponseData();
		NameEnquiryResponseDataBankDetail bankDetail = new NameEnquiryResponseDataBankDetail();

		bankDetail.setAccountName(this.accountName);
		bankDetail.setAccountNumber(this.accountNumber);
		bankDetail.setBankName(this.bankName);
		bankDetail.setAccountCurrency(this.accountCurrency);
		bankDetail.setAccountType(this.accountType);

		data.setBankDetails(bankDetail);
		response.setData(data);

		return response;
	}
}
