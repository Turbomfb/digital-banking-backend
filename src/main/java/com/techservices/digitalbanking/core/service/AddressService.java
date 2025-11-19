/* (C)2025 */
package com.techservices.digitalbanking.core.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.domain.dto.State;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

  private final AddressRepository addressRepository;
  private List<State> states = new ArrayList<>();

  @PostConstruct
  public void init() {

    ObjectMapper mapper = new ObjectMapper();
    try {
      log.info("Loading Nigerian states and LGAs from JSON");
      InputStream is = getClass().getResourceAsStream("/nigerianStatesAndLgas.json");
      if (is == null) {
        throw new RuntimeException("JSON file not found in resources");
      }
      log.info("Nigerian states and LGAs JSON file found, parsing...");
      JsonNode root = mapper.readTree(is);
      JsonNode nigeriaNode = root.get("Nigeria");
      if (nigeriaNode != null && nigeriaNode.isArray()) {
        TypeFactory typeFactory = mapper.getTypeFactory();
        states =
            mapper.convertValue(
                nigeriaNode, typeFactory.constructCollectionType(List.class, State.class));
        log.info("Successfully loaded {} states", states.size());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error loading Nigerian states and LGAs from JSON", e);
    }
  }

  public BasePageResponse<Address> getAddressesByCustomer(Long customerId) {

    return BasePageResponse.instance(addressRepository.findByCustomerId(customerId));
  }

  public Address getAddressByCustomerAndType(Long customerId, String type) {

    return addressRepository
        .findByCustomerIdAndType(customerId, type)
        .orElseThrow(
            () ->
                new PlatformServiceException(
                    "address.not-found",
                    "Address with type " + type + " not found for this customer"));
  }

  @Transactional
  public Address createAddress(Address address) {

    if (addressRepository.existsByCustomerIdAndType(address.getCustomerId(), address.getType())) {
      throw new PlatformServiceException(
          "address.address-type.exists",
          "Address with type " + address.getType() + " already exists for this customer");
    }
    return addressRepository.save(address);
  }

  @Transactional
  public Address updateAddress(Long customerId, String type, Address updated) {

    Address existing = getAddressByCustomerAndType(customerId, type);

    existing.setLga(updated.getLga());
    existing.setTown(updated.getTown());
    existing.setAddressLine(updated.getAddressLine());
    existing.setState(updated.getState());

    return addressRepository.save(existing);
  }

  @Transactional
  public void deleteAddress(Long customerId, String type) {

    Address existing = getAddressByCustomerAndType(customerId, type);
    addressRepository.delete(existing);
  }

  public BasePageResponse<String> getAllStates() {

    return BasePageResponse.instance(
        states.stream().map(State::getState).collect(Collectors.toList()));
  }

  public BasePageResponse<String> getLgasForState(String stateName) {

    Optional<State> stateOpt =
        states.stream().filter(s -> s.getState().equalsIgnoreCase(stateName)).findFirst();
    return BasePageResponse.instance(stateOpt.map(State::getLgas).orElse(Collections.emptyList()));
  }
}
