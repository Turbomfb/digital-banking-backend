/*
 * Copyright (c) 2025 Techservice Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of Techservice Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * Techservice Engineering Team
 * Email: engineering@techservice.com
 */ 
package com.techservices.digitalbanking.core.domain.data.model;

import lombok.*;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AppUser extends AbstractAuthenticationToken {
    private Long userId;
    private String emailAddress;
    private String accessToken;
    private boolean isAuthenticated;
    private boolean isActive;
    private List<String> permissions;

    public AppUser() {
        super(List.of());
        this.isAuthenticated = false;
        this.isActive = false;
        this.permissions = new ArrayList<>();
    }

    public AppUser(Long userId, String emailAddress, String accessToken, boolean isAuthenticated, boolean isActive, List<String> permissions) {
        super(permissions != null
                ? permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList())
                : List.of());
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.accessToken = accessToken;
        this.isAuthenticated = isAuthenticated;
        this.isActive = isActive;
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
}
