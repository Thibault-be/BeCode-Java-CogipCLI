package org.thibault.proxy;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.thibault.controllers.AuthController;
import org.thibault.services.AuthService;

public class AuthInterceptor implements RequestInterceptor {
  
  @Autowired
  private AuthService authService;
  
  public AuthInterceptor(){}
  
  public AuthInterceptor(AuthService authService) {
    this.authService = authService;
  }
  
  @Override
  public void apply(RequestTemplate requestTemplate) {
    String token = this.authService.getJwToken();
    requestTemplate.header("Authorization", "Bearer " + token);
  }
  
  

  
}
