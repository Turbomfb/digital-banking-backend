package com.techservices.digitalbanking.walletaccount.domain.data.repository;

import com.techservices.digitalbanking.walletaccount.domain.data.model.PaymentOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, Long> {

    Optional<PaymentOrder> findByReference(String reference);
}
