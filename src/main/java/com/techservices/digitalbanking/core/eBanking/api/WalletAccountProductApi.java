/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import com.techservices.digitalbanking.core.eBanking.model.request.PostSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.request.PutSavingsAccountProductRequest;
import com.techservices.digitalbanking.core.eBanking.model.response.GetSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PostSavingsAccountProductsResponse;
import com.techservices.digitalbanking.core.eBanking.model.response.PutSavingsAccountProductsProductIdResponse;
import java.util.List;
import org.springframework.web.bind.annotation.*;

public interface WalletAccountProductApi {
  @GetMapping(value = "/savingsproducts/{productId}")
  GetSavingsAccountProductsResponse retrieveById(@PathVariable Long productId);

  @GetMapping(value = "/savingsproducts")
  List<GetSavingsAccountProductsResponse> retrieveAll();

  @PostMapping(value = "/savingsproducts")
  PostSavingsAccountProductsResponse createSavingsAccountProduct(
      @RequestBody PostSavingsAccountProductRequest postSavingsAccountProductRequest);

  @PutMapping(value = "/savingsproducts/{productId}")
  PutSavingsAccountProductsProductIdResponse updateSavingsAccountProduct(
      @RequestBody PutSavingsAccountProductRequest putSavingsAccountProductRequest,
      @PathVariable Long productId);
}
