/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.model.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineractPageResponse<E> implements Serializable {

	private int totalFilteredRecords;
	private List<E> pageItems = Collections.emptyList();

	public static <E> FineractPageResponse<E> create(int totalFilteredRecords, List<E> pageItems) {
		FineractPageResponse<E> response = new FineractPageResponse<>();
		response.setTotalFilteredRecords(totalFilteredRecords);
		response.setPageItems(pageItems);
		return response;
	}

	public static <E> FineractPageResponse<E> create(FineractPageTransactionResponse<E> savingsAccountTransactions) {
		FineractPageResponse<E> response = new FineractPageResponse<>();
		response.setTotalFilteredRecords(savingsAccountTransactions.getContent().size());
		response.setPageItems(savingsAccountTransactions.getContent());
		return response;
	}
}
