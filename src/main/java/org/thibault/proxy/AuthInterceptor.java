package org.thibault.proxy;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.thibault.controllers.AuthController;

public class AuthInterceptor implements RequestInterceptor {
  
  private AuthController authController;
  
  public AuthInterceptor(AuthController authController) {
    this.authController = authController;
  }
  
  
  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header("Authorization", "Bearer " + this.authController.getAuthResponseDTO().getAccessToken());
  }
  
  

  
}
