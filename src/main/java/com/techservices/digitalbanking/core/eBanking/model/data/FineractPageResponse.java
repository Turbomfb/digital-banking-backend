/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FineractPageResponse<E> implements Serializable {

	private int totalFilteredRecords;
	private List<E> pageItems = Collections.emptyList();

	public FineractPageResponse(List<E> contents) {
		this.totalFilteredRecords = contents.size();
		this.pageItems = !contents.isEmpty() ? contents : Collections.emptyList();
	}

	public static <E> FineractPageResponse<E> create(int totalFilteredRecords, List<E> pageItems) {
		FineractPageResponse<E> response = new FineractPageResponse<>();
		response.setTotalFilteredRecords(totalFilteredRecords);
		response.setPageItems(pageItems);
		return response;
	}

	public static <E> FineractPageResponse<E> create(FineractPageTransactionResponse<E> savingsAccountTransactions) {
		FineractPageResponse<E> response = new FineractPageResponse<>();
		response.setTotalFilteredRecords(savingsAccountTransactions.getContent().size());
		response.setPageItems(
				savingsAccountTransactions.getContent()
						.stream()
						.sorted((a, b) -> {
							if (a instanceof SavingsAccountTransactionData && b instanceof SavingsAccountTransactionData) {
								return ((SavingsAccountTransactionData) a).getId().compareTo(((SavingsAccountTransactionData) b).getId());
							}
							return 0;
						})
						.toList()
		);
		return response;
	}
}
