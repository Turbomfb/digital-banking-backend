/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XmlKeyGenerator {

  /**
   * Generates a sample RSA public key in the XML format expected by your EncryptionUtil This is for
   * testing purposes - replace with your actual RSA public key
   */
  public static String generateSampleEncodedXmlKey() throws Exception {

    // Generate a sample RSA key pair for demonstration
    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
    keyGen.initialize(2048);
    KeyPair keyPair = keyGen.generateKeyPair();
    RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

    // Extract modulus and exponent
    BigInteger modulus = publicKey.getModulus();
    BigInteger exponent = publicKey.getPublicExponent();

    // Convert to Base64 - handle potential negative values properly
    String modulusB64 = Base64.getEncoder().encodeToString(toUnsignedByteArray(modulus));
    String exponentB64 = Base64.getEncoder().encodeToString(toUnsignedByteArray(exponent));

    // Create XML format
    String xmlKey =
        String.format(
            "<RSAKeyValue>"
                + "<Modulus>%s</Modulus>"
                + "<Exponent>%s</Exponent>"
                + "</RSAKeyValue>",
            modulusB64, exponentB64);

    // Base64 encode the XML
    String xmlKeyB64 = Base64.getEncoder().encodeToString(xmlKey.getBytes(StandardCharsets.UTF_8));

    // Format as expected: {keySize}!{base64EncodedXmlKey}
    String encodedXmlKey = "2048!" + xmlKeyB64;

    log.info("Generated sample encodedXmlKey: {}", encodedXmlKey);
    return encodedXmlKey;
  }

  /**
   * Converts existing RSA public key components to the expected format FIXED: Removed premature
   * Base64 validation that was causing the error
   */
  public static String createEncodedXmlKey(String modulusB64, String exponentB64, int keySize) {

    try {
      // Create XML format without validating individual components
      // The components should already be valid Base64 when coming from a proper
      // source
      String xmlKey =
          String.format(
              "<RSAKeyValue>"
                  + "<Modulus>%s</Modulus>"
                  + "<Exponent>%s</Exponent>"
                  + "</RSAKeyValue>",
              modulusB64, exponentB64);

      log.info("Created XML: {}", xmlKey);

      // Base64 encode the XML
      String xmlKeyB64 =
          Base64.getEncoder().encodeToString(xmlKey.getBytes(StandardCharsets.UTF_8));

      // Format as expected: {keySize}!{base64EncodedXmlKey}
      String encodedXmlKey = keySize + "!" + xmlKeyB64;

      log.info("Created encodedXmlKey: {}", encodedXmlKey);
      log.info("Base64 XML length: {}", xmlKeyB64.length());

      // Validate the final result by trying to decode it
      validateEncodedXmlKey(encodedXmlKey);

      return encodedXmlKey;

    } catch (Exception e) {
      log.error("Failed to create encoded XML key: {}", e.getMessage());
      throw new RuntimeException("Failed to create encoded XML key", e);
    }
  }

  /** Validates the complete encoded XML key instead of individual components */
  private static void validateEncodedXmlKey(String encodedXmlKey) {

    try {
      int sep = encodedXmlKey.indexOf("!");
      if (sep <= 0) {
        throw new IllegalArgumentException("Invalid key format - missing separator");
      }

      String base64Xml = encodedXmlKey.substring(sep + 1);

      // Try to decode the complete XML
      String xmlKey = new String(Base64.getDecoder().decode(base64Xml), StandardCharsets.UTF_8);
      log.info("✅ Validation successful - decoded XML: {}", xmlKey);

    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid encoded XML key: " + e.getMessage());
    }
  }

  /** Extracts and displays the components of an existing encodedXmlKey */
  public static void analyzeEncodedXmlKey(String encodedXmlKey) {

    try {
      int sep = encodedXmlKey.indexOf("!");
      if (sep <= 0) {
        log.error("Invalid key format - missing separator");
        return;
      }

      int keySize = Integer.parseInt(encodedXmlKey.substring(0, sep));
      String base64Xml = encodedXmlKey.substring(sep + 1);

      log.info("🔍 Analyzing encodedXmlKey:");
      log.info("  Key Size: {}", keySize);
      log.info("  Base64 XML Length: {}", base64Xml.length());
      log.info("  Base64 XML modulo 4: {}", base64Xml.length() % 4);

      // Check for potential issues with Base64 string
      if (base64Xml.length() % 4 != 0) {
        log.warn(
            "  ⚠️  Base64 string length is not a multiple of 4! This will cause decoding issues.");
      }

      // Check for invalid characters
      if (!base64Xml.matches("[A-Za-z0-9+/=]*")) {
        log.warn("  ⚠️  Base64 string contains invalid characters!");
      }

      // Try to decode the XML
      try {
        String xmlKey = new String(Base64.getDecoder().decode(base64Xml), StandardCharsets.UTF_8);
        log.info("  ✅ Successfully decoded XML: {}", xmlKey);

        // Extract and validate individual components from the decoded XML
        extractAndValidateComponents(xmlKey);

      } catch (IllegalArgumentException e) {
        log.error("  ❌ Failed to decode Base64: {}", e.getMessage());

        // Try with padding fix
        String fixedBase64 = fixBase64Padding(base64Xml);
        log.info("  🔧 Attempting with padding fix: {}", fixedBase64);

        try {
          String xmlKey =
              new String(Base64.getDecoder().decode(fixedBase64), StandardCharsets.UTF_8);
          log.info("  ✅ Successfully decoded XML with padding fix: {}", xmlKey);
        } catch (IllegalArgumentException e2) {
          log.error("  ❌ Still failed after padding fix: {}", e2.getMessage());
        }
      }

    } catch (Exception e) {
      log.error("Failed to analyze encoded XML key: {}", e.getMessage());
    }
  }

  /** Extracts and validates individual components from decoded XML */
  private static void extractAndValidateComponents(String xmlKey) {

    try {
      // Extract modulus
      String modulusStart = "<Modulus>";
      String modulusEnd = "</Modulus>";
      int modulusStartIdx = xmlKey.indexOf(modulusStart);
      int modulusEndIdx = xmlKey.indexOf(modulusEnd);

      if (modulusStartIdx != -1 && modulusEndIdx != -1) {
        String modulus = xmlKey.substring(modulusStartIdx + modulusStart.length(), modulusEndIdx);
        log.info("  📋 Extracted Modulus: {}", modulus);

        // Test if modulus is valid Base64
        try {
          Base64.getDecoder().decode(modulus);
          log.info("  ✅ Modulus is valid Base64");
        } catch (IllegalArgumentException e) {
          log.warn("  ⚠️  Modulus is not valid Base64: {}", e.getMessage());
        }
      }

      // Extract exponent
      String exponentStart = "<Exponent>";
      String exponentEnd = "</Exponent>";
      int exponentStartIdx = xmlKey.indexOf(exponentStart);
      int exponentEndIdx = xmlKey.indexOf(exponentEnd);

      if (exponentStartIdx != -1 && exponentEndIdx != -1) {
        String exponent =
            xmlKey.substring(exponentStartIdx + exponentStart.length(), exponentEndIdx);
        log.info("  📋 Extracted Exponent: {}", exponent);

        // Test if exponent is valid Base64
        try {
          Base64.getDecoder().decode(exponent);
          log.info("  ✅ Exponent is valid Base64");
        } catch (IllegalArgumentException e) {
          log.warn("  ⚠️  Exponent is not valid Base64: {}", e.getMessage());
        }
      }

    } catch (Exception e) {
      log.error("Failed to extract components: {}", e.getMessage());
    }
  }

  /**
   * Validates that a string is valid Base64 - kept for backwards compatibility but should not be
   * used for validating extracted XML components
   */
  @Deprecated
  private static void validateBase64(String base64String, String fieldName) {

    if (base64String == null || base64String.isEmpty()) {
      throw new IllegalArgumentException(fieldName + " cannot be null or empty");
    }

    try {
      Base64.getDecoder().decode(base64String);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(fieldName + " is not valid Base64: " + e.getMessage());
    }
  }

  /** Converts BigInteger to unsigned byte array (removes leading zero if present) */
  private static byte[] toUnsignedByteArray(BigInteger bigInt) {

    byte[] bytes = bigInt.toByteArray();
    if (bytes[0] == 0 && bytes.length > 1) {
      // Remove leading zero byte
      byte[] result = new byte[bytes.length - 1];
      System.arraycopy(bytes, 1, result, 0, result.length);
      return result;
    }
    return bytes;
  }

  /** Fixes Base64 padding issues */
  private static String fixBase64Padding(String base64) {

    if (base64 == null || base64.isEmpty()) {
      return base64;
    }

    // Remove any whitespace
    base64 = base64.replaceAll("\\s+", "");

    // Add padding if needed
    int padding = 4 - (base64.length() % 4);
    if (padding != 4) {
      base64 += "=".repeat(padding);
    }

    return base64;
  }

  /** Recreates the problematic key from your logs for debugging */
  public static void debugProblematicKey() {

    String problematicKey =
        "2048!PFJTQUtleVZhbHVlPjxNb2R1bHVzPnJPbkhCNnlldWZiMEhTTjhqL3c0OXp2c1Y1S1BNYzFoN1d4bWczS2RtYXJuUVBJTUppZFhiMDRCMVp6WGErc0RrcXlhWGxkdHBTM2xRS0JhSEpLRnFZNDIvYU00MjhuTG80S2I5L3orZnZtV2tuM2NmM2ZvMzNBRzZiMHBPczFwcHV4R1BhOUkrNlFUajFxRDA2K3haVkxrY1F5aGx3eGVVRmJoY1ZScHoxcWF0Zk8zTUV1UmdWaXZTcDZOYUdIQzB3WWdBbCtXPTwvTW9kdWx1cz48RXhwb25lbnQ+QVFBQjwvRXhwb25lbnQ+PC9SU0FLZXlWYWx1ZT4=";

    log.info("🐛 Debugging problematic key from logs:");
    analyzeEncodedXmlKey(problematicKey);
  }

  /** Creates encoded XML key from RSA public key components with proper validation */
  public static String createEncodedXmlKeyFromComponents(
      BigInteger modulus, BigInteger exponent, int keySize) {

    try {
      // Convert to Base64 properly
      String modulusB64 = Base64.getEncoder().encodeToString(toUnsignedByteArray(modulus));
      String exponentB64 = Base64.getEncoder().encodeToString(toUnsignedByteArray(exponent));

      log.info("Generated modulus Base64: {}", modulusB64);
      log.info("Generated exponent Base64: {}", exponentB64);

      return createEncodedXmlKey(modulusB64, exponentB64, keySize);

    } catch (Exception e) {
      log.error("Failed to create encoded XML key from components: {}", e.getMessage());
      throw new RuntimeException("Failed to create encoded XML key from components", e);
    }
  }

  /** Test method to demonstrate the correct format */
  public static void main(String[] args) {

    try {
      // Debug the problematic key first
      debugProblematicKey();

      System.out.println("\n" + "=".repeat(50) + "\n");

      // Generate a sample key
      String sampleKey = generateSampleEncodedXmlKey();
      System.out.println("Sample encodedXmlKey: " + sampleKey);

      // Analyze it
      analyzeEncodedXmlKey(sampleKey);

      System.out.println("\n" + "=".repeat(50) + "\n");

      // Example of creating from known components - this should work now
      String knownModulus =
          "rOnHB6yeufb0HSN8j/w49zvsV5KPMc1h7Wxmg3KdmarnQPIMJidXb04B1ZzXa+sDkqyaXldtpS3lQKBaHJKFqY42/aM428nLo4Kb9/z+fvmWkn3cf3fo33AG6b0pOs1ppuxGPa9I+6QTj1qD06+xZVLkcQyhlwxeUFbhcVRpz1qatfO3MEuRgVivSp6NaGHC0wYgAl+W=";
      String knownExponent = "AQAB";

      String customKey = createEncodedXmlKey(knownModulus, knownExponent, 2048);
      System.out.println("Custom encodedXmlKey: " + customKey);

      // Analyze the custom key
      analyzeEncodedXmlKey(customKey);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
