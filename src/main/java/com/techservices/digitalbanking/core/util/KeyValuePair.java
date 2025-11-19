/* (C)2024 */
package com.techservices.digitalbanking.core.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValuePair<K, V> {

  private K key;
  private V value;

  public KeyValuePair(K key, V value) {

    this.key = key;
    this.value = value;
  }
}
