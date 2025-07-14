/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.domain.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.core.fineract.model.response.TransactionMetadata;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SavingsAccountTransactionRequest {
	private BigDecimal amount;
	private String narration;
	private String savingsId;
	private String accountNumber;
	private String otp;
	private String bankNipCode;
	private String transactionPin;
	private String uniqueId;

	public void validateForOtpGeneration() {
		this.validateAmount();
		this.validateTransactionPin();
		this.narration = null;
		this.savingsId = null;
		this.accountNumber = null;
		this.otp = null;
		this.bankNipCode = null;
	}

	private void validateTransactionPin() {
		if (StringUtils.isBlank(this.transactionPin)) {
			throw new ValidationException("transactionPin.field.cannot.be.blank", "transactionPin cannot be empty");
		}
	}

	private void validateAmount() {
		if (this.amount == null || this.amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new ValidationException("amount.field.cannot.be.zero", "Amount cannot be zero");
		}
	}

	public void validateForOtpVerification() {
		this.validateAmount();
		if (StringUtils.isBlank(this.accountNumber)) {
			throw new ValidationException("accountNumber.field.cannot.be.blank", "accountNumber cannot be empty");
		}
		if (StringUtils.isBlank(this.otp)) {
			throw new ValidationException("otp.field.cannot.be.blank", "otp cannot be empty");
		}
		if (StringUtils.isBlank(this.bankNipCode)) {
			throw new ValidationException("bankNipCode.field.cannot.be.blank", "bankNipCode cannot be empty");
		}
		if (StringUtils.isBlank(this.uniqueId)) {
			throw new ValidationException("uniqueId.field.cannot.be.blank", "uniqueId cannot be empty");
		}
	}
}
