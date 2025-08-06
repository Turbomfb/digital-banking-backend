package com.techservices.digitalbanking.authentication.util;

import com.techservices.digitalbanking.core.service.IpLocationService;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;

@Slf4j
public class UserLoginActivityUtil {
    private static final IpLocationService ipLocationService = new IpLocationService();

    // Known server/cloud provider IP ranges that should not be used for user geolocation
    private static final Set<String> CLOUD_PROVIDER_RANGES = Set.of(
            "34.255.", "34.254.", "34.253.", // AWS Ireland
            "52.16.", "52.17.", "52.18.",    // AWS Ireland
            "13.40.", "13.41.", "13.42.",    // AWS Europe
            "35.157.", "35.158.", "35.159.", // AWS Frankfurt
            "104.199.", "104.198.",          // Google Cloud
            "20.50.", "20.51.", "20.52."     // Azure
    );

    public static String extractDeviceNameFromHeaders(HttpServletRequest request, String userAgent) {
        String deviceName = "Computer"; // Default for web browsers

        // First priority: Check sec-ch-ua-mobile header (most reliable for mobile detection)
        String secChUaMobile = request.getHeader("sec-ch-ua-mobile");
        if (secChUaMobile != null) {
            if ("?1".equals(secChUaMobile)) {
                deviceName = "Mobile";
            } else {
                // Check sec-ch-ua-platform for more specific device type
                String secChUaPlatform = request.getHeader("sec-ch-ua-platform");
                if (secChUaPlatform != null) {
                    String platform = secChUaPlatform.replace("\"", "").toLowerCase();
                    switch (platform) {
                        case "android":
                            deviceName = "Mobile";
                            break;
                        case "ios":
                            deviceName = "Mobile";
                            break;
                        case "macos":
                        case "windows":
                        case "linux":
                        case "chrome os":
                            deviceName = "Computer";
                            break;
                        default:
                            deviceName = "Computer";
                    }
                }
            }
        } else {
            // Fallback to user agent parsing if sec-ch-ua headers not available
            if (userAgent != null) {
                String lowerUserAgent = userAgent.toLowerCase();
                if (lowerUserAgent.contains("android") ||
                        lowerUserAgent.contains("iphone") ||
                        lowerUserAgent.contains("ipad") ||
                        lowerUserAgent.contains("mobile")) {
                    deviceName = "Mobile";
                } else if (lowerUserAgent.contains("macintosh") ||
                        lowerUserAgent.contains("windows") ||
                        lowerUserAgent.contains("linux") ||
                        lowerUserAgent.contains("mac os")) {
                    deviceName = "Computer";
                }
            }
        }

        // Override for API clients regardless of other detection
        if (userAgent != null) {
            String lowerUserAgent = userAgent.toLowerCase();
            if (lowerUserAgent.contains("okhttp")) {
                deviceName = "Android App";
            } else if (lowerUserAgent.contains("dart")) {
                deviceName = "Flutter App";
            } else if (lowerUserAgent.contains("cfnetwork")) {
                deviceName = "iOS App";
            } else if (lowerUserAgent.contains("postman") ||
                    lowerUserAgent.contains("insomnia") ||
                    lowerUserAgent.contains("curl")) {
                deviceName = "API Client";
            }
        }

        return deviceName;
    }

    public static String extractSourceFromHeaders(HttpServletRequest request, String userAgent) {
        String source = "Unknown";

        String secChUa = request.getHeader("sec-ch-ua");
        if (secChUa != null && !secChUa.isEmpty()) {
            source = parseBrowserFromSecChUa(secChUa);
        }

        if ("Unknown".equals(source) && userAgent != null) {
            UserAgent userAgentObject = UserAgent.parseUserAgentString(userAgent);
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

        if ("Unknown".equals(source) && userAgent != null && !userAgent.isBlank()) {
            if (userAgent.contains("Chrome/")) {
                String chromeVersion = userAgent.substring(userAgent.indexOf("Chrome/") + 7);
                chromeVersion = chromeVersion.split(" ")[0].split("\\.")[0]; // Get major version only
                source = "Chrome " + chromeVersion;
            } else if (userAgent.contains("Firefox/")) {
                String firefoxVersion = userAgent.substring(userAgent.indexOf("Firefox/") + 8);
                firefoxVersion = firefoxVersion.split(" ")[0].split("\\.")[0];
                source = "Firefox " + firefoxVersion;
            } else if (userAgent.contains("Safari/") && !userAgent.contains("Chrome")) {
                source = "Safari";
            } else {
                source = userAgent;
            }
        }

        return source;
    }

    private static String parseBrowserFromSecChUa(String secChUa) {
        try {
            String[] brands = secChUa.split(",");

            for (String brand : brands) {
                brand = brand.trim();
                if (brand.contains("Google Chrome")) {
                    String version = extractVersionFromBrand(brand);
                    return "Chrome" + (version != null ? " " + version : "");
                } else if (brand.contains("Microsoft Edge")) {
                    String version = extractVersionFromBrand(brand);
                    return "Edge" + (version != null ? " " + version : "");
                } else if (brand.contains("Firefox")) {
                    String version = extractVersionFromBrand(brand);
                    return "Firefox" + (version != null ? " " + version : "");
                } else if (brand.contains("Safari") && !brand.contains("Chrome")) {
                    String version = extractVersionFromBrand(brand);
                    return "Safari" + (version != null ? " " + version : "");
                } else if (brand.contains("Chromium") && !secChUa.contains("Google Chrome")) {
                    String version = extractVersionFromBrand(brand);
                    return "Chromium" + (version != null ? " " + version : "");
                }
            }
        } catch (Exception e) {
            log.warn("Failed to parse sec-ch-ua header: {}", secChUa, e);
        }

        return "Unknown";
    }

    public static String extractVersionFromBrand(String brand) {
        try {
            if (brand.contains(";v=\"")) {
                String version = brand.substring(brand.indexOf(";v=\"") + 4);
                version = version.replace("\"", "");
                return version.split("\\.")[0];
            }
        } catch (Exception e) {
            log.debug("Failed to extract version from brand: {}", brand);
        }
        return null;
    }

    public static String extractClientIp(HttpServletRequest request) {
        // Priority order for IP headers - most reliable first
        String[] headers = {
                "CF-Connecting-IP",     // Cloudflare (most reliable)
                "True-Client-IP",       // Akamai, Cloudflare Enterprise
                "X-Real-IP",           // Nginx proxy
                "X-Forwarded-For",     // Standard proxy header
                "X-Client-IP",         // Apache mod_proxy
                "X-Forwarded",         // Less common
                "Forwarded-For",       // Less common
                "Forwarded"            // RFC 7239
        };

        log.info("=== IP EXTRACTION DEBUG ===");

        // Log all relevant headers for debugging
        Arrays.stream(headers).forEach(header -> {
            String headerValue = request.getHeader(header);
            log.info("Header {}: {}", header, headerValue);
        });
        log.info("Remote Address: {}", request.getRemoteAddr());

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
                // Handle comma-separated IPs (X-Forwarded-For chain)
                if (ip.contains(",")) {
                    String[] ips = ip.split(",");
                    for (String singleIp : ips) {
                        singleIp = singleIp.trim();
                        log.info("Checking IP from {}: {}", header, singleIp);
                        if (isValidUserIp(singleIp)) {
                            log.info("Using IP: {} from header: {}", singleIp, header);
                            return singleIp;
                        }
                    }
                } else {
                    log.info("Checking single IP from {}: {}", header, ip.trim());
                    if (isValidUserIp(ip.trim())) {
                        log.info("Using IP: {} from header: {}", ip.trim(), header);
                        return ip.trim();
                    }
                }
            }
        }

        // Fallback to remote address only if it's a valid user IP
        String remoteAddr = request.getRemoteAddr();
        if (isValidUserIp(remoteAddr)) {
            log.info("Using remote address: {}", remoteAddr);
            return remoteAddr;
        }

        log.warn("No valid user IP found. All IPs appear to be server/proxy IPs");
        return null;
    }

    private static boolean isValidUserIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        // Basic IP format validation
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if (!ip.matches(ipPattern)) {
            log.debug("IP {} failed format validation", ip);
            return false;
        }

        // Exclude localhost and private IP ranges
        if (ip.startsWith("127.") ||           // localhost
                ip.startsWith("10.") ||            // private class A
                ip.startsWith("192.168.") ||       // private class C
                (ip.startsWith("172.") &&
                        Integer.parseInt(ip.split("\\.")[1]) >= 16 &&
                        Integer.parseInt(ip.split("\\.")[1]) <= 31)) { // private class B
            log.debug("IP {} is private/localhost", ip);
            return false;
        }

        // Check if IP belongs to known cloud provider ranges (likely server IPs)
        for (String cloudRange : CLOUD_PROVIDER_RANGES) {
            if (ip.startsWith(cloudRange)) {
                log.info("IP {} matches cloud provider range {}, likely server IP", ip, cloudRange);
                return false;
            }
        }

        return true;
    }

    public static String getLocationFromRequest(String ip, HttpServletRequest request) {
        log.info("=== LOCATION EXTRACTION DEBUG ===");
        log.info("Input IP: {}", ip);

        String location = "-";

        // Priority 1: CloudFlare country header (most reliable for user location)
        String cfIpCountry = request.getHeader("CF-IPCountry");
        log.info("CF-IPCountry header: {}", cfIpCountry);
        if (cfIpCountry != null && !cfIpCountry.equalsIgnoreCase("XX") && !cfIpCountry.isEmpty()) {
            location = mapCountryCodeToName(cfIpCountry);
            log.info("Using CloudFlare country: {}", location);
            return location;
        }

        // Priority 2: Other location headers from CDN/proxy
        String[] locationHeaders = {
                "CloudFront-Viewer-Country",
                "X-Country-Code",
                "X-Geo-Country",
                "Geo-Country"
        };

        for (String header : locationHeaders) {
            String headerValue = request.getHeader(header);
            log.info("Header {}: {}", header, headerValue);
            if (headerValue != null && !headerValue.isEmpty() && !headerValue.equalsIgnoreCase("XX")) {
                location = mapCountryCodeToName(headerValue);
                log.info("Using location from {}: {}", header, location);
                return location;
            }
        }

        // Priority 3: IP geolocation (only if we have a valid user IP)
        if (ip != null && isValidUserIp(ip)) {
            try {
                log.info("Attempting IP geolocation for: {}", ip);
                location = ipLocationService.getLocation(ip);
                log.info("IP geolocation result: {}", location);
                if (location != null &&
                        !location.equalsIgnoreCase("Unknown") &&
                        !location.trim().isEmpty() &&
                        !location.toLowerCase().contains("dublin") && // Filter out server location
                        !location.toLowerCase().contains("ireland")) {
                    return location;
                } else {
                    log.info("IP geolocation returned server location or unknown, ignoring");
                }
            } catch (Exception e) {
                log.warn("Failed to get location from IP {}: {}", ip, e.getMessage());
            }
        }

        // Priority 4: Accept-Language header as last resort
        String acceptLanguage = request.getHeader("Accept-Language");
        log.info("Accept-Language header: {}", acceptLanguage);
        if (acceptLanguage != null) {
            location = getLocationFromAcceptLanguage(acceptLanguage);
            if (!"-".equals(location)) {
                log.info("Using location from Accept-Language: {}", location);
                return location;
            }
        }

        log.info("Final location result: {}", location);
        return location;
    }

    private static String mapCountryCodeToName(String countryCode) {
        if (countryCode == null || countryCode.length() != 2) {
            return countryCode;
        }

        return switch (countryCode.toUpperCase()) {
            case "NG" -> "Nigeria";
            case "US" -> "United States";
            case "GB" -> "United Kingdom";
            case "IE" -> "Ireland";
            case "DE" -> "Germany";
            case "FR" -> "France";
            case "CA" -> "Canada";
            case "AU" -> "Australia";
            case "ZA" -> "South Africa";
            case "KE" -> "Kenya";
            case "GH" -> "Ghana";
            case "EG" -> "Egypt";
            case "MA" -> "Morocco";
            case "TN" -> "Tunisia";
            case "SN" -> "Senegal";
            case "UG" -> "Uganda";
            case "TZ" -> "Tanzania";
            case "ET" -> "Ethiopia";
            case "RW" -> "Rwanda";
            case "BW" -> "Botswana";
            case "MU" -> "Mauritius";
            case "IN" -> "India";
            case "PK" -> "Pakistan";
            case "BD" -> "Bangladesh";
            case "LK" -> "Sri Lanka";
            case "MY" -> "Malaysia";
            case "SG" -> "Singapore";
            case "TH" -> "Thailand";
            case "PH" -> "Philippines";
            case "ID" -> "Indonesia";
            case "VN" -> "Vietnam";
            case "CN" -> "China";
            case "JP" -> "Japan";
            case "KR" -> "South Korea";
            case "BR" -> "Brazil";
            case "MX" -> "Mexico";
            case "AR" -> "Argentina";
            case "CL" -> "Chile";
            case "PE" -> "Peru";
            case "CO" -> "Colombia";
            default -> countryCode.toUpperCase();
        };
    }

    private static String getLocationFromAcceptLanguage(String acceptLanguage) {
        String lowerLang = acceptLanguage.toLowerCase();

        if (lowerLang.contains("en-ng") || lowerLang.contains("yo") || lowerLang.contains("ig") || lowerLang.contains("ha")) {
            return "Nigeria";
        } else if (lowerLang.contains("en-us")) {
            return "United States";
        } else if (lowerLang.contains("en-gb")) {
            return "United Kingdom";
        } else if (lowerLang.contains("en-ie")) {
            return "Ireland";
        } else if (lowerLang.contains("en-ca")) {
            return "Canada";
        } else if (lowerLang.contains("en-au")) {
            return "Australia";
        } else if (lowerLang.contains("en-za")) {
            return "South Africa";
        } else if (lowerLang.contains("fr-fr")) {
            return "France";
        } else if (lowerLang.contains("de-de")) {
            return "Germany";
        } else if (lowerLang.contains("es-es")) {
            return "Spain";
        } else if (lowerLang.contains("pt-br")) {
            return "Brazil";
        }

        return "-";
    }
}
