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
        String deviceName = "Computer"; // Default fallback

        log.info("=== DEVICE NAME EXTRACTION DEBUG ===");
        log.info("sec-ch-ua-mobile: {}", request.getHeader("sec-ch-ua-mobile"));
        log.info("sec-ch-ua-platform: {}", request.getHeader("sec-ch-ua-platform"));
        log.info("User-Agent: {}", userAgent);

        // First priority: Check sec-ch-ua-mobile header (most reliable for mobile detection)
        String secChUaMobile = request.getHeader("sec-ch-ua-mobile");
        if (secChUaMobile != null) {
            if ("?1".equals(secChUaMobile)) {
                // Mobile device - get specific mobile device name
                String secChUaPlatform = request.getHeader("sec-ch-ua-platform");
                if (secChUaPlatform != null) {
                    String platform = secChUaPlatform.replace("\"", "").toLowerCase();
                    switch (platform) {
                        case "android":
                            deviceName = "Android";
                            break;
                        case "ios":
                            deviceName = determineIosDevice(userAgent);
                            break;
                        default:
                            deviceName = "Mobile";
                    }
                } else {
                    deviceName = "Mobile";
                }
            } else {
                // Desktop device - get specific desktop device name
                String secChUaPlatform = request.getHeader("sec-ch-ua-platform");
                if (secChUaPlatform != null) {
                    String platform = secChUaPlatform.replace("\"", "");
                    switch (platform.toLowerCase()) {
                        case "macos":
                            deviceName = determineMacDevice(userAgent);
                            break;
                        case "windows":
                            deviceName = determineWindowsDevice(userAgent);
                            break;
                        case "linux":
                            deviceName = "Linux Computer";
                            break;
                        case "chrome os":
                            deviceName = "Chromebook";
                            break;
                        default:
                            deviceName = platform + " Computer";
                    }
                } else {
                    deviceName = "Computer";
                }
            }
        } else {
            // Fallback to user agent parsing if sec-ch-ua headers not available
            if (userAgent != null) {
                deviceName = extractDeviceFromUserAgent(userAgent);
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
            } else if (lowerUserAgent.contains("postman")) {
                deviceName = "Postman";
            } else if (lowerUserAgent.contains("insomnia")) {
                deviceName = "Insomnia";
            } else if (lowerUserAgent.contains("curl")) {
                deviceName = "API Client";
            }
        }

        log.info("Final device name: {}", deviceName);
        return deviceName;
    }

    private static String determineMacDevice(String userAgent) {
        if (userAgent == null) return "Mac";

        String lowerUserAgent = userAgent.toLowerCase();

        // Check for specific Mac models in user agent
        if (lowerUserAgent.contains("macbook")) {
            return "MacBook";
        } else if (lowerUserAgent.contains("imac")) {
            return "iMac";
        } else if (lowerUserAgent.contains("mac pro")) {
            return "Mac Pro";
        } else if (lowerUserAgent.contains("mac mini")) {
            return "Mac Mini";
        } else if (lowerUserAgent.contains("macintosh") || lowerUserAgent.contains("mac os")) {
            // Generic Mac detection - could be any Mac
            return "Mac";
        }

        return "Mac"; // Default for macOS platform
    }

    private static String determineWindowsDevice(String userAgent) {
        if (userAgent == null) return "Windows PC";

        String lowerUserAgent = userAgent.toLowerCase();

        // Check Windows version
        if (lowerUserAgent.contains("windows nt 10.0")) {
            return "Windows 10/11 PC";
        } else if (lowerUserAgent.contains("windows nt 6.3")) {
            return "Windows 8.1 PC";
        } else if (lowerUserAgent.contains("windows nt 6.1")) {
            return "Windows 7 PC";
        } else if (lowerUserAgent.contains("windows")) {
            return "Windows PC";
        }

        return "Windows PC";
    }

    private static String determineIosDevice(String userAgent) {
        if (userAgent == null) return "iOS Device";

        String lowerUserAgent = userAgent.toLowerCase();

        if (lowerUserAgent.contains("iphone")) {
            return "iPhone";
        } else if (lowerUserAgent.contains("ipad")) {
            return "iPad";
        } else if (lowerUserAgent.contains("ipod")) {
            return "iPod";
        }

        return "iOS Device";
    }

    private static String extractDeviceFromUserAgent(String userAgent) {
        String lowerUserAgent = userAgent.toLowerCase();

        // Mobile devices
        if (lowerUserAgent.contains("android")) {
            return "Android";
        } else if (lowerUserAgent.contains("iphone")) {
            return "iPhone";
        } else if (lowerUserAgent.contains("ipad")) {
            return "iPad";
        } else if (lowerUserAgent.contains("mobile")) {
            return "Mobile";
        }
        // Desktop devices
        else if (lowerUserAgent.contains("macintosh") || lowerUserAgent.contains("mac os")) {
            return determineMacDevice(userAgent);
        } else if (lowerUserAgent.contains("windows")) {
            return determineWindowsDevice(userAgent);
        } else if (lowerUserAgent.contains("linux")) {
            return "Linux Computer";
        } else if (lowerUserAgent.contains("chrome os")) {
            return "Chromebook";
        }

        return "Computer";
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

        // Priority 1: Custom client location header (highest priority - user provided)
        String clientLocation = request.getHeader("X-Client-Location");
        log.info("X-Client-Location header: {}", clientLocation);
        if (clientLocation != null && !clientLocation.isEmpty() && !clientLocation.trim().equals("-")) {
            location = clientLocation.trim();
            log.info("Using client-provided location: {}", location);
            return location;
        }

        // Alternative custom location headers
        String[] customLocationHeaders = {
                "X-User-Location",
                "X-Geo-Location",
                "X-Location"
        };

        for (String header : customLocationHeaders) {
            String headerValue = request.getHeader(header);
            log.info("Header {}: {}", header, headerValue);
            if (headerValue != null && !headerValue.isEmpty() && !headerValue.trim().equals("-")) {
                location = headerValue.trim();
                log.info("Using location from {}: {}", header, location);
                return location;
            }
        }

        // Priority 2: Separate city and state headers
        String clientCity = request.getHeader("X-Client-City");
        String clientState = request.getHeader("X-Client-State");
        log.info("X-Client-City: {}, X-Client-State: {}", clientCity, clientState);

        if (clientCity != null && !clientCity.isEmpty()) {
            if (clientState != null && !clientState.isEmpty()) {
                location = clientCity.trim() + ", " + clientState.trim();
            } else {
                location = clientCity.trim();
            }
            log.info("Using client city/state: {}", location);
            return location;
        }

        // Priority 3: CloudFlare country header (most reliable for country-level location)
        String cfIpCountry = request.getHeader("CF-IPCountry");
        log.info("CF-IPCountry header: {}", cfIpCountry);
        if (cfIpCountry != null && !cfIpCountry.equalsIgnoreCase("XX") && !cfIpCountry.isEmpty()) {
            location = mapCountryCodeToName(cfIpCountry);
            log.info("Using CloudFlare country: {}", location);
            return location;
        }

        // Priority 4: Other location headers from CDN/proxy
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

        // Priority 5: IP geolocation (only if we have a valid user IP)
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

        // Priority 6: Accept-Language header as last resort
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

        // Map common country codes to full names
        switch (countryCode.toUpperCase()) {
            case "NG": return "Nigeria";
            case "US": return "United States";
            case "GB": return "United Kingdom";
            case "IE": return "Ireland";
            case "DE": return "Germany";
            case "FR": return "France";
            case "CA": return "Canada";
            case "AU": return "Australia";
            case "ZA": return "South Africa";
            case "KE": return "Kenya";
            case "GH": return "Ghana";
            case "EG": return "Egypt";
            case "MA": return "Morocco";
            case "TN": return "Tunisia";
            case "SN": return "Senegal";
            case "UG": return "Uganda";
            case "TZ": return "Tanzania";
            case "ET": return "Ethiopia";
            case "RW": return "Rwanda";
            case "BW": return "Botswana";
            case "MU": return "Mauritius";
            case "IN": return "India";
            case "PK": return "Pakistan";
            case "BD": return "Bangladesh";
            case "LK": return "Sri Lanka";
            case "MY": return "Malaysia";
            case "SG": return "Singapore";
            case "TH": return "Thailand";
            case "PH": return "Philippines";
            case "ID": return "Indonesia";
            case "VN": return "Vietnam";
            case "CN": return "China";
            case "JP": return "Japan";
            case "KR": return "South Korea";
            case "BR": return "Brazil";
            case "MX": return "Mexico";
            case "AR": return "Argentina";
            case "CL": return "Chile";
            case "PE": return "Peru";
            case "CO": return "Colombia";
            default: return countryCode.toUpperCase(); // Return code if not mapped
        }
    }

    private static String getLocationFromAcceptLanguage(String acceptLanguage) {
        String lowerLang = acceptLanguage.toLowerCase();

        // Check for specific locale patterns
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
