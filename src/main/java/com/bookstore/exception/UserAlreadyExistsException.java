package com.bookstore.exception;

public class UserAlreadyExistsException extends RuntimeException {


    public UserAlreadyExistsException() {
        super("User Already Exist!!");
    }

    public UserAlreadyExistsException(String message) {
    super(message);
  }
  public UserAlreadyExistsException(String message, Throwable cause) {
    super(message,cause);
  }
}
