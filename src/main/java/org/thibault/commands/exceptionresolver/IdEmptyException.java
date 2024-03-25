package org.thibault.commands.exceptionresolver;

public class IdEmptyException extends RuntimeException {
  
  public IdEmptyException(String message){
    super(message);
  }
}
