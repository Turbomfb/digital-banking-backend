/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSearchRequest {

	private Request request;
	private Long page;
	private Long size;

	@Setter
	@Getter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Request {
		private String text;
	}
}
