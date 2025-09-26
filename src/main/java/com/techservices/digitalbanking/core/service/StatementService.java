package com.techservices.digitalbanking.core.service;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.TransactionDto;
import com.techservices.digitalbanking.core.eBanking.model.response.PaymentDetailData;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.techservices.digitalbanking.core.configuration.BankConfigurationService;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.walletaccount.domain.request.StatementRequest;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.techservices.digitalbanking.core.util.AppUtil.DEFAULT_CURRENCY;

@Service
@AllArgsConstructor
@Slf4j
public class StatementService {

        private final WalletAccountTransactionService walletAccountTransactionService;

        private final AccountService accountService;

        private final BankConfigurationService bankConfigurationService;


        public byte[] generatePdfStatement(StatementRequest request, Customer customer) throws IOException {
            log.info("Generating PDF statement for account: {}", request.getSavingsId());

            try {
                AccountDto accountData = accountService.retrieveSavingsAccount(request.getSavingsId());
                List<TransactionDto> transactions = getFilteredTransactions(request);

                Document document = new Document(PageSize.A4, 36, 36, 54, 36);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter writer = PdfWriter.getInstance(document, baos);

                // Add header and footer
                StatementHeaderFooter headerFooter = new StatementHeaderFooter(bankConfigurationService.getBankName(), bankConfigurationService.getBankAddress());
                writer.setPageEvent(headerFooter);

                document.open();

                // Add bank logo and header
                addBankHeader(document);

                // Add account information
                addAccountInformation(document, accountData, request, customer);

                // Add statement summary
                addStatementSummary(document, accountData, transactions, request);

                // Add transaction table
                addTransactionTable(document, transactions);

                // Add footer information
                addStatementFooter(document);

                document.close();

                log.info("PDF statement generated successfully for account: {}", request.getSavingsId());
                return baos.toByteArray();

            } catch (DocumentException e) {
                log.error("Error generating PDF statement", e);
                throw new IOException("Failed to generate PDF statement", e);
            }
        }

        public byte[] generateExcelStatement(StatementRequest request, Customer customer) throws IOException {
            log.info("Generating Excel statement for account: {}", request.getSavingsId());

            try (Workbook workbook = new XSSFWorkbook()) {
                // Retrieve data
                AccountDto accountData = accountService.retrieveSavingsAccount(request.getSavingsId());
                List<TransactionDto> transactions = getFilteredTransactions(request);

                // Create main statement sheet
                Sheet statementSheet = workbook.createSheet("Account Statement");

                // Create styles
                CellStyle headerStyle = createHeaderStyle(workbook);
                CellStyle dataStyle = createDataStyle(workbook);
                CellStyle currencyStyle = createCurrencyStyle(workbook);
                CellStyle dateStyle = createDateStyle(workbook);

                int rowNum = 0;

                // Add bank header
                rowNum = addExcelBankHeader(statementSheet, rowNum, headerStyle);

                // Add account information
                rowNum = addExcelAccountInfo(statementSheet, accountData, request, rowNum, headerStyle, dataStyle, customer);

                // Add statement summary
                rowNum = addExcelStatementSummary(statementSheet, accountData, transactions, request, rowNum, headerStyle, currencyStyle);

                // Add transaction headers
                rowNum = addExcelTransactionHeaders(statementSheet, rowNum, headerStyle);

                // Add transactions
                addExcelTransactionData(statementSheet, transactions, rowNum, dataStyle, currencyStyle, dateStyle);

                // Auto-size columns
                for (int i = 0; i < 10; i++) {
                    statementSheet.autoSizeColumn(i);
                }

                // Create summary sheet
                createSummarySheet(workbook, transactions);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                workbook.write(baos);

                log.info("Excel statement generated successfully for account: {}", request.getSavingsId());
                return baos.toByteArray();

            } catch (Exception e) {
                log.error("Error generating Excel statement", e);
                throw new IOException("Failed to generate Excel statement", e);
            }
        }

        private List<TransactionDto> getFilteredTransactions(StatementRequest request) {
            // Retrieve transactions
            BasePageResponse<TransactionDto> transactionResult =
                    walletAccountTransactionService.retrieveSavingsAccountTransactions(
                            request.getCustomerId(),
                            request.getStartDate().toString(),
                            request.getEndDate().toString(),
                            request.getLimit());

            List<TransactionDto> transactions = transactionResult.getData();

            // Apply filters
            if (request.getTransactionType() != null && !request.getTransactionType().equals("ALL")) {
                transactions = transactions.stream()
                        .filter(t -> request.getTransactionType().equals(t.getTransactionType()))
                        .collect(Collectors.toList());
            }

            return transactions;
        }

        // PDF Generation Methods
        private void addBankHeader(Document document) throws DocumentException {
            try {
                // Load bank logo
                String logoUrl = bankConfigurationService.getBankLogoUrl();
                Image bankLogo = Image.getInstance(logoUrl);
                bankLogo.scaleToFit(50, 50); // Adjust logo size
                bankLogo.setAlignment(Element.ALIGN_LEFT);
                document.add(bankLogo);

                // Bank name and address
                Paragraph bankDetails = new Paragraph();
                bankDetails.setAlignment(Element.ALIGN_CENTER);

                Font bankNameFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
                bankDetails.add(new Phrase(bankConfigurationService.getBankName() + "\n", bankNameFont));

                Font addressFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.GRAY);
                bankDetails.add(new Phrase(bankConfigurationService.getBankAddress() + "\n", addressFont));
                bankDetails.add(new Phrase("Phone: " + bankConfigurationService.getBankSupportPhone() + " | Email: " + bankConfigurationService.getBankSupportEmail() + "\n", addressFont));

                document.add(bankDetails);

                // Statement title
                Paragraph title = new Paragraph("ACCOUNT STATEMENT",
                        new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20);
                document.add(title);

            } catch (Exception e) {
                log.warn("Failed to load bank logo from URL: {}", bankConfigurationService.getBankLogoUrl(), e);
            }
        }

        private void addAccountInformation(Document document, AccountDto accountData,
                                           StatementRequest request, Customer customer) throws DocumentException {
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingAfter(15);

            Font labelFont = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
            Font valueFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL);

            // Account information
            addTableCell(table, "Account Number:", labelFont);
            addTableCell(table, accountData.getAccountNumber(), valueFont);
            addTableCell(table, "Account Type:", labelFont);
            addTableCell(table, accountData.getProductName(), valueFont);

            addTableCell(table, "Account Holder:", labelFont);
            addTableCell(table, customer.getFullName(), valueFont);
            addTableCell(table, "Statement Period:", labelFont);
            addTableCell(table, formatDateRange(request.getStartDate(), request.getEndDate()), valueFont);

            addTableCell(table, "Generated On:", labelFont);
            addTableCell(table, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), valueFont);
            addTableCell(table, "Currency:", labelFont);
            addTableCell(table, DEFAULT_CURRENCY, valueFont);

            document.add(table);
        }

        private void addStatementSummary(Document document, AccountDto accountData,
                                         List<TransactionDto> transactions,
                                         StatementRequest request) throws DocumentException {

            BigDecimal openingBalance = getOpeningBalance(request.getCustomerId(), request.getStartDate());
            BigDecimal totalCredits = calculateTotalCredits(transactions);
            BigDecimal totalDebits = calculateTotalDebits(transactions);
            BigDecimal closingBalance = accountData.getAccountBalance();

            PdfPTable summaryTable = new PdfPTable(2);
            summaryTable.setWidthPercentage(60);
            summaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
            summaryTable.setSpacingAfter(20);

            Font summaryFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

            addSummaryRow(summaryTable, "Opening Balance:", formatCurrency(openingBalance), summaryFont);
            addSummaryRow(summaryTable, "Total Credits:", formatCurrency(totalCredits), summaryFont);
            addSummaryRow(summaryTable, "Total Debits:", formatCurrency(totalDebits), summaryFont);
            addSummaryRow(summaryTable, "Closing Balance:", formatCurrency(closingBalance), summaryFont);
            addSummaryRow(summaryTable, "Available Balance:", formatCurrency(accountData.getAccountBalance()), summaryFont);

            document.add(summaryTable);
        }

        private void addTransactionTable(Document document, List<TransactionDto> transactions)
                throws DocumentException {

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{12, 12, 15, 30, 12, 12, 15});

            Font headerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.WHITE);
            Font dataFont = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL);

            // Headers
            addTransactionHeader(table, "Date", headerFont);
            addTransactionHeader(table, "Value Date", headerFont);
            addTransactionHeader(table, "Reference", headerFont);
            addTransactionHeader(table, "Description", headerFont);
            addTransactionHeader(table, "Debit", headerFont);
            addTransactionHeader(table, "Credit", headerFont);
            addTransactionHeader(table, "Balance", headerFont);

            // Transaction data
            for (TransactionDto transaction : transactions) {
                String transactionType = transaction.getTransactionType();
                BigDecimal debitAmount = "DEBIT".equals(transactionType) ? transaction.getAmount() : null;
                BigDecimal creditAmount = "CREDIT".equals(transactionType) ? transaction.getAmount() : null;

                addTransactionCell(table, formatDate(LocalDate.from(transaction.getDate())), dataFont);
                addTransactionCell(table, formatDate(LocalDate.from(transaction.getDate())), dataFont);
                addTransactionCell(table, transaction.getReference() != null ? transaction.getReference() : "", dataFont);
                addTransactionCell(table, getTransactionDescription(transaction), dataFont);
                addTransactionCell(table, debitAmount != null ? formatCurrency(debitAmount) : "", dataFont);
                addTransactionCell(table, creditAmount != null ? formatCurrency(creditAmount) : "", dataFont);
                addTransactionCell(table, formatCurrency(transaction.getRunningBalance()), dataFont);
            }

            document.add(table);
        }

        private void addStatementFooter(Document document) throws DocumentException {
            Paragraph footer = new Paragraph();
            footer.setSpacingBefore(30);

            Font footerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.GRAY);
            footer.add(new Phrase("This is a computer generated statement and does not require signature.\n", footerFont));
            footer.add(new Phrase("For any queries, please contact us at " + bankConfigurationService.getBankSupportPhone() + " or " + bankConfigurationService.getBankSupportEmail() + "\n", footerFont));

            document.add(footer);
        }

        // Excel Generation Methods
        private int addExcelBankHeader(Sheet sheet, int startRow, CellStyle headerStyle) {
            Row row = sheet.createRow(startRow++);
            Cell cell = row.createCell(0);
            cell.setCellValue(bankConfigurationService.getBankName());
            cell.setCellStyle(headerStyle);

            row = sheet.createRow(startRow++);
            cell = row.createCell(0);
            cell.setCellValue("ACCOUNT STATEMENT");
            cell.setCellStyle(headerStyle);

            return startRow + 1;
        }

        private int addExcelAccountInfo(Sheet sheet, AccountDto accountData, StatementRequest request,
                                        int startRow, CellStyle headerStyle, CellStyle dataStyle, Customer customer) {

            // Account Number
            Row row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Account Number:");
            row.getCell(0).setCellStyle(headerStyle);
            row.createCell(1).setCellValue(accountData.getAccountNumber());
            row.getCell(1).setCellStyle(dataStyle);

            // Account Holder
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Account Holder:");
            row.getCell(0).setCellStyle(headerStyle);
            row.createCell(1).setCellValue(customer.getFullName());
            row.getCell(1).setCellStyle(dataStyle);

            // Account Type
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Account Type:");
            row.getCell(0).setCellStyle(headerStyle);
            row.createCell(1).setCellValue(accountData.getProductName());
            row.getCell(1).setCellStyle(dataStyle);

            // Statement Period
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Statement Period:");
            row.getCell(0).setCellStyle(headerStyle);
            row.createCell(1).setCellValue(formatDateRange(request.getStartDate(), request.getEndDate()));
            row.getCell(1).setCellStyle(dataStyle);

            // Currency
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Currency:");
            row.getCell(0).setCellStyle(headerStyle);
            row.createCell(1).setCellValue(DEFAULT_CURRENCY);
            row.getCell(1).setCellStyle(dataStyle);

            return startRow + 1;
        }

        private int addExcelStatementSummary(Sheet sheet, AccountDto accountData,
                                             List<TransactionDto> transactions,
                                             StatementRequest request, int startRow,
                                             CellStyle headerStyle, CellStyle currencyStyle) {

            BigDecimal openingBalance = getOpeningBalance(request.getCustomerId(), request.getStartDate());
            BigDecimal totalCredits = calculateTotalCredits(transactions);
            BigDecimal totalDebits = calculateTotalDebits(transactions);
            BigDecimal closingBalance = accountData.getAccountBalance();

            // Summary header
            Row row = sheet.createRow(startRow++);
            Cell cell = row.createCell(0);
            cell.setCellValue("SUMMARY");
            cell.setCellStyle(headerStyle);

            // Opening Balance
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Opening Balance:");
            row.getCell(0).setCellStyle(headerStyle);
            cell = row.createCell(1);
            cell.setCellValue(openingBalance.doubleValue());
            cell.setCellStyle(currencyStyle);

            // Total Credits
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Total Credits:");
            row.getCell(0).setCellStyle(headerStyle);
            cell = row.createCell(1);
            cell.setCellValue(totalCredits.doubleValue());
            cell.setCellStyle(currencyStyle);

            // Total Debits
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Total Debits:");
            row.getCell(0).setCellStyle(headerStyle);
            cell = row.createCell(1);
            cell.setCellValue(totalDebits.doubleValue());
            cell.setCellStyle(currencyStyle);

            // Closing Balance
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Closing Balance:");
            row.getCell(0).setCellStyle(headerStyle);
            cell = row.createCell(1);
            cell.setCellValue(closingBalance.doubleValue());
            cell.setCellStyle(currencyStyle);

            // Available Balance
            row = sheet.createRow(startRow++);
            row.createCell(0).setCellValue("Available Balance:");
            row.getCell(0).setCellStyle(headerStyle);
            cell = row.createCell(1);
            cell.setCellValue(accountData.getAccountBalance().doubleValue());
            cell.setCellStyle(currencyStyle);

            return startRow + 2;
        }

        private int addExcelTransactionHeaders(Sheet sheet, int startRow, CellStyle headerStyle) {
            Row headerRow = sheet.createRow(startRow);

            String[] headers = {"Transaction Date", "Value Date", "Reference", "Description",
                    "Transaction Type", "Debit Amount", "Credit Amount", "Running Balance", "Status"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            return startRow + 1;
        }

        private void addExcelTransactionData(Sheet sheet, List<TransactionDto> transactions,
                                             int startRow, CellStyle dataStyle, CellStyle currencyStyle, CellStyle dateStyle) {

            for (int i = 0; i < transactions.size(); i++) {
                TransactionDto transaction = transactions.get(i);
                Row row = sheet.createRow(startRow + i);

                String transactionType = transaction.getTransactionType();
                BigDecimal debitAmount = "DEBIT".equals(transactionType) ? transaction.getAmount() : BigDecimal.ZERO;
                BigDecimal creditAmount = "CREDIT".equals(transactionType) ? transaction.getAmount() : BigDecimal.ZERO;

                // Transaction Date
                Cell cell = row.createCell(0);
                if (transaction.getDate() != null) {
                    cell.setCellValue(java.sql.Date.valueOf(String.valueOf(transaction.getDate())));
                    cell.setCellStyle(dateStyle);
                }

                // Value Date
                cell = row.createCell(1);
                if (transaction.getDate() != null) {
                    cell.setCellValue(java.sql.Date.valueOf(String.valueOf(transaction.getDate())));
                    cell.setCellStyle(dateStyle);
                }

                // Reference
                cell = row.createCell(2);
                cell.setCellValue(transaction.getReference() != null ? transaction.getReference() : "");
                cell.setCellStyle(dataStyle);

                // Description
                cell = row.createCell(3);
                cell.setCellValue(getTransactionDescription(transaction));
                cell.setCellStyle(dataStyle);

                // Transaction Type
                cell = row.createCell(4);
                cell.setCellValue(transactionType);
                cell.setCellStyle(dataStyle);

                // Debit Amount
                cell = row.createCell(5);
                if (debitAmount.compareTo(BigDecimal.ZERO) > 0) {
                    cell.setCellValue(debitAmount.doubleValue());
                    cell.setCellStyle(currencyStyle);
                }

                // Credit Amount
                cell = row.createCell(6);
                if (creditAmount.compareTo(BigDecimal.ZERO) > 0) {
                    cell.setCellValue(creditAmount.doubleValue());
                    cell.setCellStyle(currencyStyle);
                }

                // Running Balance
                cell = row.createCell(7);
                cell.setCellValue(transaction.getRunningBalance().doubleValue());
                cell.setCellStyle(currencyStyle);

                // Status
                cell = row.createCell(8);
                cell.setCellValue("PROCESSED");
                cell.setCellStyle(dataStyle);
            }
        }

        private void createSummarySheet(Workbook workbook,
                                        List<TransactionDto> transactions) {
            Sheet summarySheet = workbook.createSheet("Summary");

            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle currencyStyle = createCurrencyStyle(workbook);

            int rowNum = 0;

            // Summary statistics
            Row row = summarySheet.createRow(rowNum++);
            row.createCell(0).setCellValue("TRANSACTION SUMMARY");
            row.getCell(0).setCellStyle(headerStyle);

            rowNum++; // Empty row

            // Total transactions
            row = summarySheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Total Transactions:");
            row.createCell(1).setCellValue(transactions.size());

            // Credit transactions count
            long creditCount = transactions.stream()
                    .filter(t -> "CREDIT".equals(t.getTransactionType()))
                    .count();
            row = summarySheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Credit Transactions:");
            row.createCell(1).setCellValue(creditCount);

            // Debit transactions count
            long debitCount = transactions.stream()
                    .filter(t -> "DEBIT".equals(t.getTransactionType()))
                    .count();
            row = summarySheet.createRow(rowNum++);
            row.createCell(0).setCellValue("Debit Transactions:");
            row.createCell(1).setCellValue(debitCount);

            // Average transaction amount
            Optional<BigDecimal> avgAmountOptional = transactions.isEmpty() ?
                    Optional.empty() :
                    Optional.of(transactions.stream()
                            .map(TransactionDto::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .divide(BigDecimal.valueOf(transactions.size()), 2, RoundingMode.HALF_UP));

            BigDecimal avgAmount = avgAmountOptional.orElse(BigDecimal.ZERO);
            row = summarySheet.createRow(++rowNum);
            row.createCell(0).setCellValue("Average Transaction Amount:");
            Cell avgCell = row.createCell(1);
            avgCell.setCellValue(avgAmount.doubleValue());
            avgCell.setCellStyle(currencyStyle);

            // Auto-size columns
            summarySheet.autoSizeColumn(0);
            summarySheet.autoSizeColumn(1);
        }

        // Style creation methods
        private CellStyle createHeaderStyle(Workbook workbook) {
            CellStyle style = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            return style;
        }

        private CellStyle createDataStyle(Workbook workbook) {
            CellStyle style = workbook.createCellStyle();
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            return style;
        }

        private CellStyle createCurrencyStyle(Workbook workbook) {
            CellStyle style = createDataStyle(workbook);
            DataFormat format = workbook.createDataFormat();
            style.setDataFormat(format.getFormat("#,##0.00"));
            return style;
        }

        private CellStyle createDateStyle(Workbook workbook) {
            CellStyle style = createDataStyle(workbook);
            DataFormat format = workbook.createDataFormat();
            style.setDataFormat(format.getFormat("dd/mm/yyyy"));
            return style;
        }

        // Utility methods
        private void addTableCell(PdfPTable table, String text, Font font) {
            PdfPCell cell = new PdfPCell(new Phrase(text, font));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setPadding(5);
            table.addCell(cell);
        }

        private void addSummaryRow(PdfPTable table, String label, String value, Font font) {
            PdfPCell labelCell = new PdfPCell(new Phrase(label, font));
            labelCell.setBorder(Rectangle.NO_BORDER);
            labelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(labelCell);

            PdfPCell valueCell = new PdfPCell(new Phrase(value, font));
            valueCell.setBorder(Rectangle.NO_BORDER);
            valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(valueCell);
        }

        private void addTransactionHeader(PdfPTable table, String text, Font font) {
            PdfPCell cell = new PdfPCell(new Phrase(text, font));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(8);
            table.addCell(cell);
        }

        private void addTransactionCell(PdfPTable table, String text, Font font) {
            PdfPCell cell = new PdfPCell(new Phrase(text, font));
            cell.setPadding(5);
            cell.setBorderWidth(0.5f);
            table.addCell(cell);
        }

        private String getTransactionDescription(TransactionDto transaction) {
            StringBuilder description = new StringBuilder();

            if (transaction.getNarration() != null && !transaction.getNarration().trim().isEmpty()) {
                description.append(transaction.getNarration());
            } else if (transaction.getTransactionType() != null) {
                description.append(transaction.getTransactionType());
            }
            return description.toString();
        }

        private String formatDate(LocalDate date) {
            return date != null ? date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "";
        }

        private String formatDateRange(LocalDate startDate, LocalDate endDate) {
            return formatDate(startDate) + " to " + formatDate(endDate);
        }

        private String formatCurrency(BigDecimal amount) {
            return amount != null ? String.format("%,.2f", amount) : "0.00";
        }

        private BigDecimal getOpeningBalance(Long customerId, LocalDate startDate) {
            try {
                return walletAccountTransactionService.getBalanceAsOfDate(customerId, startDate.minusDays(1));
            } catch (Exception e) {
                log.warn("Could not retrieve opening balance for customer: {}", customerId);
                return BigDecimal.ZERO;
            }
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

        // Inner class for PDF header/footer
        private static class StatementHeaderFooter extends PdfPageEventHelper {
            private String bankName;
            private String bankAddress;

            public StatementHeaderFooter(String bankName, String bankAddress) {
                this.bankName = bankName;
                this.bankAddress = bankAddress;
            }

            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                PdfContentByte cb = writer.getDirectContent();

                // Add page number
                Phrase pagePhrase = new Phrase("Page " + writer.getPageNumber(),
                        new Font(Font.FontFamily.HELVETICA, 8));
                ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, pagePhrase,
                        document.right(), document.bottom() - 10, 0);

                Phrase timestampPhrase = new Phrase("Generated on: " +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                        new Font(Font.FontFamily.HELVETICA, 8));
                ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, timestampPhrase,
                        document.left(), document.bottom() - 10, 0);
            }
        }
}
