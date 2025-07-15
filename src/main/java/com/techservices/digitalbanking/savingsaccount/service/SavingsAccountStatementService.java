/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.savingsaccount.service;

import com.techservices.digitalbanking.savingsaccount.domain.request.StatementRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface SavingsAccountStatementService {

	void generateCsvStatement(StatementRequest statementRequest, HttpServletResponse response) throws IOException;

	void generatePdfStatement(StatementRequest statementRequest, HttpServletResponse response) throws IOException;

	void generateExcelStatement(StatementRequest statementRequest, HttpServletResponse response) throws IOException;
}
