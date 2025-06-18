/* Developed by MKAN Engineering (C)2024 */
package com.techservices.digitalbanking.core.fineract.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.techservices.digitalbanking.core.fineract.model.request.PostClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsClientIdRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PostClientsRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutClientClientIdAddressesRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutClientsClientIdRequest;
import com.techservices.digitalbanking.core.fineract.model.request.PutDataTableRequest;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientAddressTemplateResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdAccountsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetClientsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.GetDataTablesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientClientIdImagesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsClientIdResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PostClientsResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientClientIdAddressesResponse;
import com.techservices.digitalbanking.core.fineract.model.response.PutClientsClientIdResponse;

import jakarta.validation.Valid;

public interface ClientApi {
	@PostMapping(value = "/clients")
	PostClientsResponse create(@RequestBody @Valid PostClientsRequest postClientsRequest);

	@GetMapping(value = "/client/addresses/template")
	GetClientAddressTemplateResponse getAddressesTemplate();

	@PutMapping(value = "/client/{clientId}/addresses")
	PutClientClientIdAddressesResponse updateClientAddress(@PathVariable("clientId") Long clientId,
			@RequestParam(value = "type", required = false) Long type,
			@Valid @RequestBody PutClientClientIdAddressesRequest putClientClientIdAddressesRequest);

	@GetMapping(value = "/client/{clientId}/addresses")
	List<GetClientClientIdAddressesResponse> getClientAddresses(@PathVariable("clientId") Long clientId,
			@Valid @RequestParam(value = "status", required = false) String status,
			@Valid @RequestParam(value = "type", required = false) Long type);

	@PostMapping(value = "/client/{clientId}/addresses")
	PostClientClientIdAddressesResponse addClientAddress(@PathVariable("clientId") Long clientId,
			@Valid @RequestBody PostClientClientIdAddressesRequest postClientClientIdAddressesRequest,
			@Valid @RequestParam(value = "type", required = false) Long type);

	@PutMapping(value = "/clients/{clientId}")
	PutClientsClientIdResponse updateClient(@PathVariable Long clientId,
			@Valid @RequestBody PutClientsClientIdRequest putClientsClientIdRequest);

	@GetMapping(value = "/clients/{clientId}")
	GetClientsClientIdResponse retrieveOne(@PathVariable("clientId") Long clientId,
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly);

	@PostMapping(value = "/clients/{clientId}/images")
	PostClientClientIdImagesResponse uploadImageDataUri(@PathVariable String clientId, String dataUri);

	@PostMapping(value = "/clients/{clientId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	PostClientClientIdImagesResponse uploadImageFile(@PathVariable String clientId, MultipartFile file);

	@GetMapping(value = "/clients")
	GetClientsResponse retrieveAll(@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit);

	@PostMapping(value = "/clients/{clientId}")
	PostClientsClientIdResponse manageClient(@PathVariable("clientId") Long clientId,
			@RequestBody PostClientsClientIdRequest postClientsClientIdRequest,
			@RequestParam(value = "command", required = false) String command);

	@GetMapping(value = "/clients/template")
	String retrieveClientTemplate(@RequestParam(value = "officeId", required = false) Long officeId,
			@Valid @RequestParam(value = "commandParam", required = false) String commandParam,
			@Valid @RequestParam(value = "staffInSelectedOfficeOnly", required = false, defaultValue = "false") Boolean staffInSelectedOfficeOnly);

	@GetMapping(value = "/clients/{clientId}/accounts")
	GetClientsClientIdAccountsResponse retrieveAssociatedAccounts(@PathVariable("clientId") Long clientId,
			@RequestParam(value = "fields", required = false) String fields);

	@GetMapping(value = "/datatables/{registeredTableName}/{clientId}")
	List<GetDataTablesResponse> getClientDataTables(
			@PathVariable(name = "registeredTableName") String registeredTableName,
			@PathVariable(name = "clientId") Long clientId,
			@RequestParam(name = "genericResultSet", defaultValue = "false") String genericResultSet);

	@PutMapping(value = "/datatables/{registeredTableName}/{clientId}")
	PutClientsClientIdResponse updateClientDataTables(
			@PathVariable(name = "registeredTableName") String registeredTableName,
			@PathVariable(name = "clientId") Long clientId,
			@RequestParam(name = "genericResultSet", defaultValue = "true") String genericResultSet,
			@Valid @RequestBody PutDataTableRequest putDataTableRequest);
}
