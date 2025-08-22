package com.techservices.digitalbanking.core.domain.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDataResponse {
    private boolean success;
    private int statusCode;
    private String message;
    private BusinessData data;
    private List<Object> links;


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class BusinessData {
        private String businessId;
        private String parentId;
        private String type;
        private String searchTerm;
        private String searchBusinessName;
        private String name;
        private String formerName;
        private String brandName;
        private String registrationNumber;
        private String registryNumber;
        private String vatNumber;
        private boolean premium;
        private String registrationDate;
        private String registrationSubmissionDate;
        private String dateDisolved;
        private String tin;
        private String jtbTin;
        private String taxOffice;
        private String email;
        private String phone;
        private String websiteEmail;
        private String typeOfEntity;
        private String activity;
        private String address;
        private String state;
        private String lga;
        private String city;
        private String branchAddress;
        private String headOfficeAddress;
        private String objectives;
        private String status;
        private String companyStatus;
        private String companyContactPersons;
        private boolean isConsent;
        private String requestedAt;
        private String requestedById;
        private String lastUpdatedAt;
        private String shareCapitalInWords;
        private String paidShareCapital;
        private String subscribedShareCapital;
        private String sharesValue;
        private String activityDescription;
        private String sharesIssued;
        private String country;
        private String parentCountry;
        private String bulkUploadId;
        private String verificationType;
        private String batchSerialNumber;
        private String amlReport;
        private String adverseMediaReport;
        private String countryCode;
        private String itNumber;
        private String bankName;
        private String branchCode;
        private String branchName;
        private String accountNumber;
        private String accountType;
        private String accountHolder;
        private String id;
    }

}
