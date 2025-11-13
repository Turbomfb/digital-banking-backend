package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.model.Beneficiary;
import com.techservices.digitalbanking.core.domain.data.repository.BeneficiaryRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.request.AddBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.request.UpdateBeneficiaryRequest;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryListResponse;
import com.techservices.digitalbanking.core.domain.dto.response.BeneficiaryResponse;
import com.techservices.digitalbanking.core.exception.ValidationException;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeneficiaryService {

  private final BeneficiaryRepository beneficiaryRepository;
  private final CustomerRepository customerRepository;

  @Transactional
  public BeneficiaryResponse addBeneficiary(AddBeneficiaryRequest request, Long customerId) {
    log.info("Adding beneficiary for customer: {}, accountNumber: {}, bankCode: {}",
        customerId, request.getAccountNumber(), request.getBankCode());

    request.validate();

    Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ValidationException("customer.not.found", "Customer not found"));

    if (beneficiaryRepository.existsByCustomerIdAndAccountNumberAndBankCode(
        customerId, request.getAccountNumber(), request.getBankCode())) {
      throw new ValidationException("beneficiary.already.exists",
          "This beneficiary already exists in your list");
    }

    if (StringUtils.isNotBlank(request.getNickname()) &&
        beneficiaryRepository.existsByCustomerIdAndNickname(customerId, request.getNickname())) {
      throw new ValidationException("nickname.already.exists",
          "This nickname is already used for another beneficiary");
    }

    Beneficiary beneficiary = new Beneficiary();
    beneficiary.setCustomerId(customerId);
    beneficiary.setAccountName(request.getAccountName());
    beneficiary.setAccountNumber(request.getAccountNumber());
    beneficiary.setBankName(request.getBankName());
    beneficiary.setBankCode(request.getBankCode());
    beneficiary.setNickname(request.getNickname());
    beneficiary.setIsVerified(true);
    beneficiary.setIsActive(true);
    beneficiary.setCreatedAt(LocalDateTime.now());

    Beneficiary savedBeneficiary = beneficiaryRepository.save(beneficiary);
    log.info("Beneficiary added successfully: {}", savedBeneficiary.getId());

    return BeneficiaryResponse.from(savedBeneficiary);
  }

  @Transactional(readOnly = true)
  public BeneficiaryListResponse getAllBeneficiaries(Long customerId) {
    log.info("Fetching all beneficiaries for customer: {}", customerId);

    if (!customerRepository.existsById(customerId)) {
      throw new ValidationException("customer.not.found", "Customer not found");
    }

    List<Beneficiary> beneficiaries = beneficiaryRepository
        .findByCustomerIdAndIsActiveTrueOrderByLastUsedAtDesc(customerId);

    List<BeneficiaryResponse> beneficiaryResponses = beneficiaries.stream()
        .map(BeneficiaryResponse::from)
        .collect(Collectors.toList());

    long activeCount = beneficiaryRepository.countActiveByCustomerId(customerId);

    log.info("Found {} active beneficiaries for customer: {}", beneficiaryResponses.size(), customerId);

    return new BeneficiaryListResponse(
        beneficiaryResponses,
        beneficiaryResponses.size(),
        (int) activeCount
    );
  }

  @Transactional(readOnly = true)
  public BeneficiaryListResponse searchBeneficiaries(Long customerId, String searchTerm) {
    log.info("Searching beneficiaries for customer: {}, searchTerm: {}", customerId, searchTerm);

    if (!customerRepository.existsById(customerId)) {
      throw new ValidationException("customer.not.found", "Customer not found");
    }

    if (StringUtils.isBlank(searchTerm)) {
      return getAllBeneficiaries(customerId);
    }

    List<Beneficiary> beneficiaries = beneficiaryRepository.searchBeneficiaries(customerId, searchTerm);

    List<BeneficiaryResponse> beneficiaryResponses = beneficiaries.stream()
        .map(BeneficiaryResponse::from)
        .collect(Collectors.toList());

    log.info("Found {} beneficiaries matching search term for customer: {}", beneficiaryResponses.size(), customerId);

    return new BeneficiaryListResponse(
        beneficiaryResponses,
        beneficiaryResponses.size(),
        beneficiaryResponses.stream().filter(BeneficiaryResponse::getIsActive).count()
    );
  }

  @Transactional(readOnly = true)
  public BeneficiaryListResponse getFrequentBeneficiaries(Long customerId) {
    log.info("Fetching frequent beneficiaries for customer: {}", customerId);

    if (!customerRepository.existsById(customerId)) {
      throw new ValidationException("customer.not.found", "Customer not found");
    }

    List<Beneficiary> beneficiaries = beneficiaryRepository.findFrequentBeneficiaries(customerId);

    List<BeneficiaryResponse> beneficiaryResponses = beneficiaries.stream()
        .limit(10)
        .map(BeneficiaryResponse::from)
        .collect(Collectors.toList());

    log.info("Found {} frequent beneficiaries for customer: {}", beneficiaryResponses.size(), customerId);

    return new BeneficiaryListResponse(
        beneficiaryResponses,
        beneficiaryResponses.size(),
        beneficiaryResponses.size()
    );
  }

  @Transactional(readOnly = true)
  public BeneficiaryResponse getBeneficiaryById(Long beneficiaryId, Long customerId) {
    log.info("Fetching beneficiary: {} for customer: {}", beneficiaryId, customerId);

    Beneficiary beneficiary = beneficiaryRepository.findByIdAndCustomerId(beneficiaryId, customerId)
        .orElseThrow(() -> new ValidationException("beneficiary.not.found",
            "Beneficiary not found"));

    return BeneficiaryResponse.from(beneficiary);
  }

  @Transactional
  public BeneficiaryResponse updateBeneficiary(Long beneficiaryId, UpdateBeneficiaryRequest request, Long customerId) {
    log.info("Updating beneficiary: {} for customer: {}", beneficiaryId, customerId);

    request.validate();

    Beneficiary beneficiary = beneficiaryRepository.findByIdAndCustomerId(beneficiaryId, customerId)
        .orElseThrow(() -> new ValidationException("beneficiary.not.found",
            "Beneficiary not found"));

    if (request.getNickname() != null) {
      if (beneficiaryRepository.existsByCustomerIdAndNickname(customerId, request.getNickname())) {
        Optional<Beneficiary> existing = beneficiaryRepository.findByCustomerIdAndNickname(customerId, request.getNickname());
        if (existing.isPresent() && !existing.get().getId().equals(beneficiaryId)) {
          throw new ValidationException("nickname.already.exists",
              "This nickname is already used for another beneficiary");
        }
      }
      beneficiary.setNickname(request.getNickname());
    }

    beneficiary.setLastModifiedAt(LocalDateTime.now());
    Beneficiary updatedBeneficiary = beneficiaryRepository.save(beneficiary);

    log.info("Beneficiary updated successfully: {}", beneficiaryId);

    return BeneficiaryResponse.from(updatedBeneficiary);
  }

  @Transactional
  public void deleteBeneficiary(Long beneficiaryId, Long customerId) {
    log.info("Deleting beneficiary: {} for customer: {}", beneficiaryId, customerId);

    Beneficiary beneficiary = beneficiaryRepository.findByIdAndCustomerId(beneficiaryId, customerId)
        .orElseThrow(() -> new ValidationException("beneficiary.not.found",
            "Beneficiary not found"));

    beneficiary.setIsActive(false);
    beneficiary.setLastModifiedAt(LocalDateTime.now());
    beneficiaryRepository.save(beneficiary);

    log.info("Beneficiary soft deleted successfully: {}", beneficiaryId);
  }

  @Transactional
  public void hardDeleteBeneficiary(Long beneficiaryId, Long customerId) {
    log.info("Hard deleting beneficiary: {} for customer: {}", beneficiaryId, customerId);

    Beneficiary beneficiary = beneficiaryRepository.findByIdAndCustomerId(beneficiaryId, customerId)
        .orElseThrow(() -> new ValidationException("beneficiary.not.found",
            "Beneficiary not found"));

    beneficiaryRepository.delete(beneficiary);

    log.info("Beneficiary hard deleted successfully: {}", beneficiaryId);
  }

  @Transactional
  public void markBeneficiaryAsUsed(String accountNumber, String bankCode, Long customerId) {
    log.info("Marking beneficiary as used - accountNumber: {}, bankCode: {}, customer: {}",
        accountNumber, bankCode, customerId);

    beneficiaryRepository.findByCustomerIdAndAccountNumberAndBankCode(customerId, accountNumber, bankCode)
        .ifPresent(beneficiary -> {
          beneficiary.incrementUsageCount();
          beneficiaryRepository.save(beneficiary);
          log.info("Beneficiary usage count updated: {}", beneficiary.getId());
        });
  }
}