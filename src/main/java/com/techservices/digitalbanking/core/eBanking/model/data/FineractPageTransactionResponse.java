/* (C)2024 */
package com.techservices.digitalbanking.core.eBanking.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FineractPageTransactionResponse<E> implements Serializable {

  private int total;
  private List<E> content = Collections.emptyList();
}
