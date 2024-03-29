package org.thibault.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.commands.exceptionresolver.LoginStatusException;
import org.thibault.commands.exceptionresolver.NoAccessException;
import org.thibault.controllers.AuthController;
import org.thibault.enums.UserRole;
import org.thibault.model.AuthResponseDTO;
import org.thibault.model.UserCredentials;
import org.thibault.proxy.ApiProxy;
import org.thibault.services.AuthService;

@Command (group= "Login", description = "Command to log in to or out of the service.")
public class LoginCommand {
  
  private final AuthController authController;
  private final ApiProxy apiProxy;
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
    
    if (this.authService.getJwToken() != null){
      throw new LoginStatusException("A user is already logged in. Please logout first.");
    }
    
    String basicAuthHeader = "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    
    try{
      ResponseEntity<AuthResponseDTO> authDTO =  this.authController.login(basicAuthHeader,
              new UserCredentials(username, password, UserRole.ADMIN));
      if (authDTO.getStatusCode().is2xxSuccessful()) {
        AuthResponseDTO authResponseDTO = authDTO.getBody();
        if (authResponseDTO != null) {
          System.out.println("Login successful");
        }
      }
    } catch (Exception ex){
      throw new NoAccessException("The entered username and password are not valid.");
    }
  }
  
  @Command (command= "logout", description= "To log out of the system.")
  public void logout(){
    if (authService.getJwToken() == null){
      throw new LoginStatusException("You are not yet logged in.");
    }
    authService.setJwToken(null);
    System.out.println("You've logged out of the service.");
  }
}
