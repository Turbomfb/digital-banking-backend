/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.walletaccount.service.impl;

import com.techservices.digitalbanking.core.fineract.model.data.FineractPageResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetSavingsAccountsAccountIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PaymentDetailData;
import com.techservices.digitalbanking.core.fineract.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.fineract.service.AccountService;
import com.techservices.digitalbanking.core.service.StatementService;
import com.techservices.digitalbanking.walletaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.walletaccount.service.SavingsAccountStatementService;
import com.techservices.digitalbanking.walletaccount.service.SavingsAccountTransactionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SavingsAccountStatementServiceImpl implements SavingsAccountStatementService {
    private final AccountService accountService;
    private final StatementService statementService;
    private final SavingsAccountTransactionService savingsAccountTransactionService;

    @Override
    public void generateCsvStatement(StatementRequest request, HttpServletResponse response) throws IOException {
        // Retrieve account details
        GetSavingsAccountsAccountIdResponse accountData = accountService.retrieveSavingsAccount(request.getSavingsId(), false);

        // Retrieve transactions
        FineractPageResponse<SavingsAccountTransactionData> transactionResult =
                savingsAccountTransactionService.retrieveSavingsAccountTransactions(
                        request.getCustomerId(),
                        request.getStartDate().toString(),
                        request.getEndDate().toString(),
                        "yyyy-MM-dd",
                        request.getProductId(),
                        request.getLimit(),
                        request.getOffset(), null);

        List<SavingsAccountTransactionData> transactions = transactionResult.getPageItems();

        // Filter transactions if needed
        if (request.getTransactionType() != null && !request.getTransactionType().equals("ALL")) {
            transactions = transactions.stream()
                    .filter(t -> request.getTransactionType().equals(t.getActualTransactionType()))
                    .collect(Collectors.toList());
        }

        if (!request.getIncludeReversals()) {
            transactions = transactions.stream()
                    .filter(t -> !t.isReversed())
                    .collect(Collectors.toList());
        }

        // Set response headers
        String filename = String.format("statement_%s_%s_%s.csv",
                accountData.getAccountNo(),
                request.getStartDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                request.getEndDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        try (PrintWriter writer = response.getWriter()) {
            // Write header with account information
            writer.println("# ACCOUNT STATEMENT");
            writer.println("# Account Number: " + accountData.getAccountNo());
            writer.println("# Account Holder: " + accountData.getClientName());
            writer.println("# Statement Period: " + request.getStartDate() + " to " + request.getEndDate());
            writer.println("# Generated On: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("# Currency: " + (accountData.getCurrency() != null ? accountData.getCurrency().getCode() : ""));
            writer.println("# Opening Balance: " + getOpeningBalance(request.getSavingsId(), request.getStartDate()));
            writer.println("# Closing Balance: " + accountData.getAccountBalance());
            writer.println("#");

            // CSV Headers
            writer.println("Transaction Date,Value Date,Transaction ID,Reference Number,Description,Transaction Type,Debit Amount,Credit Amount,Running Balance,Status");

            // Write transactions
            for (SavingsAccountTransactionData transaction : transactions) {
                String transactionType = transaction.getActualTransactionType();
                BigDecimal debitAmount = "DEBIT".equals(transactionType) ? transaction.getAmount() : BigDecimal.ZERO;
                BigDecimal creditAmount = "CREDIT".equals(transactionType) ? transaction.getAmount() : BigDecimal.ZERO;

                writer.printf("%s,%s,%d,%s,\"%s\",%s,%s,%s,%s,%s%n",
                        formatDate(transaction.getSubmittedOnDate()),
                        formatDate(transaction.getDate()),
                        transaction.getId(),
                        StringUtils.defaultString(transaction.getRefNo(), ""),
                        escapeForCsv(getTransactionDescription(transaction)),
                        transactionType,
                        formatAmount(debitAmount),
                        formatAmount(creditAmount),
                        formatAmount(transaction.getRunningBalance()),
                        transaction.isReversed() ? "REVERSED" : "PROCESSED");
            }

            // Summary section
            writer.println("#");
            writer.println("# SUMMARY");
            writer.println("# Total Transactions: " + transactions.size());
            writer.println("# Total Debits: " + calculateTotalDebits(transactions));
            writer.println("# Total Credits: " + calculateTotalCredits(transactions));
        }
    }

    @Override
    public void generatePdfStatement(StatementRequest request, HttpServletResponse response) throws IOException {
        // Implementation for PDF generation using iText or similar library
        // This would create a professional PDF statement with bank letterhead, formatting, etc.
        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"statement_" + request.getSavingsId() + ".pdf\"");

        // Use StatementService to generate PDF
        byte[] pdfContent = this.statementService.generatePdfStatement(request);
        response.getOutputStream().write(pdfContent);
    }

    @Override
    public void generateExcelStatement(StatementRequest request, HttpServletResponse response) throws IOException {
        // Implementation for Excel generation using Apache POI
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"statement_" + request.getSavingsId() + ".xlsx\"");

        // Use StatementService to generate Excel
        byte[] excelContent = this.statementService.generateExcelStatement(request);
        response.getOutputStream().write(excelContent);
    }

    private String getTransactionDescription(SavingsAccountTransactionData transaction) {
        StringBuilder description = new StringBuilder();

        if (StringUtils.isNotBlank(transaction.getNarration())) {
            description.append(transaction.getNarration());
        } else if (StringUtils.isNotBlank(transaction.getNote())) {
            description.append(transaction.getNote());
        } else if (transaction.getTransactionType() != null) {
            description.append(transaction.getActualTransactionType());
        }

        // Add payment details if available
        if (transaction.getPaymentDetailData() != null) {
            PaymentDetailData paymentData = transaction.getPaymentDetailData();
            if (StringUtils.isNotBlank(paymentData.getReceiptNumber())) {
                description.append(" - Receipt: ").append(paymentData.getReceiptNumber());
            }
        }

        return description.toString();
    }

    private String formatDate(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
    }

    private String formatAmount(BigDecimal amount) {
        return amount != null ? amount.setScale(2, RoundingMode.HALF_UP).toString() : "0.00";
    }

    private String escapeForCsv(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"");
    }

    private BigDecimal getOpeningBalance(Long savingsId, LocalDate startDate) {
        // Calculate opening balance as of start date
        // This would typically involve querying transactions before the start date
        return savingsAccountTransactionService.getBalanceAsOfDate(savingsId, startDate.minusDays(1));
    }

    private BigDecimal calculateTotalDebits(List<SavingsAccountTransactionData> transactions) {
        return transactions.stream()
                .filter(t -> "DEBIT".equals(t.getActualTransactionType()))
                .map(SavingsAccountTransactionData::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalCredits(List<SavingsAccountTransactionData> transactions) {
        return transactions.stream()
                .filter(t -> "CREDIT".equals(t.getActualTransactionType()))
                .map(SavingsAccountTransactionData::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
