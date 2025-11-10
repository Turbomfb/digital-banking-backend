/*
 * Copyright (c) 2025 TechService Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of TechService Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * TechService Engineering Team
 * Email: engineering@techservicehub.io
 */
package com.techservices.digitalbanking.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Filter criteria for searching customers. All fields are optional and can be combined for flexible
 * customer search functionality.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilterDto {

  private String customerId;
  private String bvn;
  private String email;
  private String phoneNumber;
  private String firstName;
  private String lastName;
  private String customerType;
  private String status;
  private LocalDate dateOfBirthFrom;
  private LocalDate dateOfBirthTo;
  private String city;
  private String state;
  private String country;

  public CustomerFilterDto bvn(String bvn) {
    this.bvn = bvn;
    return this;
  }
}
