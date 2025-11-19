/* (C)2024 */
package com.techservices.digitalbanking.core.exception;

/** User: ayoade_farooq@yahoo.com Date: 22/05/2023 Time: 22:50 */
public class CustomerAccountNotFoundException extends AbstractPlatformResourceNotFoundException {

  public CustomerAccountNotFoundException(final String identifier) {

    super(
        "error.msg.customer.account.not.found",
        "Customer  account with identifier `" + identifier + "` does not exist",
        identifier);
  }
}
