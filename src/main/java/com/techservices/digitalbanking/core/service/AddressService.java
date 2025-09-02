package com.techservices.digitalbanking.core.service;

import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.data.repository.AddressRepository;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.exception.PlatformServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public BasePageResponse<Address> getAddressesByCustomer(Long customerId) {
        return BasePageResponse.instance(addressRepository.findByCustomerId(customerId));
    }

    public Address getAddressByCustomerAndType(Long customerId, String type) {
        return addressRepository.findByCustomerIdAndType(customerId, type)
                .orElseThrow(() -> new PlatformServiceException("address.not-found",
                        "Address with type " + type + " not found for this customer"));
    }

    @Transactional
    public Address createAddress(Address address) {
        if (addressRepository.existsByCustomerIdAndType(address.getCustomerId(), address.getType())) {
            throw new PlatformServiceException("address.address-type.exists",
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
}
