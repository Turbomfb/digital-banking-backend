package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.core.configuration.resttemplate.ApiService;
import com.techservices.digitalbanking.core.domain.data.model.BankData;
import com.techservices.digitalbanking.core.domain.data.repository.BankDataRepository;
import com.techservices.digitalbanking.core.domain.dto.response.BankDataResponse;
import com.techservices.digitalbanking.core.domain.dto.response.YouverifyBankDataResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.walletaccount.domain.request.NameEnquiryRequest;
import com.techservices.digitalbanking.walletaccount.domain.response.NameEnquiryResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankDataService {

    private final YouverifyBankCache youverifyBankCache;
    private final ApiService apiService;
    private final SystemProperty systemProperty;
    private final BankDataRepository bankDataRepository;

    private static final double SCORE_THRESHOLD = 0.85;

    private static final String ACCOUNT_VERIFICATION_FAILED_ERROR =
            "Account verification failed. Please check the details or try again later.";

    private static final Pattern STOP_WORDS = Pattern.compile(
            "\\b(bank|plc|ltd|limited|mfb|microfinance|merchant|nigeria|ng)\\b");

    private static final Map<String, String> ACRONYMS = Map.ofEntries(
            Map.entry("gtbank", "guaranty trust bank"),
            Map.entry("gt", "guaranty trust bank"),
            Map.entry("gtb", "guaranty trust bank"),
            Map.entry("uba", "united bank for africa"),
            Map.entry("fcmb", "first city monument bank"),
            Map.entry("kuda", "kuda microfinance bank"),
            Map.entry("stanbic", "stanbic ibtc bank"),
            Map.entry("wema", "wema bank"),
            Map.entry("sterling", "sterling bank"),
            Map.entry("fidelity", "fidelity bank"),
            Map.entry("opay", "opay digital services limited")
    );

    private static final Map<String, String> DIRECT_MAPPINGS = Map.of(
            "gtbank plc", "guaranty trust bank",
            "gtbank", "guaranty trust bank",
            "opay", "opay digital services limited",
            "kuda mfb", "kuda microfinance bank",
            "sterling bank plc", "sterling bank"
    );

    private final JaroWinklerDistance distance = new JaroWinklerDistance();
    private final String pathUrl = "/v2/api/identity/ng/bank-account-number/";

    public BankDataResponse retrieveAllBanks() {
        BankDataResponse bankDataResponse = new BankDataResponse();
        bankDataResponse.setSuccess(true);
        bankDataResponse.setStatusCode(200);
        bankDataResponse.setMessage("Bank data retrieved successfully");
        bankDataResponse.setData(bankDataRepository.findAll());
        return bankDataResponse;
    }

    public YouverifyBankDataResponse retrieveAllYouverifyBankList() {
        return this.youverifyBankCache.getBankList();
    }

    public NameEnquiryResponse processNameEnquiry(NameEnquiryRequest request) {
        log.info("Processing name enquiry for bank code: {}, account: {}",
                request.getBankCode(), request.getAccountNumber());

        validateRequest(request);

        try {
            request.setSubjectConsent(true);
            return callYouverifyResolve(request);
        } catch (Exception directFailure) {
            log.info("Direct enquiry failed ({}). Falling back to fuzzy match.",
                    directFailure.getMessage());
            return processWithFuzzyMatching(request);
        }
    }

    private NameEnquiryResponse processWithFuzzyMatching(NameEnquiryRequest originalRequest) {
        BankData localBankData = bankDataRepository.findByCode(originalRequest.getBankCode())
                .orElseThrow(() -> new ValidationException(ACCOUNT_VERIFICATION_FAILED_ERROR));

        String mappedCode = findBestMatchingBankCode(localBankData.getName());
        if (mappedCode == null) {
            throw new ValidationException(ACCOUNT_VERIFICATION_FAILED_ERROR);
        }

        log.info("Mapped bank code {} to {} for fuzzy matching",
                originalRequest.getBankCode(), mappedCode);

        NameEnquiryRequest mappedRequest = new NameEnquiryRequest();
        mappedRequest.setBankCode(mappedCode);
        mappedRequest.setAccountNumber(originalRequest.getAccountNumber());
        mappedRequest.setSubjectConsent(true);

        try {
            return callYouverifyResolve(mappedRequest);
        } catch (Exception mappedFailure) {
            log.error("Fuzzy matching also failed for mapped code: {}", mappedCode, mappedFailure);
            throw new ValidationException(ACCOUNT_VERIFICATION_FAILED_ERROR);
        }
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", systemProperty.getYouverifyIntegrationApiKey());
        return headers;
    }

    private NameEnquiryResponse callYouverifyResolve(NameEnquiryRequest req)
            throws JsonProcessingException, PlatformServiceException {

        String url = systemProperty.getYouverifyIntegrationUrl() + pathUrl + "resolve";
        NameEnquiryResponse resp =
                apiService.callExternalApi(url, NameEnquiryResponse.class, HttpMethod.POST, req, getHeaders());

        if (resp.getData() == null ||
                resp.getData().getBankDetails() == null ||
                StringUtils.isBlank(resp.getData().getBankDetails().getAccountName())) {
            throw new ValidationException(ACCOUNT_VERIFICATION_FAILED_ERROR);
        }
        return resp;
    }

    private void validateRequest(NameEnquiryRequest request) {
        if (request == null ||
                StringUtils.isBlank(request.getBankCode()) ||
                StringUtils.isBlank(request.getAccountNumber())) {
            throw new ValidationException("request.invalid", "Bank code and account number are required");
        }
    }

    private String findBestMatchingBankCode(String localBankName) {
        YouverifyBankDataResponse yResp = retrieveAllYouverifyBankList();
        if (yResp == null || yResp.getData() == null || yResp.getData().isEmpty()) {
            log.warn("No Youverify banks available for matching");
            return null;
        }

        log.info("Looking for match for local bank: \"{}\"", localBankName);

        // First, try direct mappings
        String directMapping = DIRECT_MAPPINGS.get(localBankName.toLowerCase());
        if (directMapping != null) {
            log.info("Found direct mapping for \"{}\": \"{}\"", localBankName, directMapping);
            String resp = findYouverifyBankByName(yResp, directMapping);
            if (resp != null) {
                return resp;
            }
        }

        String expandedLocal = expandAcronym(localBankName);
        String normalizedLocal = normalize(expandedLocal);

        log.info("Expanded \"{}\" to \"{}\"", localBankName, expandedLocal);
        log.info("Normalized \"{}\" to \"{}\"", expandedLocal, normalizedLocal);

        // Try exact match after expansion and normalization
        for (YouverifyBankDataResponse.BankData bank : yResp.getData()) {
            String expandedYouverify = expandAcronym(bank.getName());
            String normalizedYouverify = normalize(expandedYouverify);

            if (normalizedLocal.equals(normalizedYouverify)) {
                log.info("Found exact match for \"{}\": \"{}\" (code: {})",
                        localBankName, bank.getName(), bank.getCode());
                return bank.getCode();
            }
        }

        // Try substring matching with expanded names
        for (YouverifyBankDataResponse.BankData bank : yResp.getData()) {
            String expandedYouverify = expandAcronym(bank.getName());
            String normalizedYouverify = normalize(expandedYouverify);

            if (normalizedLocal.contains(normalizedYouverify) || normalizedYouverify.contains(normalizedLocal)) {
                log.info("Found substring match for \"{}\": \"{}\" (code: {})",
                        localBankName, bank.getName(), bank.getCode());
                return bank.getCode();
            }
        }

        // Finally, fall back to fuzzy matching
        double bestScore = 0.0;
        String bestCode = null;
        String bestName = null;

        for (YouverifyBankDataResponse.BankData bank : yResp.getData()) {
            double score = similarity(localBankName, bank.getName());
            if (score > bestScore) {
                bestScore = score;
                bestCode = bank.getCode();
                bestName = bank.getName();
            }
        }

        if (bestScore >= SCORE_THRESHOLD) {
            log.info("Found fuzzy match for \"{}\": \"{}\" (score: {:.3f}, code: {})",
                    localBankName, bestName, bestScore, bestCode);
            return bestCode;
        } else {
            log.warn("No suitable match found for \"{}\". Best score: {:.3f} (threshold: {})",
                    localBankName, bestScore, SCORE_THRESHOLD);
            return null;
        }
    }

    private String findYouverifyBankByName(YouverifyBankDataResponse yResp, String targetName) {
        String normalizedTarget = normalize(targetName);

        for (YouverifyBankDataResponse.BankData bank : yResp.getData()) {
            String normalizedBank = normalize(bank.getName());
            if (normalizedTarget.equals(normalizedBank)) {
                log.info("Found YouVerify bank for \"{}\": \"{}\" (code: {})",
                        targetName, bank.getName(), bank.getCode());
                return bank.getCode();
            }
        }

        log.warn("No YouVerify bank found for direct mapping: \"{}\"", targetName);
        return null;
    }

    private double similarity(String name1, String name2) {
        return distance.apply(normalize(expandAcronym(name1)),
                normalize(expandAcronym(name2)));
    }

    private String expandAcronym(String input) {
        if (input == null) return "";

        String key = input.toLowerCase().replaceAll("\\s+", "");
        String expanded = ACRONYMS.get(key);

        if (expanded != null) {
            log.debug("Expanded acronym: \"{}\" -> \"{}\"", input, expanded);
            return expanded;
        }

        for (Map.Entry<String, String> entry : ACRONYMS.entrySet()) {
            if (key.contains(entry.getKey())) {
                log.debug("Partially expanded acronym: \"{}\" -> \"{}\"", input, entry.getValue());
                return entry.getValue();
            }
        }

        return input;
    }

    private String normalize(String name) {
        if (name == null) return "";

        String cleaned = name.toLowerCase().replaceAll("[^a-z0-9 ]", " ");
        cleaned = STOP_WORDS.matcher(cleaned).replaceAll(" ");
        String result = cleaned.replaceAll("\\s+", " ").trim();

        log.debug("Normalized \"{}\" -> \"{}\"", name, result);
        return result;
    }
}
