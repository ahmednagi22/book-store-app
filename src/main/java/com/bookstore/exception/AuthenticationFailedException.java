package com.bookstore.exception;

public class AuthenticationFailedException extends RuntimeException {

  public AuthenticationFailedException(){
    super("Invalid credentials");
  }
  public AuthenticationFailedException(String message) {
    super(message);
  }

  public AuthenticationFailedException(String message, Throwable cause) {
    super(message, cause);
  }
}
