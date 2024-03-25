package org.thibault.commands.exceptionresolver;

public class LoginStatusException extends RuntimeException {
  
  public LoginStatusException(String message){
    super(message);
  }
}
