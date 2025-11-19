/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import com.techservices.digitalbanking.core.domain.dto.AccountDto;
import com.techservices.digitalbanking.core.domain.dto.CustomerDto;
import com.techservices.digitalbanking.core.domain.dto.CustomerFilterDto;
import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import com.techservices.digitalbanking.core.eBanking.model.request.*;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import com.techservices.digitalbanking.investment.domain.enums.InvestmentType;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerApi {
  @PostMapping(value = "/customers")
  PostClientsResponse create(@RequestBody @Valid PostClientsRequest postClientsRequest);

  @PutMapping(value = "/customer/{externalId}/addresses")
  PutClientClientIdAddressesResponse updateClientAddress(
      @PathVariable("externalId") Long externalId,
      @RequestParam(value = "type", required = false) Long type,
      @Valid @RequestBody PutClientClientIdAddressesRequest putClientClientIdAddressesRequest);

  @GetMapping(value = "/customer/{externalId}/addresses")
  List<GetClientClientIdAddressesResponse> getClientAddresses(
      @PathVariable("externalId") Long externalId,
      @Valid @RequestParam(value = "status", required = false) String status,
      @Valid @RequestParam(value = "type", required = false) Long type);

  @PostMapping(value = "/customer/{externalId}/addresses")
  PostClientClientIdAddressesResponse addClientAddress(
      @PathVariable("externalId") Long externalId,
      @Valid @RequestBody PostClientClientIdAddressesRequest postClientClientIdAddressesRequest,
      @Valid @RequestParam(value = "type", required = false) Long type);

  @PutMapping(value = "/customers/{externalId}")
  PutClientsClientIdResponse updateClient(
      @PathVariable String externalId,
      @Valid @RequestBody PutClientsClientIdRequest putClientsClientIdRequest);

  @GetMapping(value = "/customers/{externalId}")
  GetClientsClientIdResponse retrieveOne(@PathVariable("externalId") Long externalId);

  @GetMapping(value = "/customers")
  GetClientsResponse retrieveAll(
      @RequestParam(value = "offset", required = false) Integer offset,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "nin", required = false) String nin,
      @RequestParam(value = "bvn", required = false) String bvn,
      @RequestParam(value = "emailAddress", required = false) String emailAddress,
      @RequestParam(value = "mobileNo", required = false) String mobileNo);

  @PostMapping(value = "/customers/{externalId}")
  PostClientsClientIdResponse manageClient(
      @PathVariable("externalId") Long externalId,
      @RequestBody PostClientsClientIdRequest postClientsClientIdRequest,
      @RequestParam(value = "command", required = false) String command);

  @GetMapping(value = "/customers/{externalId}/accounts")
  List<@Valid AccountDto> retrieveCustomerAccounts(
      @PathVariable("externalId") String externalId, @RequestParam InvestmentType accountType);

  @GetMapping(value = "/customers/tiers")
  List<KycTierDto> retrieveAllKycTier();

  @PostMapping(value = "/customers/search")
  List<CustomerDto> searchCustomer(@RequestBody CustomerFilterDto customerFilterDto);
}
