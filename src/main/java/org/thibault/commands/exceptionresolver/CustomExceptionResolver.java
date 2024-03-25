package org.thibault.commands.exceptionresolver;

import feign.FeignException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class CustomExceptionResolver implements CommandExceptionResolver {
  
  @Override
  public CommandHandlingResult resolve(Exception ex) {
    if (ex instanceof NoAccessException) {
      String errorMessage = ex.getMessage();
      return CommandHandlingResult.of("\u001B[35m"+errorMessage+"\n"+"\u001B[0m", 42);
    }
    if (ex instanceof LoginStatusException){
      String errorMessage = ex.getMessage();
      return CommandHandlingResult.of("\u001B[35m"+errorMessage+"\n"+"\u001B[0m", 42);
    }
    if (ex instanceof FeignException) {
      String errorMessage = extractErrorMessage(ex.getMessage());
      if (errorMessage.isEmpty()) errorMessage = "Access denied.";
      return CommandHandlingResult.of("\u001B[35m"+errorMessage+"\n"+"\u001B[0m", 42);
    }
    if (ex instanceof ConversionFailedException){
      String errorMessage = "You have entered an illegal parameter. Try using explicit flags for data entry.";
      return CommandHandlingResult.of("\u001B[35m"+errorMessage+"\n"+"\u001B[0m", 42);
    }
    if (ex instanceof EnumException){
      String errorMessage = ex.getMessage();
      return CommandHandlingResult.of("\u001B[35m"+errorMessage+"\n"+"\u001B[0m", 42);
    }
    return null;
  }
  
  private String extractErrorMessage(String message){
    String pattern = "\\[([^\\[\\]]*)\\][^\\[\\]]*$";
    Pattern r = Pattern.compile(pattern);
    Matcher m = r.matcher(message);
    
    String lastMatch = "";
    while (m.find()) {
      lastMatch = m.group(1);
    }
    return lastMatch;
  }
}
