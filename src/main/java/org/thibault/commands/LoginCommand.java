package org.thibault.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.config.ProjectConfig;
import org.thibault.controllers.AuthController;
import org.thibault.enums.UserRole;
import org.thibault.model.AuthResponseDTO;
import org.thibault.model.UserCredentials;

@Command (group= "Login", description = "Command to log in to the service.")
public class LoginCommand {
  
  private AuthResponseDTO authResponseDTO;
  private AuthController authController;
  
  @Autowired
  public LoginCommand(AuthResponseDTO authResponseDTO, AuthController authController) {
    this.authResponseDTO = authResponseDTO;
    this.authController = authController;
  }
  
  @Command (command = "login", description = "To log in into the system.")
  public String login(@Option (longNames = {"username", "user"}, shortNames = 'u', required = true) String username,
                               @Option (longNames = {"password", "pass"}, shortNames = 'p', required = true) String password) {
    
    
    UserCredentials userCredentials = new UserCredentials(username, password,  UserRole.ADMIN);
    //ResponseEntity<AuthResponseDTO> authDTO =  this.authController.login(userCredentials);
    
    this.authResponseDTO = this.authController.login(userCredentials).getBody();
    //this.authResponseDTO = authDTO.getBody();
    
    return authResponseDTO.getAccessToken();
    //return authDTO.getBody();
  }
}
