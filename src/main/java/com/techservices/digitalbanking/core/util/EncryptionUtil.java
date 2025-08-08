package com.techservices.digitalbanking.core.util;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Slf4j
public class EncryptionUtil {

    public static String encryptAesPublicKeyXml(String data, String encodedXmlKey) throws Exception {
        if (data == null || data.isEmpty() || encodedXmlKey == null || encodedXmlKey.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: data or encodedXmlKey is null/empty");
        }
        ParsedKey parsedKey = parseXmlKey(encodedXmlKey);
        String xmlKey = cleanXmlKey(parsedKey.xmlKey);

        log.info("✅ Key parsing successful");
        log.debug("🔍 Final XML key preview: {}", xmlKey.substring(0, Math.min(200, xmlKey.length())) + "...");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);

        Document doc = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xmlKey.getBytes(StandardCharsets.UTF_8)));
        log.info("✅ XML parsing successful");

        String modulusB64 = doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponentB64 = doc.getElementsByTagName("Exponent").item(0).getTextContent();

        log.debug("🔢 Modulus length: {}, Exponent length: {}", modulusB64.length(), exponentB64.length());

        BigInteger modulus = new BigInteger(1, Base64.getDecoder().decode(modulusB64));
        BigInteger exponent = new BigInteger(1, Base64.getDecoder().decode(exponentB64));
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        byte[] aesKey = new byte[32];
        new SecureRandom().nextBytes(aesKey);
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec aesKeySpec = new SecretKeySpec(aesKey, "AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKeySpec, new IvParameterSpec(iv));
        byte[] encryptedData = aesCipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedAesKey = rsaCipher.doFinal(aesKey);

        return Base64.getEncoder().encodeToString(encryptedAesKey) + "." +
                Base64.getEncoder().encodeToString(iv) + "." +
                Base64.getEncoder().encodeToString(encryptedData);
    }

    private static String cleanXmlKey(String xmlKey) {
        if (xmlKey == null || xmlKey.isEmpty()) {
            throw new IllegalArgumentException("XML key is null or empty");
        }
        if (xmlKey.startsWith("\uFEFF")) {
            xmlKey = xmlKey.substring(1);
        }
        xmlKey = xmlKey.trim();
        log.debug("🧹 Cleaned XML Key: {}", xmlKey);
        return xmlKey;
    }

    private static ParsedKey parseXmlKey(String encodedKey) {
        try {
            int outerSep = encodedKey.indexOf("!");
            if (outerSep <= 0) {
                throw new IllegalArgumentException("Invalid key format. Missing '!' in outer structure.");
            }
            int outerKeySize = Integer.parseInt(encodedKey.substring(0, outerSep));
            String base64Content = encodedKey.substring(outerSep + 1);
            log.info("📦 Outer key size: {}", outerKeySize);
            log.info("📦 Base64 content to decode: {}", base64Content.substring(0, Math.min(50, base64Content.length())) + "...");

            String decodedContent = new String(Base64.getDecoder().decode(base64Content), StandardCharsets.UTF_8);

            log.debug("📦 Decoded content: {}", decodedContent.substring(0, Math.min(100, decodedContent.length())) + "...");

            String xmlKey = decodedContent;
            int innerKeySize = outerKeySize;

            if (decodedContent.matches("^\\d+!.*")) {
                int innerSep = decodedContent.indexOf("!");
                if (innerSep > 0) {
                    innerKeySize = Integer.parseInt(decodedContent.substring(0, innerSep));
                    xmlKey = decodedContent.substring(innerSep + 1);
                    log.debug("📦 Found inner key size: {}", innerKeySize);
                }
            }

            log.debug("📦 Final XML content to parse: {}", xmlKey.substring(0, Math.min(100, xmlKey.length())) + "...");

            return new ParsedKey(innerKeySize, xmlKey);
        } catch (Exception e) {
            log.error("Failed to parse key: {}", e.getMessage());
            throw new IllegalArgumentException("Failed to parse XML key", e);
        }
    }

    private static class ParsedKey {
        final int keySize;
        final String xmlKey;

        ParsedKey(int keySize, String xmlKey) {
            this.keySize = keySize;
            this.xmlKey = xmlKey;
        }
    }
}
