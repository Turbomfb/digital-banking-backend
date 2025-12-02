/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalPaymentServiceAuthenticationResponse {
	private List<String> notifications;
	private Token token;
	private User user;
	private String loginHash;
	private EnvironmentDetail envronmentDetail;
	private String id;
	private BusinessDetails businessDetails;
	private Object subsidiaryDetails;
	private Object activeSubsidiary;
	private boolean routeToGetStarted;
	private String status;
	private String statusCode;
	private String message;

	public boolean isSuccessful() {

		return "success".equalsIgnoreCase(status);
	}

	@Getter
	@Setter
	public static class Token {
		private String accessToken;
	}

	@Getter
	@Setter
	public static class User {
		private long id;
		private String firstName;
		private String lastName;
		private String emailAddress;
		private String mobileNumber;
		private int roleId;
		private String role;
		private boolean isEmailVerified;
		private String avatar;
		private String status;
	}

	@Getter
	@Setter
	public static class EnvironmentDetail {
		private Long id;
		private String name;
	}

	@Getter
	@Setter
	public static class BusinessDetails {
		private long id;
		private String accountNo;
		private String externalId;
		private Status status;
		private boolean active;
		private String fullname;
		private String displayName;
		private String mobileNo;
		private String emailAddress;
		private String clientClassification;
		private String clientType;
		private List<Object> groups;
		private boolean isStaff;
		private long officeId;
		private String officeName;
		private Timeline timeline;
		private String savingsProductName;
		private long savingsAccountId;
		private LegalForm legalForm;
		private ClientNonPersonDetails clientNonPersonDetails;

		@Getter
		@Setter
		public static class Status {
			private long id;
			private String code;
			private String value;
		}

		@Getter
		@Setter
		public static class Timeline {
			private String submittedByUsername;
			private String submittedByFirstname;
			private String submittedByLastname;
			private String approvedByUsername;
			private String approvedByFirstname;
			private String approvedByLastname;
		}

		@Getter
		@Setter
		public static class LegalForm {
			private long id;
			private String code;
			private String value;
		}

		@Getter
		@Setter
		public static class ClientNonPersonDetails {
			private Constitution constitution;
			private String incorpNumber;
			private String mainBusinessLine;
			private String remarks;

			@Getter
			@Setter
			public static class Constitution {
				private long id;
				private String name;
				private boolean active;
				private boolean mandatory;
			}
		}
	}
}
