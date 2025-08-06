package com.techservices.digitalbanking.authentication.util;

import com.techservices.digitalbanking.core.service.IpLocationService;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserLoginActivityUtil {
    private static final IpLocationService ipLocationService = new IpLocationService();

    public static String extractDeviceName(UserAgent userAgentObject, String userAgent) {
        String deviceName = "Computer";

        if (userAgentObject != null && userAgentObject.getOperatingSystem() != null) {
            String osName = userAgentObject.getOperatingSystem().getName();

            if (osName != null && !osName.equalsIgnoreCase("Unknown")) {
                if (userAgent != null) {
                    String lowerUserAgent = userAgent.toLowerCase();
                    if (lowerUserAgent.contains("android")) {
                        deviceName = "Mobile";
                    } else if (lowerUserAgent.contains("iphone") || lowerUserAgent.contains("ipad")) {
                        deviceName = "Mobile";
                    } else if (lowerUserAgent.contains("macintosh") || lowerUserAgent.contains("mac os")) {
                        deviceName = "Computer";
                    } else if (lowerUserAgent.contains("windows")) {
                        deviceName = "Computer";
                    } else if (lowerUserAgent.contains("linux")) {
                        deviceName = "Computer";
                    }
                }
            }
        }

        if (userAgent != null) {
            String lowerUserAgent = userAgent.toLowerCase();
            if (lowerUserAgent.contains("okhttp") ||
                    lowerUserAgent.contains("dart") ||
                    lowerUserAgent.contains("cfnetwork")) {
                deviceName = "Mobile";
            } else if (lowerUserAgent.contains("postman") ||
                    lowerUserAgent.contains("insomnia") ||
                    lowerUserAgent.contains("curl")) {
                deviceName = "API Client";
            }
        }

        return deviceName;
    }

    public static String extractSource(UserAgent userAgentObject, String userAgent) {
        String source = "Unknown";

        if (userAgentObject != null && userAgentObject.getBrowser() != null) {
            String browserName = userAgentObject.getBrowser().getName();
            if (browserName != null && !browserName.equalsIgnoreCase("Unknown")) {
                if (userAgentObject.getBrowserVersion() != null) {
                    source = browserName + " " + userAgentObject.getBrowserVersion().getMajorVersion();
                } else {
                    source = browserName;
                }
            }
        }

        if (userAgent != null) {
            String lowerUserAgent = userAgent.toLowerCase();
            if (lowerUserAgent.contains("okhttp")) {
                source = "Android App";
            } else if (lowerUserAgent.contains("dart")) {
                source = "Flutter App";
            } else if (lowerUserAgent.contains("cfnetwork")) {
                source = "iOS App";
            } else if (lowerUserAgent.contains("postman")) {
                source = "Postman";
            } else if (lowerUserAgent.contains("insomnia")) {
                source = "Insomnia";
            } else if (lowerUserAgent.contains("curl")) {
                source = "cURL";
            }
        }

        if ("Unknown".equalsIgnoreCase(source) && userAgent != null && !userAgent.isBlank()) {
            source = userAgent;
        }

        return source;
    }

    public static String extractClientIp(HttpServletRequest request) {
        String[] headers = {
                "X-Forwarded-For",
                "X-Real-IP",
                "X-Client-IP",
                "CF-Connecting-IP", // Cloudflare
                "True-Client-IP"    // Akamai
        };

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                if (ip.contains(",")) {
                    ip = ip.split(",")[0].trim();
                }

                if (isValidPublicIp(ip)) {
                    return ip;
                }
            }
        }

        String remoteAddr = request.getRemoteAddr();
        return isValidPublicIp(remoteAddr) ? remoteAddr : null;
    }

    public static boolean isValidPublicIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if (!ip.matches(ipPattern)) {
            return false;
        }

        return !ip.startsWith("127.") &&           // localhost
                !ip.startsWith("10.") &&            // public static class A
                !ip.startsWith("192.168.") &&       // public static class C
                !(ip.startsWith("172.") &&
                        Integer.parseInt(ip.split("\\.")[1]) >= 16 &&
                        Integer.parseInt(ip.split("\\.")[1]) <= 31); // public static class B
    }

    public static String getLocationFromRequest(String ip, HttpServletRequest request) {
        String location = "-";

        if (isValidPublicIp(ip)) {
            try {
                location = ipLocationService.getLocation(ip);
                if (location != null &&
                        !location.equalsIgnoreCase("Unknown") &&
                        !location.trim().isEmpty()) {
                    System.err.println("Location for IP " + ip + ": " + location);
                    return location;
                }
                System.err.println("Could not determine location for IP: " + ip);
            } catch (Exception e) {
                log.warn("Failed to get location from IP {}: {}", ip, e.getMessage());
            }
        }

        String cfIpCountry = request.getHeader("CF-IPCountry");
        if (cfIpCountry != null && !cfIpCountry.equalsIgnoreCase("XX")) {
            return cfIpCountry;
        }

        String acceptLanguage = request.getHeader("Accept-Language");
        if (acceptLanguage != null) {
            if (acceptLanguage.toLowerCase().contains("en-us")) {
                location = "United States";
            } else if (acceptLanguage.toLowerCase().contains("en-gb")) {
                location = "United Kingdom";
            } else if (acceptLanguage.toLowerCase().contains("en-ie")) {
                location = "Ireland";
            }
        }

        return location;
    }
}
