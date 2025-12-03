/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SmsNotificationRequest {
	private String to;

	private String from;

	private String sms;

	private String type;
	private String channel;

  @JsonProperty("api_key")
	private String apiKey;

  @JsonProperty("Message_type")
	private String messageType;
}
