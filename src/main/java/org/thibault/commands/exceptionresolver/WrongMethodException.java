package org.thibault.commands.exceptionresolver;

public class WrongMethodException extends RuntimeException {
  
  public WrongMethodException(String message){
    super (message);
  }
}
