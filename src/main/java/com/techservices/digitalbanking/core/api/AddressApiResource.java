/* (C)2025 */
package com.techservices.digitalbanking.core.api;

import com.techservices.digitalbanking.core.configuration.security.SpringSecurityAuditorAware;
import com.techservices.digitalbanking.core.domain.data.model.Address;
import com.techservices.digitalbanking.core.domain.dto.BasePageResponse;
import com.techservices.digitalbanking.core.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/me/addresses")
@RequiredArgsConstructor
@Tag(
    name = "Customer Address Management",
    description =
        "APIs for managing customer addresses including CRUD operations and location data retrieval")
@SecurityRequirement(name = "bearerAuth")
public class AddressApiResource {

  private final AddressService addressService;
  private final SpringSecurityAuditorAware springSecurityAuditorAware;

  @GetMapping
  @Operation(
      summary = "Retrieve all addresses for authenticated customer",
      description =
          "Fetches a paginated list of all addresses associated with the currently authenticated customer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved customer addresses",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BasePageResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "Access forbidden - insufficient permissions",
            content = @Content)
      })
  public ResponseEntity<BasePageResponse<Address>> getAddresses() {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(addressService.getAddressesByCustomer(customerId));
  }

  @GetMapping("/{type}")
  @Operation(
      summary = "Retrieve specific address by type",
      description =
          "Fetches a specific address for the authenticated customer based on the address type (e.g., HOME, WORK, BILLING)")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved the address",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Address.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "Address not found for the specified type",
            content = @Content)
      })
  public ResponseEntity<Address> getAddress(
      @Parameter(
              description = "Type of address to retrieve (e.g., HOME, WORK, BILLING)",
              required = true)
          @PathVariable
          String type) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(addressService.getAddressByCustomerAndType(customerId, type));
  }

  @PutMapping("/{type}")
  @Operation(
      summary = "Update existing address",
      description =
          "Updates an existing address for the authenticated customer. Creates a new address if one doesn't exist for the specified type")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Address successfully updated",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Address.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request body or address data",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content)
      })
  public ResponseEntity<Address> updateAddress(
      @Parameter(
              description = "Type of address to update (e.g., HOME, WORK, BILLING)",
              required = true)
          @PathVariable
          String type,
      @Parameter(
              description = "Updated address information",
              required = true,
              content = @Content(schema = @Schema(implementation = Address.class)))
          @RequestBody
          Address updated) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    return ResponseEntity.ok(addressService.updateAddress(customerId, type, updated));
  }

  @DeleteMapping("/{type}")
  @Operation(
      summary = "Delete address by type",
      description =
          "Permanently removes an address of the specified type for the authenticated customer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "204",
            description = "Address successfully deleted",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "Address not found for the specified type",
            content = @Content)
      })
  public ResponseEntity<Void> deleteAddress(
      @Parameter(
              description = "Type of address to delete (e.g., HOME, WORK, BILLING)",
              required = true)
          @PathVariable
          String type) {

    Long customerId = springSecurityAuditorAware.getAuthenticatedUser().getUserId();
    addressService.deleteAddress(customerId, type);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/states/all")
  @Operation(
      summary = "Retrieve all available states",
      description =
          "Fetches a paginated list of all states/provinces available in the system for address selection")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved list of states",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BasePageResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content)
      })
  public ResponseEntity<BasePageResponse<String>> getAllStates() {

    BasePageResponse<String> states = addressService.getAllStates();
    return ResponseEntity.ok(states);
  }

  @GetMapping("/states/{state}/lgas")
  @Operation(
      summary = "Retrieve local government areas for a state",
      description =
          "Fetches a paginated list of all Local Government Areas (LGAs) within the specified state")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved LGAs for the specified state",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BasePageResponse.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Authentication required - invalid or missing token",
            content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "State not found or no LGAs available for the specified state",
            content = @Content)
      })
  public ResponseEntity<BasePageResponse<String>> getLgasForState(
      @Parameter(description = "Name of the state to retrieve LGAs for", required = true)
          @PathVariable
          String state) {

    BasePageResponse<String> lgas = addressService.getLgasForState(state);
    return ResponseEntity.ok(lgas);
  }
}
