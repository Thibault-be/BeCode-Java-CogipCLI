package org.thibault.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.commands.exceptionresolver.NoAccessException;
import org.thibault.controllers.AuthController;
import org.thibault.enums.UserRole;
import org.thibault.model.AuthResponseDTO;
import org.thibault.model.UserCredentials;
import org.thibault.proxy.ApiProxy;
import org.thibault.services.AuthService;

@Command (group= "Login", description = "Command to log in to the service.")
public class LoginCommand {
  
  private final AuthController authController;
  private final ApiProxy apiProxy;
  
  //new
  private final AuthService authService;
  
  @Autowired
  public LoginCommand(AuthController authController, ApiProxy apiProxy, AuthService authService){
    this.authController = authController;
    this.apiProxy = apiProxy;
    this.authService = authService;
  }
  
  @Command (command = "login", description = "To log in into the system.")
  public void login(@Option (longNames = {"username", "user"}, shortNames = 'u', required = true) String username,
                               @Option (longNames = {"password", "pass"}, shortNames = 'p', required = true) String password) {
    
    String basicAuthHeader = "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    
    try{
      ResponseEntity<AuthResponseDTO> authDTO =  this.authController.login(basicAuthHeader,
              new UserCredentials(username, password, UserRole.ADMIN));
    } catch (Exception e){
      throw new NoAccessException("The entered username and password are not valid.");
    }
    
    ResponseEntity<AuthResponseDTO> authDTO =  this.authController.login(basicAuthHeader,
            new UserCredentials(username, password, UserRole.ADMIN));

    if (authDTO.getStatusCode().is2xxSuccessful()) {
      AuthResponseDTO authResponseDTO = authDTO.getBody();
      if (authResponseDTO != null) {
        System.out.println("Login successful");
      } else {
        System.out.println("Authentication failed. No response received.");
      }
    } else {
      System.out.println("Authentication failed. Status code: " + authDTO.getStatusCodeValue());
    }
  }
  
  @Command (command= "logout", description= "To log out of the system.")
  public void logout(){
    authService.setJwToken(null);
    System.out.println("You've logged out of the service.");
  }
}
