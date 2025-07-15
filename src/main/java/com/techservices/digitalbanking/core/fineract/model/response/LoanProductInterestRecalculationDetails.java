/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.response;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;

/** LoanProductInterestRecalculationDetails */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-30T13:54:37.023258+01:00[Africa/Lagos]", comments = "Generator version: 7.5.0")
public class LoanProductInterestRecalculationDetails {

	private Boolean arrearsBasedOnOriginalSchedule;

	private Integer compoundingFrequencyNthDay;

	private Integer compoundingFrequencyOnDay;

	/** Gets or Sets compoundingFrequencyType */
	public enum CompoundingFrequencyTypeEnum {
		INVALID("INVALID"),

		SAME_AS_REPAYMENT_PERIOD("SAME_AS_REPAYMENT_PERIOD"),

		DAILY("DAILY"),

		WEEKLY("WEEKLY"),

		MONTHLY("MONTHLY");

		private String value;

		CompoundingFrequencyTypeEnum(String value) {
			this.value = value;
		}

		@JsonValue
		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static CompoundingFrequencyTypeEnum fromValue(String value) {
			for (CompoundingFrequencyTypeEnum b : CompoundingFrequencyTypeEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	private CompoundingFrequencyTypeEnum compoundingFrequencyType;

	private Integer compoundingFrequencyWeekday;

	private Integer compoundingInterval;

	private Long id;

	private Integer interestRecalculationCompoundingMethod;

	private Boolean isCompoundingToBePostedAsTransaction;

	private Boolean _new;

	private Integer rescheduleStrategyMethod;

	private Integer restFrequencyNthDay;

	private Integer restFrequencyOnDay;

	/** Gets or Sets restFrequencyType */
	public enum RestFrequencyTypeEnum {
		INVALID("INVALID"),

		SAME_AS_REPAYMENT_PERIOD("SAME_AS_REPAYMENT_PERIOD"),

		DAILY("DAILY"),

		WEEKLY("WEEKLY"),

		MONTHLY("MONTHLY");

		private String value;

		RestFrequencyTypeEnum(String value) {
			this.value = value;
		}

		@JsonValue
		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static RestFrequencyTypeEnum fromValue(String value) {
			for (RestFrequencyTypeEnum b : RestFrequencyTypeEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}
	}

	private RestFrequencyTypeEnum restFrequencyType;

	private Integer restFrequencyWeekday;

	private Integer restInterval;

	public LoanProductInterestRecalculationDetails arrearsBasedOnOriginalSchedule(
			Boolean arrearsBasedOnOriginalSchedule) {
		this.arrearsBasedOnOriginalSchedule = arrearsBasedOnOriginalSchedule;
		return this;
	}

	/**
	 * Get arrearsBasedOnOriginalSchedule
	 *
	 * @return arrearsBasedOnOriginalSchedule
	 */
	@Schema(name = "arrearsBasedOnOriginalSchedule", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("arrearsBasedOnOriginalSchedule")
	public Boolean getArrearsBasedOnOriginalSchedule() {
		return arrearsBasedOnOriginalSchedule;
	}

	public void setArrearsBasedOnOriginalSchedule(Boolean arrearsBasedOnOriginalSchedule) {
		this.arrearsBasedOnOriginalSchedule = arrearsBasedOnOriginalSchedule;
	}

	public LoanProductInterestRecalculationDetails compoundingFrequencyNthDay(Integer compoundingFrequencyNthDay) {
		this.compoundingFrequencyNthDay = compoundingFrequencyNthDay;
		return this;
	}

	/**
	 * Get compoundingFrequencyNthDay
	 *
	 * @return compoundingFrequencyNthDay
	 */
	@Schema(name = "compoundingFrequencyNthDay", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("compoundingFrequencyNthDay")
	public Integer getCompoundingFrequencyNthDay() {
		return compoundingFrequencyNthDay;
	}

	public void setCompoundingFrequencyNthDay(Integer compoundingFrequencyNthDay) {
		this.compoundingFrequencyNthDay = compoundingFrequencyNthDay;
	}

	public LoanProductInterestRecalculationDetails compoundingFrequencyOnDay(Integer compoundingFrequencyOnDay) {
		this.compoundingFrequencyOnDay = compoundingFrequencyOnDay;
		return this;
	}

	/**
	 * Get compoundingFrequencyOnDay
	 *
	 * @return compoundingFrequencyOnDay
	 */
	@Schema(name = "compoundingFrequencyOnDay", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("compoundingFrequencyOnDay")
	public Integer getCompoundingFrequencyOnDay() {
		return compoundingFrequencyOnDay;
	}

	public void setCompoundingFrequencyOnDay(Integer compoundingFrequencyOnDay) {
		this.compoundingFrequencyOnDay = compoundingFrequencyOnDay;
	}

	public LoanProductInterestRecalculationDetails compoundingFrequencyType(
			CompoundingFrequencyTypeEnum compoundingFrequencyType) {
		this.compoundingFrequencyType = compoundingFrequencyType;
		return this;
	}

	/**
	 * Get compoundingFrequencyType
	 *
	 * @return compoundingFrequencyType
	 */
	@Schema(name = "compoundingFrequencyType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("compoundingFrequencyType")
	public CompoundingFrequencyTypeEnum getCompoundingFrequencyType() {
		return compoundingFrequencyType;
	}

	public void setCompoundingFrequencyType(CompoundingFrequencyTypeEnum compoundingFrequencyType) {
		this.compoundingFrequencyType = compoundingFrequencyType;
	}

	public LoanProductInterestRecalculationDetails compoundingFrequencyWeekday(Integer compoundingFrequencyWeekday) {
		this.compoundingFrequencyWeekday = compoundingFrequencyWeekday;
		return this;
	}

	/**
	 * Get compoundingFrequencyWeekday
	 *
	 * @return compoundingFrequencyWeekday
	 */
	@Schema(name = "compoundingFrequencyWeekday", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("compoundingFrequencyWeekday")
	public Integer getCompoundingFrequencyWeekday() {
		return compoundingFrequencyWeekday;
	}

	public void setCompoundingFrequencyWeekday(Integer compoundingFrequencyWeekday) {
		this.compoundingFrequencyWeekday = compoundingFrequencyWeekday;
	}

	public LoanProductInterestRecalculationDetails compoundingInterval(Integer compoundingInterval) {
		this.compoundingInterval = compoundingInterval;
		return this;
	}

	/**
	 * Get compoundingInterval
	 *
	 * @return compoundingInterval
	 */
	@Schema(name = "compoundingInterval", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("compoundingInterval")
	public Integer getCompoundingInterval() {
		return compoundingInterval;
	}

	public void setCompoundingInterval(Integer compoundingInterval) {
		this.compoundingInterval = compoundingInterval;
	}

	public LoanProductInterestRecalculationDetails id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 */
	@Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanProductInterestRecalculationDetails interestRecalculationCompoundingMethod(
			Integer interestRecalculationCompoundingMethod) {
		this.interestRecalculationCompoundingMethod = interestRecalculationCompoundingMethod;
		return this;
	}

	/**
	 * Get interestRecalculationCompoundingMethod
	 *
	 * @return interestRecalculationCompoundingMethod
	 */
	@Schema(name = "interestRecalculationCompoundingMethod", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("interestRecalculationCompoundingMethod")
	public Integer getInterestRecalculationCompoundingMethod() {
		return interestRecalculationCompoundingMethod;
	}

	public void setInterestRecalculationCompoundingMethod(Integer interestRecalculationCompoundingMethod) {
		this.interestRecalculationCompoundingMethod = interestRecalculationCompoundingMethod;
	}

	public LoanProductInterestRecalculationDetails isCompoundingToBePostedAsTransaction(
			Boolean isCompoundingToBePostedAsTransaction) {
		this.isCompoundingToBePostedAsTransaction = isCompoundingToBePostedAsTransaction;
		return this;
	}

	/**
	 * Get isCompoundingToBePostedAsTransaction
	 *
	 * @return isCompoundingToBePostedAsTransaction
	 */
	@Schema(name = "isCompoundingToBePostedAsTransaction", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("isCompoundingToBePostedAsTransaction")
	public Boolean getIsCompoundingToBePostedAsTransaction() {
		return isCompoundingToBePostedAsTransaction;
	}

	public void setIsCompoundingToBePostedAsTransaction(Boolean isCompoundingToBePostedAsTransaction) {
		this.isCompoundingToBePostedAsTransaction = isCompoundingToBePostedAsTransaction;
	}

	public LoanProductInterestRecalculationDetails _new(Boolean _new) {
		this._new = _new;
		return this;
	}

	/**
	 * Get _new
	 *
	 * @return _new
	 */
	@Schema(name = "new", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("new")
	public Boolean getNew() {
		return _new;
	}

	public void setNew(Boolean _new) {
		this._new = _new;
	}

	public LoanProductInterestRecalculationDetails rescheduleStrategyMethod(Integer rescheduleStrategyMethod) {
		this.rescheduleStrategyMethod = rescheduleStrategyMethod;
		return this;
	}

	/**
	 * Get rescheduleStrategyMethod
	 *
	 * @return rescheduleStrategyMethod
	 */
	@Schema(name = "rescheduleStrategyMethod", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("rescheduleStrategyMethod")
	public Integer getRescheduleStrategyMethod() {
		return rescheduleStrategyMethod;
	}

	public void setRescheduleStrategyMethod(Integer rescheduleStrategyMethod) {
		this.rescheduleStrategyMethod = rescheduleStrategyMethod;
	}

	public LoanProductInterestRecalculationDetails restFrequencyNthDay(Integer restFrequencyNthDay) {
		this.restFrequencyNthDay = restFrequencyNthDay;
		return this;
	}

	/**
	 * Get restFrequencyNthDay
	 *
	 * @return restFrequencyNthDay
	 */
	@Schema(name = "restFrequencyNthDay", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("restFrequencyNthDay")
	public Integer getRestFrequencyNthDay() {
		return restFrequencyNthDay;
	}

	public void setRestFrequencyNthDay(Integer restFrequencyNthDay) {
		this.restFrequencyNthDay = restFrequencyNthDay;
	}

	public LoanProductInterestRecalculationDetails restFrequencyOnDay(Integer restFrequencyOnDay) {
		this.restFrequencyOnDay = restFrequencyOnDay;
		return this;
	}

	/**
	 * Get restFrequencyOnDay
	 *
	 * @return restFrequencyOnDay
	 */
	@Schema(name = "restFrequencyOnDay", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("restFrequencyOnDay")
	public Integer getRestFrequencyOnDay() {
		return restFrequencyOnDay;
	}

	public void setRestFrequencyOnDay(Integer restFrequencyOnDay) {
		this.restFrequencyOnDay = restFrequencyOnDay;
	}

	public LoanProductInterestRecalculationDetails restFrequencyType(RestFrequencyTypeEnum restFrequencyType) {
		this.restFrequencyType = restFrequencyType;
		return this;
	}

	/**
	 * Get restFrequencyType
	 *
	 * @return restFrequencyType
	 */
	@Schema(name = "restFrequencyType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("restFrequencyType")
	public RestFrequencyTypeEnum getRestFrequencyType() {
		return restFrequencyType;
	}

	public void setRestFrequencyType(RestFrequencyTypeEnum restFrequencyType) {
		this.restFrequencyType = restFrequencyType;
	}

	public LoanProductInterestRecalculationDetails restFrequencyWeekday(Integer restFrequencyWeekday) {
		this.restFrequencyWeekday = restFrequencyWeekday;
		return this;
	}

	/**
	 * Get restFrequencyWeekday
	 *
	 * @return restFrequencyWeekday
	 */
	@Schema(name = "restFrequencyWeekday", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("restFrequencyWeekday")
	public Integer getRestFrequencyWeekday() {
		return restFrequencyWeekday;
	}

	public void setRestFrequencyWeekday(Integer restFrequencyWeekday) {
		this.restFrequencyWeekday = restFrequencyWeekday;
	}

	public LoanProductInterestRecalculationDetails restInterval(Integer restInterval) {
		this.restInterval = restInterval;
		return this;
	}

	/**
	 * Get restInterval
	 *
	 * @return restInterval
	 */
	@Schema(name = "restInterval", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonProperty("restInterval")
	public Integer getRestInterval() {
		return restInterval;
	}

	public void setRestInterval(Integer restInterval) {
		this.restInterval = restInterval;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoanProductInterestRecalculationDetails loanProductInterestRecalculationDetails = (LoanProductInterestRecalculationDetails) o;
		return Objects.equals(this.arrearsBasedOnOriginalSchedule,
				loanProductInterestRecalculationDetails.arrearsBasedOnOriginalSchedule)
				&& Objects.equals(this.compoundingFrequencyNthDay,
						loanProductInterestRecalculationDetails.compoundingFrequencyNthDay)
				&& Objects.equals(this.compoundingFrequencyOnDay,
						loanProductInterestRecalculationDetails.compoundingFrequencyOnDay)
				&& Objects.equals(this.compoundingFrequencyType,
						loanProductInterestRecalculationDetails.compoundingFrequencyType)
				&& Objects.equals(this.compoundingFrequencyWeekday,
						loanProductInterestRecalculationDetails.compoundingFrequencyWeekday)
				&& Objects.equals(this.compoundingInterval, loanProductInterestRecalculationDetails.compoundingInterval)
				&& Objects.equals(this.id, loanProductInterestRecalculationDetails.id)
				&& Objects.equals(this.interestRecalculationCompoundingMethod,
						loanProductInterestRecalculationDetails.interestRecalculationCompoundingMethod)
				&& Objects.equals(this.isCompoundingToBePostedAsTransaction,
						loanProductInterestRecalculationDetails.isCompoundingToBePostedAsTransaction)
				&& Objects.equals(this._new, loanProductInterestRecalculationDetails._new)
				&& Objects.equals(this.rescheduleStrategyMethod,
						loanProductInterestRecalculationDetails.rescheduleStrategyMethod)
				&& Objects.equals(this.restFrequencyNthDay, loanProductInterestRecalculationDetails.restFrequencyNthDay)
				&& Objects.equals(this.restFrequencyOnDay, loanProductInterestRecalculationDetails.restFrequencyOnDay)
				&& Objects.equals(this.restFrequencyType, loanProductInterestRecalculationDetails.restFrequencyType)
				&& Objects.equals(this.restFrequencyWeekday,
						loanProductInterestRecalculationDetails.restFrequencyWeekday)
				&& Objects.equals(this.restInterval, loanProductInterestRecalculationDetails.restInterval);
	}

	@Override
	public int hashCode() {
		return Objects.hash(arrearsBasedOnOriginalSchedule, compoundingFrequencyNthDay, compoundingFrequencyOnDay,
				compoundingFrequencyType, compoundingFrequencyWeekday, compoundingInterval, id,
				interestRecalculationCompoundingMethod, isCompoundingToBePostedAsTransaction, _new,
				rescheduleStrategyMethod, restFrequencyNthDay, restFrequencyOnDay, restFrequencyType,
				restFrequencyWeekday, restInterval);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoanProductInterestRecalculationDetails {\n");
		sb.append("    arrearsBasedOnOriginalSchedule: ").append(toIndentedString(arrearsBasedOnOriginalSchedule))
				.append("\n");
		sb.append("    compoundingFrequencyNthDay: ").append(toIndentedString(compoundingFrequencyNthDay)).append("\n");
		sb.append("    compoundingFrequencyOnDay: ").append(toIndentedString(compoundingFrequencyOnDay)).append("\n");
		sb.append("    compoundingFrequencyType: ").append(toIndentedString(compoundingFrequencyType)).append("\n");
		sb.append("    compoundingFrequencyWeekday: ").append(toIndentedString(compoundingFrequencyWeekday))
				.append("\n");
		sb.append("    compoundingInterval: ").append(toIndentedString(compoundingInterval)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    interestRecalculationCompoundingMethod: ")
				.append(toIndentedString(interestRecalculationCompoundingMethod)).append("\n");
		sb.append("    isCompoundingToBePostedAsTransaction: ")
				.append(toIndentedString(isCompoundingToBePostedAsTransaction)).append("\n");
		sb.append("    _new: ").append(toIndentedString(_new)).append("\n");
		sb.append("    rescheduleStrategyMethod: ").append(toIndentedString(rescheduleStrategyMethod)).append("\n");
		sb.append("    restFrequencyNthDay: ").append(toIndentedString(restFrequencyNthDay)).append("\n");
		sb.append("    restFrequencyOnDay: ").append(toIndentedString(restFrequencyOnDay)).append("\n");
		sb.append("    restFrequencyType: ").append(toIndentedString(restFrequencyType)).append("\n");
		sb.append("    restFrequencyWeekday: ").append(toIndentedString(restFrequencyWeekday)).append("\n");
		sb.append("    restInterval: ").append(toIndentedString(restInterval)).append("\n");
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
