package com.techservices.digitalbanking.core.service;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.techservices.digitalbanking.core.configuration.BankConfigurationService;
import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.request.ReceiptRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.SavingsAccountTransactionData;
import com.techservices.digitalbanking.core.eBanking.service.AccountService;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.service.CustomerService;
import com.techservices.digitalbanking.walletaccount.service.WalletAccountTransactionService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@Slf4j
public class ReceiptService {
    private final AccountService accountService;
    private final CustomerService customerService;
    private final BankConfigurationService bankConfigurationService;
    private final WalletAccountTransactionService walletAccountTransactionService;
    private final SpringSecurityAuditorAware springSecurityAuditorAware;

    public void generatePdfReceipt(ReceiptRequest request, Long customerId, HttpServletResponse response) throws IOException {
        log.info("Generating PDF receipt for transaction: {} for customer: {}", request.getTransactionId(), customerId);

        try {
            byte[] pdfContent = generatePdfReceiptContent(request, customerId);

            String filename = String.format("receipt_%s_%s.pdf",
                    request.getTransactionId(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

            response.setContentType("application/pdf");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            response.setContentLength(pdfContent.length);

            response.getOutputStream().write(pdfContent);
            response.getOutputStream().flush();

            log.info("PDF receipt generated successfully for transaction: {}", request.getTransactionId());

        } catch (Exception e) {
            log.error("Error generating PDF receipt for transaction: {}", request.getTransactionId(), e);
            throw new IOException("Failed to generate PDF receipt", e);
        }
    }

    public void generateImageReceipt(ReceiptRequest request, Long customerId, HttpServletResponse response, String format) throws IOException {
        log.info("Generating {} receipt for transaction: {} for customer: {}", format, request.getTransactionId(), customerId);

        try {
            byte[] imageContent = generateImageReceiptContent(request, customerId, format);

            String filename = String.format("receipt_%s_%s.%s",
                    request.getTransactionId(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
                    format.toLowerCase());

            String contentType = format.equalsIgnoreCase("PNG") ? "image/png" : "image/jpeg";
            response.setContentType(contentType);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            response.setContentLength(imageContent.length);

            response.getOutputStream().write(imageContent);
            response.getOutputStream().flush();

            log.info("{} receipt generated successfully for transaction: {}", format, request.getTransactionId());

        } catch (Exception e) {
            log.error("Error generating {} receipt for transaction: {}", format, request.getTransactionId(), e);
            throw new IOException("Failed to generate image receipt", e);
        }
    }

    public byte[] generateReceiptAsByteArray(ReceiptRequest request, Long customerId, String format) throws IOException {
        if ("PDF".equalsIgnoreCase(format)) {
            return generatePdfReceiptContent(request, customerId);
        } else {
            return generateImageReceiptContent(request, customerId, format);
        }
    }

    private byte[] generatePdfReceiptContent(ReceiptRequest request, Long customerId) throws IOException {
        try {
            // Get customer and account data
            Customer foundCustomer = customerService.getCustomerById(customerId);
            String accountId = foundCustomer.getAccountId();
            AccountDto accountData = accountService.retrieveSavingsAccount(accountId);

            Document document = new Document(PageSize.A5, 36, 36, 36, 36);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, baos);

            document.open();

            // Add bank header
            addReceiptBankHeader(document);

            // Add receipt title
            addReceiptTitle(document);

            // Add transaction details
            addTransactionDetails(document, request, accountData, foundCustomer);

            // Add recipient details
            addRecipientDetails(document, request);

            // Add receipt footer
            addReceiptFooter(document);

            document.close();

            return baos.toByteArray();

        } catch (DocumentException e) {
            log.error("Error creating PDF receipt", e);
            throw new IOException("Failed to create PDF receipt", e);
        }
    }

    private byte[] generateImageReceiptContent(ReceiptRequest request, Long customerId, String format) throws IOException {
        try {
            // Get customer and account data
            Customer foundCustomer = customerService.getCustomerById(customerId);
            String accountId = foundCustomer.getAccountId();
            AccountDto accountData = accountService.retrieveSavingsAccount(accountId);

            int width = 600;
            int height = 800;
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // Enable antialiasing
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Fill background
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // Draw receipt content
            drawImageReceiptContent(g2d, request, accountData, width, foundCustomer);

            g2d.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, format.toLowerCase(), baos);

            return baos.toByteArray();

        } catch (Exception e) {
            log.error("Error creating image receipt", e);
            throw new IOException("Failed to create image receipt", e);
        }
    }

    private void addReceiptBankHeader(Document document) throws DocumentException {
        try {
            // Bank name
            Paragraph bankName = new Paragraph(bankConfigurationService.getBankName(),
                    new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY));
            bankName.setAlignment(Element.ALIGN_CENTER);
            document.add(bankName);

            // Bank address
            Paragraph bankAddress = new Paragraph(bankConfigurationService.getBankAddress(),
                    new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.GRAY));
            bankAddress.setAlignment(Element.ALIGN_CENTER);
            document.add(bankAddress);

            // Contact info
            Paragraph contactInfo = new Paragraph(
                    "Phone: " + bankConfigurationService.getBankSupportPhone() + " | Email: " + bankConfigurationService.getBankSupportEmail(),
                    new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.GRAY));
            contactInfo.setAlignment(Element.ALIGN_CENTER);
            contactInfo.setSpacingAfter(20);
            document.add(contactInfo);

        } catch (Exception e) {
            log.warn("Could not load bank configuration", e);
        }
    }

    private void addReceiptTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("TRANSACTION RECEIPT",
                new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        // Receipt number
        String receiptNumber = "RCP" + System.currentTimeMillis();
        Paragraph receiptNum = new Paragraph("Receipt No: " + receiptNumber,
                new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
        receiptNum.setAlignment(Element.ALIGN_CENTER);
        receiptNum.setSpacingAfter(15);
        document.add(receiptNum);
    }

    private void addTransactionDetails(Document document, ReceiptRequest request,
                                       AccountDto accountData,
                                       Customer foundCustomer) throws DocumentException {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingAfter(15);

        Font labelFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font valueFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        // Transaction details
        addReceiptTableRow(table, "Date & Time:", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), labelFont, valueFont);
        addReceiptTableRow(table, "Amount:", formatCurrency(request.getAmount()) + " " + request.getCurrency(), labelFont, valueFont);
        addReceiptTableRow(table, "Transaction Type:", "TRANSFER", labelFont, valueFont);
        addReceiptTableRow(table, "Status:", "SUCCESSFUL", labelFont, valueFont);
        addReceiptTableRow(table, "Reference:", request.getTransactionId(), labelFont, valueFont);

        // Sender details
        addReceiptTableRow(table, "From Account:", accountData.getAccountNumber(), labelFont, valueFont);
        addReceiptTableRow(table, "Account Holder:", foundCustomer.getFullName(), labelFont, valueFont);

        document.add(table);
    }

    private void addRecipientDetails(Document document, ReceiptRequest request) throws DocumentException {
        // Recipient section header
        Paragraph recipientHeader = new Paragraph("RECIPIENT DETAILS",
                new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        recipientHeader.setSpacingBefore(10);
        recipientHeader.setSpacingAfter(10);
        document.add(recipientHeader);

        PdfPTable recipientTable = new PdfPTable(2);
        recipientTable.setWidthPercentage(100);
        recipientTable.setSpacingAfter(15);

        Font labelFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font valueFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        addReceiptTableRow(recipientTable, "Bank Name:", request.getRecipientBankName(), labelFont, valueFont);
        addReceiptTableRow(recipientTable, "Account Number:", request.getRecipientAccountNumber(), labelFont, valueFont);
        addReceiptTableRow(recipientTable, "Account Name:", request.getRecipientAccountName(), labelFont, valueFont);

        document.add(recipientTable);
    }

    private void addReceiptFooter(Document document) throws DocumentException {
        // Add some spacing
        document.add(new Paragraph(" ", new Font(Font.FontFamily.HELVETICA, 10)));

        // Success message
        Paragraph successMsg = new Paragraph("✓ Transaction Completed Successfully",
                new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GREEN));
        successMsg.setAlignment(Element.ALIGN_CENTER);
        successMsg.setSpacingAfter(10);
        document.add(successMsg);

        // Footer text
        Paragraph footer = new Paragraph();
        footer.setSpacingBefore(20);

        Font footerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, BaseColor.GRAY);
        footer.add(new Phrase("This is a computer generated receipt and does not require signature.\n", footerFont));
        footer.add(new Phrase("Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n", footerFont));
        footer.add(new Phrase("For support, contact: " + bankConfigurationService.getBankSupportPhone(), footerFont));

        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }

    private void drawImageReceiptContent(Graphics2D g2d, ReceiptRequest request,
                                         AccountDto accountData,
                                         int width, Customer foundCustomer) {
        int y = 30;
        int padding = 40;

        // Bank name
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        g2d.setColor(Color.BLACK);
        drawCenteredText(g2d, bankConfigurationService.getBankName(), width, y);
        y += 35;

        // Receipt title
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        drawCenteredText(g2d, "TRANSACTION RECEIPT", width, y);
        y += 40;

        // Receipt number
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        g2d.setColor(Color.GRAY);
        String receiptNumber = "RCP" + System.currentTimeMillis();
        drawCenteredText(g2d, "Receipt No: " + receiptNumber, width, y);
        y += 40;

        // Transaction details
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        g2d.setColor(Color.BLACK);
        drawLeftText(g2d, "TRANSACTION DETAILS", padding, y);
        y += 25;

        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        y = drawDetailRow(g2d, "Date & Time:", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), padding, y, width);
        y = drawDetailRow(g2d, "Amount:", formatCurrency(request.getAmount()) + " " + request.getCurrency(), padding, y, width);
        y = drawDetailRow(g2d, "Status:", "SUCCESSFUL", padding, y, width);
        y = drawDetailRow(g2d, "From Account:", accountData.getAccountNumber(), padding, y, width);
        y = drawDetailRow(g2d, "Account Holder:", foundCustomer.getFullName(), padding, y, width);

        y += 20;

        // Recipient details
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        drawLeftText(g2d, "RECIPIENT DETAILS", padding, y);
        y += 25;

        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        y = drawDetailRow(g2d, "Bank Name:", request.getRecipientBankName(), padding, y, width);
        y = drawDetailRow(g2d, "Account Number:", request.getRecipientAccountNumber(), padding, y, width);
        y = drawDetailRow(g2d, "Account Name:", request.getRecipientAccountName(), padding, y, width);

        // Success message
        y += 40;
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        g2d.setColor(new Color(0, 128, 0));
        drawCenteredText(g2d, "✓ Transaction Completed Successfully", width, y);

        // Footer
        y += 60;
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));
        g2d.setColor(Color.GRAY);
        drawCenteredText(g2d, "This is a computer generated receipt", width, y);
        y += 15;
        drawCenteredText(g2d, "Generated on: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), width, y);
    }

    // Helper methods
    private void addReceiptTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(5);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(5);
        table.addCell(valueCell);
    }

    private void drawCenteredText(Graphics2D g2d, String text, int width, int y) {
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(text)) / 2;
        g2d.drawString(text, x, y);
    }

    private void drawLeftText(Graphics2D g2d, String text, int x, int y) {
        g2d.drawString(text, x, y);
    }

    private int drawDetailRow(Graphics2D g2d, String label, String value, int x, int y, int width) {
        g2d.setColor(Color.GRAY);
        g2d.drawString(label, x, y);
        g2d.setColor(Color.BLACK);
        g2d.drawString(value, x + 150, y);
        return y + 20;
    }

    private String formatCurrency(BigDecimal amount) {
        return String.format("%,.2f", amount);
    }

    private SavingsAccountTransactionData getTransactionById(Long transactionId) {
        try {
            Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
            return walletAccountTransactionService.retrieveSavingsAccountTransactionById(transactionId, customerId);
        } catch (Exception e) {
            log.warn("Could not retrieve transaction details for ID: {}", transactionId);
            return null;
        }
    }
}
