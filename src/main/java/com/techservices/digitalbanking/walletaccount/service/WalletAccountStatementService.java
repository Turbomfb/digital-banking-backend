/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.service;

import java.io.IOException;

import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.walletaccount.domain.request.StatementRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface WalletAccountStatementService {

	void generateCsvStatement(StatementRequest statementRequest, HttpServletResponse response, Customer customer)
			throws IOException;

	void generatePdfStatement(StatementRequest statementRequest, HttpServletResponse response, Customer customer)
			throws IOException;

	void generateExcelStatement(StatementRequest statementRequest, HttpServletResponse response, Customer customer)
			throws IOException;
}
