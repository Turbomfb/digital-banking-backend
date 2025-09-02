package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/me/addresses")
@RequiredArgsConstructor
public class AddressApiResource {

    private final AddressService addressService;
    private final SpringSecurityAuditorAware springSecurityAuditorAware;

    @GetMapping
    public ResponseEntity<BasePageResponse<Address>> getAddresses() {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        return ResponseEntity.ok(addressService.getAddressesByCustomer(customerId));
    }

    @GetMapping("/{type}")
    public ResponseEntity<Address> getAddress(
            @PathVariable String type
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        return ResponseEntity.ok(addressService.getAddressByCustomerAndType(customerId, type));
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(
            @RequestBody Address address
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        address.setCustomerId(customerId);
        return ResponseEntity.ok(addressService.createAddress(address));
    }

    @PutMapping("/{type}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable String type,
            @RequestBody Address updated
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        return ResponseEntity.ok(addressService.updateAddress(customerId, type, updated));
    }

    @DeleteMapping("/{type}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable String type
    ) {
        Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
        addressService.deleteAddress(customerId, type);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/states")
    public ResponseEntity<BasePageResponse<String>> getAllStates() {
        BasePageResponse<String> states = addressService.getAllStates();
        return ResponseEntity.ok(states);
    }

    @GetMapping("/states/{state}/lgas")
    public ResponseEntity<BasePageResponse<String>> getLgasForState(@PathVariable String state) {
        BasePageResponse<String> lgas = addressService.getLgasForState(state);
        return ResponseEntity.ok(lgas);
    }
}
