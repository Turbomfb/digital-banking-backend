/* (C)2025 */
package com.techservices.digitalbanking.core.domain.data.model;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
public class AppUser extends AbstractAuthenticationToken {
  private Long userId;
  private String emailAddress;
  private String accessToken;
  private boolean isAuthenticated;
  private boolean isActive;
  private UserType userType;
  private List<String> permissions;

  public AppUser() {

    super(List.of());
    this.isAuthenticated = false;
    this.isActive = false;
    this.permissions = new ArrayList<>();
  }

  public AppUser(
      Long userId,
      String emailAddress,
      String accessToken,
      boolean isAuthenticated,
      boolean isActive,
      UserType userType,
      List<String> permissions) {

    super(
        permissions != null
            ? permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            : List.of());
    this.userId = userId;
    this.emailAddress = emailAddress;
    this.accessToken = accessToken;
    this.isAuthenticated = isAuthenticated;
    this.isActive = isActive;
    this.userType = userType;
    this.permissions = new ArrayList<>();
  }

  @Override
  public Object getCredentials() {

    return this.accessToken;
  }

  @Override
  public boolean isAuthenticated() {

    return this.isAuthenticated;
  }

  @Override
  public Object getPrincipal() {

    return this.emailAddress;
  }

  @Override
  public String toString() {

    return new ToStringBuilder(this)
        .append("userId", userId)
        .append("emailAddress", emailAddress)
        .append("accessToken", accessToken)
        .append("isAuthenticated", isAuthenticated)
        .append("isActive", isActive)
        .append("permissions", permissions)
        .toString();
  }
}
