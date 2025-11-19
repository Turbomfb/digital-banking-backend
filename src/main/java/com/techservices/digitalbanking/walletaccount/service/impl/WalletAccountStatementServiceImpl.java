/* (C)2024 */
package com.techservices.digitalbanking.walletaccount.service.impl;

import static com.techservices.digitalbanking.core.util.AppUtil.DEFAULT_CURRENCY;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.domain.enums.TransactionType;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.core.service.StatementService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.walletaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountStatementService;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletAccountStatementServiceImpl implements WalletAccountStatementService {
  private final AccountService accountService;
  private final StatementService statementService;
  private final WalletAccountTransactionService walletAccountTransactionService;

  @Override
  public void generateCsvStatement(
      StatementRequest request, HttpServletResponse response, Customer customer)
      throws IOException {

    // Retrieve account details
    AccountDto accountData = accountService.retrieveSavingsAccount(request.getSavingsId());

    // Retrieve transactions
    BasePageResponse<TransactionDto> transactionResult =
        walletAccountTransactionService.retrieveSavingsAccountTransactions(
            request.getCustomerId(),
            request.getStartDate().toString(),
            request.getEndDate().toString(),
            request.getLimit(),
            TransactionType.ALL);

    List<TransactionDto> transactions = transactionResult.getData();

    // Filter transactions if needed
    if (request.getTransactionType() != null && !request.getTransactionType().equals("ALL")) {
      transactions =
          transactions.stream()
              .filter(t -> request.getTransactionType().equals(t.getTransactionType()))
              .collect(Collectors.toList());
    }

    // Set response headers
    String filename =
        String.format(
            "statement_%s_%s_%s.csv",
            accountData.getAccountNumber(),
            request.getStartDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
            request.getEndDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

    response.setContentType("text/csv; charset=UTF-8");
    response.setHeader(
        HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    try (PrintWriter writer = response.getWriter()) {
      // Write header with account information
      writer.println("# ACCOUNT STATEMENT");
      writer.println("# Account Number: " + accountData.getAccountNumber());
      writer.println("# Account Holder: " + customer.getFullName());
      writer.println(
          "# Statement Period: " + request.getStartDate() + " to " + request.getEndDate());
      writer.println(
          "# Generated On: "
              + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
      writer.println("# Currency: " + DEFAULT_CURRENCY);
      writer.println(
          "# Opening Balance: " + getOpeningBalance(customer.getId(), request.getStartDate()));
      writer.println("# Closing Balance: " + accountData.getAccountBalance());
      writer.println("#");

      // CSV Headers
      writer.println(
          "Transaction Date,Value Date,Transaction ID,Reference Number,Description,Transaction Type,Debit Amount,Credit Amount,Running Balance,Status");

      // Write transactions
      for (TransactionDto transaction : transactions) {
        String transactionType = transaction.getTransactionType();
        BigDecimal debitAmount =
            "DEBIT".equals(transactionType) ? transaction.getAmount() : BigDecimal.ZERO;
        BigDecimal creditAmount =
            "CREDIT".equals(transactionType) ? transaction.getAmount() : BigDecimal.ZERO;

        writer.printf(
            "%s,%s,%s,%s,\"%s\",%s,%s,%s,%s,%s%n",
            formatDate(LocalDate.from(transaction.getDate())),
            formatDate(LocalDate.from(transaction.getDate())),
            transaction.getId(),
            StringUtils.defaultString(transaction.getReference(), ""),
            escapeForCsv(getTransactionDescription(transaction)),
            transactionType,
            formatAmount(debitAmount),
            formatAmount(creditAmount),
            formatAmount(transaction.getRunningBalance()),
            "PROCESSED");
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
  public void generatePdfStatement(
      StatementRequest request, HttpServletResponse response, Customer customer)
      throws IOException {

    // Implementation for PDF generation using iText or similar library
    // This would create a professional PDF statement with bank letterhead,
    // formatting, etc.
    response.setContentType("application/pdf");
    response.setHeader(
        HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"statement_" + request.getSavingsId() + ".pdf\"");

    // Use StatementService to generate PDF
    byte[] pdfContent = this.statementService.generatePdfStatement(request, customer);
    response.getOutputStream().write(pdfContent);
  }

  @Override
  public void generateExcelStatement(
      StatementRequest request, HttpServletResponse response, Customer customer)
      throws IOException {

    // Implementation for Excel generation using Apache POI
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setHeader(
        HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"statement_" + request.getSavingsId() + ".xlsx\"");

    // Use StatementService to generate Excel
    byte[] excelContent = this.statementService.generateExcelStatement(request, customer);
    response.getOutputStream().write(excelContent);
  }

  private String getTransactionDescription(TransactionDto transaction) {

    StringBuilder description = new StringBuilder();

    if (StringUtils.isNotBlank(transaction.getNarration())) {
      description.append(transaction.getNarration());
    } else if (StringUtils.isNotBlank(transaction.getNarration())) {
      description.append(transaction.getNarration());
    } else if (transaction.getTransactionType() != null) {
      description.append(transaction.getTransactionType());
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
    return walletAccountTransactionService.getBalanceAsOfDate(savingsId, startDate.minusDays(1));
  }

  private BigDecimal calculateTotalDebits(List<TransactionDto> transactions) {

    return transactions.stream()
        .filter(t -> "DEBIT".equals(t.getTransactionType()))
        .map(TransactionDto::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private BigDecimal calculateTotalCredits(List<TransactionDto> transactions) {

    return transactions.stream()
        .filter(t -> "CREDIT".equals(t.getTransactionType()))
        .map(TransactionDto::getAmount)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
