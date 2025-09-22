package com.techservices.digitalbanking.core.eBanking.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
