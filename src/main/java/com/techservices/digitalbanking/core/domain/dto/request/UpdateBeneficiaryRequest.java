package com.techservices.digitalbanking.core.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.techservices.digitalbanking.core.exception.ValidationException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBeneficiaryRequest {

  private String nickname;

  public void validate() {
    if (StringUtils.isNotBlank(this.nickname) && this.nickname.length() > 25) {
      throw new ValidationException("nickname.too.long", "Nickname cannot exceed 25 characters");
    }
  }
}