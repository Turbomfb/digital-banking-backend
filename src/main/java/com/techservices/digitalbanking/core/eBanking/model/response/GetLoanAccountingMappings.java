/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

/** GetLoanAccountingMappings */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class GetLoanAccountingMappings {

	private GetLoanFundSourceAccount fundSourceAccount;

	private GetLoanIncomeFromFeeAccount incomeFromFeeAccount;

	private GetLoanIncomeFromPenaltyAccount incomeFromPenaltyAccount;

	private GetLoanInterestOnLoanAccount interestOnLoanAccount;

	private GetLoanPortfolioAccount loanPortfolioAccount;

	private GetLoanOverpaymentLiabilityAccount overpaymentLiabilityAccount;

	private GetLoanTransfersInSuspenseAccount transfersInSuspenseAccount;

	private GetLoanWriteOffAccount writeOffAccount;

	public GetLoanAccountingMappings fundSourceAccount(GetLoanFundSourceAccount fundSourceAccount) {
		this.fundSourceAccount = fundSourceAccount;
		return this;
	}

	/**
	 * Get fundSourceAccount
	 *
	 * @return fundSourceAccount
	 */
	@Valid
	@Schema(name = "fundSourceAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("fundSourceAccount")
	public GetLoanFundSourceAccount getFundSourceAccount() {
		return fundSourceAccount;
	}

	public void setFundSourceAccount(GetLoanFundSourceAccount fundSourceAccount) {
		this.fundSourceAccount = fundSourceAccount;
	}

	public GetLoanAccountingMappings incomeFromFeeAccount(GetLoanIncomeFromFeeAccount incomeFromFeeAccount) {
		this.incomeFromFeeAccount = incomeFromFeeAccount;
		return this;
	}

	/**
	 * Get incomeFromFeeAccount
	 *
	 * @return incomeFromFeeAccount
	 */
	@Valid
	@Schema(name = "incomeFromFeeAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("incomeFromFeeAccount")
	public GetLoanIncomeFromFeeAccount getIncomeFromFeeAccount() {
		return incomeFromFeeAccount;
	}

	public void setIncomeFromFeeAccount(GetLoanIncomeFromFeeAccount incomeFromFeeAccount) {
		this.incomeFromFeeAccount = incomeFromFeeAccount;
	}

	public GetLoanAccountingMappings incomeFromPenaltyAccount(
			GetLoanIncomeFromPenaltyAccount incomeFromPenaltyAccount) {
		this.incomeFromPenaltyAccount = incomeFromPenaltyAccount;
		return this;
	}

	/**
	 * Get incomeFromPenaltyAccount
	 *
	 * @return incomeFromPenaltyAccount
	 */
	@Valid
	@Schema(name = "incomeFromPenaltyAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("incomeFromPenaltyAccount")
	public GetLoanIncomeFromPenaltyAccount getIncomeFromPenaltyAccount() {
		return incomeFromPenaltyAccount;
	}

	public void setIncomeFromPenaltyAccount(GetLoanIncomeFromPenaltyAccount incomeFromPenaltyAccount) {
		this.incomeFromPenaltyAccount = incomeFromPenaltyAccount;
	}

	public GetLoanAccountingMappings interestOnLoanAccount(GetLoanInterestOnLoanAccount interestOnLoanAccount) {
		this.interestOnLoanAccount = interestOnLoanAccount;
		return this;
	}

	/**
	 * Get interestOnLoanAccount
	 *
	 * @return interestOnLoanAccount
	 */
	@Valid
	@Schema(name = "interestOnLoanAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestOnLoanAccount")
	public GetLoanInterestOnLoanAccount getInterestOnLoanAccount() {
		return interestOnLoanAccount;
	}

	public void setInterestOnLoanAccount(GetLoanInterestOnLoanAccount interestOnLoanAccount) {
		this.interestOnLoanAccount = interestOnLoanAccount;
	}

	public GetLoanAccountingMappings loanPortfolioAccount(GetLoanPortfolioAccount loanPortfolioAccount) {
		this.loanPortfolioAccount = loanPortfolioAccount;
		return this;
	}

	/**
	 * Get loanPortfolioAccount
	 *
	 * @return loanPortfolioAccount
	 */
	@Valid
	@Schema(name = "loanPortfolioAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("loanPortfolioAccount")
	public GetLoanPortfolioAccount getLoanPortfolioAccount() {
		return loanPortfolioAccount;
	}

	public void setLoanPortfolioAccount(GetLoanPortfolioAccount loanPortfolioAccount) {
		this.loanPortfolioAccount = loanPortfolioAccount;
	}

	public GetLoanAccountingMappings overpaymentLiabilityAccount(
			GetLoanOverpaymentLiabilityAccount overpaymentLiabilityAccount) {
		this.overpaymentLiabilityAccount = overpaymentLiabilityAccount;
		return this;
	}

	/**
	 * Get overpaymentLiabilityAccount
	 *
	 * @return overpaymentLiabilityAccount
	 */
	@Valid
	@Schema(name = "overpaymentLiabilityAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("overpaymentLiabilityAccount")
	public GetLoanOverpaymentLiabilityAccount getOverpaymentLiabilityAccount() {
		return overpaymentLiabilityAccount;
	}

	public void setOverpaymentLiabilityAccount(GetLoanOverpaymentLiabilityAccount overpaymentLiabilityAccount) {
		this.overpaymentLiabilityAccount = overpaymentLiabilityAccount;
	}

	public GetLoanAccountingMappings transfersInSuspenseAccount(
			GetLoanTransfersInSuspenseAccount transfersInSuspenseAccount) {
		this.transfersInSuspenseAccount = transfersInSuspenseAccount;
		return this;
	}

	/**
	 * Get transfersInSuspenseAccount
	 *
	 * @return transfersInSuspenseAccount
	 */
	@Valid
	@Schema(name = "transfersInSuspenseAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("transfersInSuspenseAccount")
	public GetLoanTransfersInSuspenseAccount getTransfersInSuspenseAccount() {
		return transfersInSuspenseAccount;
	}

	public void setTransfersInSuspenseAccount(GetLoanTransfersInSuspenseAccount transfersInSuspenseAccount) {
		this.transfersInSuspenseAccount = transfersInSuspenseAccount;
	}

	public GetLoanAccountingMappings writeOffAccount(GetLoanWriteOffAccount writeOffAccount) {
		this.writeOffAccount = writeOffAccount;
		return this;
	}

	/**
	 * Get writeOffAccount
	 *
	 * @return writeOffAccount
	 */
	@Valid
	@Schema(name = "writeOffAccount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("writeOffAccount")
	public GetLoanWriteOffAccount getWriteOffAccount() {
		return writeOffAccount;
	}

	public void setWriteOffAccount(GetLoanWriteOffAccount writeOffAccount) {
		this.writeOffAccount = writeOffAccount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		GetLoanAccountingMappings getLoanAccountingMappings = (GetLoanAccountingMappings) o;
		return Objects.equals(this.fundSourceAccount, getLoanAccountingMappings.fundSourceAccount)
				&& Objects.equals(this.incomeFromFeeAccount, getLoanAccountingMappings.incomeFromFeeAccount)
				&& Objects.equals(this.incomeFromPenaltyAccount, getLoanAccountingMappings.incomeFromPenaltyAccount)
				&& Objects.equals(this.interestOnLoanAccount, getLoanAccountingMappings.interestOnLoanAccount)
				&& Objects.equals(this.loanPortfolioAccount, getLoanAccountingMappings.loanPortfolioAccount)
				&& Objects.equals(this.overpaymentLiabilityAccount,
						getLoanAccountingMappings.overpaymentLiabilityAccount)
				&& Objects.equals(this.transfersInSuspenseAccount, getLoanAccountingMappings.transfersInSuspenseAccount)
				&& Objects.equals(this.writeOffAccount, getLoanAccountingMappings.writeOffAccount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fundSourceAccount, incomeFromFeeAccount, incomeFromPenaltyAccount, interestOnLoanAccount,
				loanPortfolioAccount, overpaymentLiabilityAccount, transfersInSuspenseAccount, writeOffAccount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GetLoanAccountingMappings {\n");
		sb.append("    fundSourceAccount: ").append(toIndentedString(fundSourceAccount)).append("\n");
		sb.append("    incomeFromFeeAccount: ").append(toIndentedString(incomeFromFeeAccount)).append("\n");
		sb.append("    incomeFromPenaltyAccount: ").append(toIndentedString(incomeFromPenaltyAccount)).append("\n");
		sb.append("    interestOnLoanAccount: ").append(toIndentedString(interestOnLoanAccount)).append("\n");
		sb.append("    loanPortfolioAccount: ").append(toIndentedString(loanPortfolioAccount)).append("\n");
		sb.append("    overpaymentLiabilityAccount: ").append(toIndentedString(overpaymentLiabilityAccount))
				.append("\n");
		sb.append("    transfersInSuspenseAccount: ").append(toIndentedString(transfersInSuspenseAccount)).append("\n");
		sb.append("    writeOffAccount: ").append(toIndentedString(writeOffAccount)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
