/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.eBanking.api;

import java.util.List;
import java.util.Set;

import com.techservices.digitalbanking.core.domain.CustomerDto;
import com.techservices.digitalbanking.core.domain.dto.CustomerFilterDto;
import com.techservices.digitalbanking.core.domain.dto.KycTierDto;
import com.techservices.digitalbanking.core.eBanking.model.request.*;
import com.techservices.digitalbanking.core.eBanking.model.response.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

public interface ClientApi {
	@PostMapping(value = "/customers")
	PostClientsResponse create(@RequestBody @Valid PostClientsRequest postClientsRequest);

	@GetMapping(value = "/customer/addresses/template")
	GetClientAddressTemplateResponse getAddressesTemplate();

	@PutMapping(value = "/customer/{clientId}/addresses")
	PutClientClientIdAddressesResponse updateClientAddress(@PathVariable("clientId") Long clientId,
			@RequestParam(value = "type", required = false) Long type,
			@Valid @RequestBody PutClientClientIdAddressesRequest putClientClientIdAddressesRequest);

	@GetMapping(value = "/customer/{clientId}/addresses")
	List<GetClientClientIdAddressesResponse> getClientAddresses(@PathVariable("clientId") Long clientId,
			@Valid @RequestParam(value = "status", required = false) String status,
			@Valid @RequestParam(value = "type", required = false) Long type);

	@PostMapping(value = "/customer/{clientId}/addresses")
	PostClientClientIdAddressesResponse addClientAddress(@PathVariable("clientId") Long clientId,
			@Valid @RequestBody PostClientClientIdAddressesRequest postClientClientIdAddressesRequest,
			@Valid @RequestParam(value = "type", required = false) Long type);

	@PutMapping(value = "/customers/{clientId}")
	PutClientsClientIdResponse updateClient(@PathVariable String clientId,
			@Valid @RequestBody PutClientsClientIdRequest putClientsClientIdRequest);

	@GetMapping(value = "/customers/{clientId}")
	GetClientsClientIdResponse retrieveOne(@PathVariable("clientId") Long clientId,
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly);


	@PostMapping(value = "/customers/{clientId}/images")
	PostClientClientIdImagesResponse uploadImageDataUri(@PathVariable String clientId, String dataUri);

	@PostMapping(value = "/customers/{clientId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	PostClientClientIdImagesResponse uploadImageFile(@PathVariable String clientId, MultipartFile file);

	@PostMapping(value = "/customers/{clientId}/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	PostClientClientIdDocumentsResponse uploadDocument(
			@PathVariable String clientId,
			@RequestParam("name") String name,
			@RequestParam("description") String description,
			@RequestBody MultipartFile file
	);

	@GetMapping(value = "/customers")
	GetClientsResponse retrieveAll(
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "nin", required = false) String nin,
			@RequestParam(value = "bvn", required = false) String bvn,
			@RequestParam(value = "emailAddress", required = false) String emailAddress,
			@RequestParam(value = "mobileNo", required = false) String mobileNo);

	@PostMapping(value = "/customers/{clientId}")
	PostClientsClientIdResponse manageClient(@PathVariable("clientId") Long clientId,
			@RequestBody PostClientsClientIdRequest postClientsClientIdRequest,
			@RequestParam(value = "command", required = false) String command);

	@GetMapping(value = "/customers/template")
	String retrieveClientTemplate(@RequestParam(value = "officeId", required = false) Long officeId,
			@Valid @RequestParam(value = "commandParam", required = false) String commandParam,
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly);

	@GetMapping(value = "/customers/{clientId}/accounts")
	GetClientsClientIdAccountsResponse retrieveAssociatedAccounts(@PathVariable("clientId") String clientId,
			@RequestParam(value = "fields", required = false) String fields);

	@GetMapping(value = "/customers/{clientId}/savingsaccounts")
	Set<@Valid GetClientsSavingsAccounts> retrieveCustomerSavingsAccounts(@PathVariable("clientId") String clientId);

	@GetMapping(value = "/datatables/{registeredTableName}/{clientId}")
	List<GetDataTablesResponse> getClientDataTables(
			@PathVariable(name = "registeredTableName") String registeredTableName,
			@PathVariable(name = "clientId") Long clientId,
			@RequestParam(name = "genericResultSet", defaultValue = "false") boolean genericResultSet);

	@PutMapping(value = "/datatables/{registeredTableName}/{clientId}")
	PutClientsClientIdResponse updateClientDataTables(
			@PathVariable(name = "registeredTableName") String registeredTableName,
			@PathVariable(name = "clientId") Long clientId,
			@RequestParam(name = "genericResultSet", defaultValue = "true") String genericResultSet,
			@Valid @RequestBody PutDataTableRequest putDataTableRequest);

	@PutMapping(value = "/datatables/{registeredTableName}/{clientId}/{datatableId}")
	PutClientsClientIdResponse updateAClientDataTable(
			@PathVariable(name = "registeredTableName") String registeredTableName,
			@PathVariable(name = "clientId") Long clientId,
			@PathVariable(name = "datatableId") Long datatableId,
			@Valid @RequestBody PutDataTableRequest putDataTableRequest);

	@PostMapping(value = "/datatables/{registeredTableName}/{clientId}")
	PutClientsClientIdResponse postAClientDataTable(
			@PathVariable(name = "registeredTableName") String registeredTableName,
			@PathVariable(name = "clientId") Long clientId,
			@Valid @RequestBody PutDataTableRequest putDataTableRequest
	);

	@GetMapping(value = "/customers/tiers")
	List<KycTierDto> retrieveAllKycTier();

	@PostMapping(value = "/customers/search")
	List<CustomerDto> searchCustomer(
			@RequestBody CustomerFilterDto customerFilterDto
	);
}
