/* (C)2025 */
package com.techservices.digitalbanking.walletaccount.domain.data.repository;

import com.techservices.digitalbanking.walletaccount.domain.data.model.PaymentOrder;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, Long> {

  Optional<PaymentOrder> findByReference(String reference);
}
