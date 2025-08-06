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
        String[] headers = {
                "X-Forwarded-For",
                "X-Real-IP",
                "X-Client-IP",
                "CF-Connecting-IP",
                "True-Client-IP"
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

    private static boolean isValidPublicIp(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        // Basic IP format validation
        String ipPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if (!ip.matches(ipPattern)) {
            return false;
        }

        // Exclude localhost and private IP ranges
        return !ip.startsWith("127.") &&           // localhost
                !ip.startsWith("10.") &&            // private class A
                !ip.startsWith("192.168.") &&       // private class C
                !(ip.startsWith("172.") &&
                        Integer.parseInt(ip.split("\\.")[1]) >= 16 &&
                        Integer.parseInt(ip.split("\\.")[1]) <= 31); // private class B
    }

    public static String getLocationFromRequest(String ip, HttpServletRequest request) {
        String location = "-";

        // First, try to get location from IP if we have a valid public IP
        if (ip != null && isValidPublicIp(ip)) {
            try {
                location = ipLocationService.getLocation(ip);
                if (location != null &&
                        !location.equalsIgnoreCase("Unknown") &&
                        !location.trim().isEmpty()) {
                    return location;
                }
            } catch (Exception e) {
                log.warn("Failed to get location from IP {}: {}", ip, e.getMessage());
            }
        }

        // Fallback: try to extract location hints from headers
        // Some CDNs or proxies might add location headers
        String cfIpCountry = request.getHeader("CF-IPCountry");
        if (cfIpCountry != null && !cfIpCountry.equalsIgnoreCase("XX")) {
            return cfIpCountry;
        }

        // Check Accept-Language header as a last resort for country hint
        String acceptLanguage = request.getHeader("Accept-Language");
        if (acceptLanguage != null) {
            // This is very basic and not reliable, but better than nothing
            if (acceptLanguage.toLowerCase().contains("en-us")) {
                location = "United States";
            } else if (acceptLanguage.toLowerCase().contains("en-gb")) {
                location = "United Kingdom";
            } else if (acceptLanguage.toLowerCase().contains("en-ie")) {
                location = "Ireland";
            }
            // Add more mappings as needed
        }

        return location;
    }
}
