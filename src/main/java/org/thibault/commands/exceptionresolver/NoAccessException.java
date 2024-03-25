package org.thibault.commands.exceptionresolver;

public class NoAccessException extends RuntimeException {
  
  public NoAccessException(String message){
    super(message);
  }
}

