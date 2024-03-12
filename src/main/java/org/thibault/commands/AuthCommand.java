package org.thibault.commands;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.AuthController;
import org.thibault.model.LoginDetails;

@Command (group = "Login command", description = "Command to log in")
public class AuthCommand {
  
  private AuthController authController;
  private PasswordEncoder passwordEncoder;
  
  public AuthCommand(AuthController authController, PasswordEncoder passwordEncoder) {
    this.authController = authController;
    this.passwordEncoder = passwordEncoder;
  }
  
  @Command (command= "login")
  public String login(@Option (longNames = {"username", "user"}, shortNames = 'u', required = true) String username,
                      @Option (longNames = {"password", "pass"}, shortNames = 'p', required = true) String password){
    
    String encodedPassword = this.passwordEncoder.encode(password);
    new BasicAuthRequestInterceptor(username, password);
    System.out.println(encodedPassword);
    LoginDetails login = new LoginDetails(username, password);
    
    return this.authController.login(login);
  }
  
}
