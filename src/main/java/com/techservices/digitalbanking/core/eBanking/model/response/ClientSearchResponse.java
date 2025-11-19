/* (C)2025 */
package com.techservices.digitalbanking.core.eBanking.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientSearchResponse {
  private List<ClientSearchData> content;

  @Setter
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ClientSearchData {

    private String displayName;
    private String mobileNo;
    private EnumOptionData status;
  }
}
