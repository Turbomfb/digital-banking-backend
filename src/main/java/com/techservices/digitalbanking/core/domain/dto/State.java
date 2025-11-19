/* (C)2025 */
package com.techservices.digitalbanking.core.domain.dto;

import java.util.List;

public class State {
  private String state;
  private List<String> lgas;

  // Getters and Setters
  public String getState() {

    return state;
  }

  public void setState(String state) {

    this.state = state;
  }

  public List<String> getLgas() {

    return lgas;
  }

  public void setLgas(List<String> lgas) {

    this.lgas = lgas;
  }
}
