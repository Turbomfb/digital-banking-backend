/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineractPageTransactionResponse<E> implements Serializable {

	private int total;
	private List<E> content = Collections.emptyList();
}
