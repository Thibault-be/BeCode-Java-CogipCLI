package org.thibault.proxy;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.thibault.services.AuthService;

public class AuthInterceptor implements RequestInterceptor {
  
  @Autowired
  private AuthService authService;
  
  @Override
  public void apply(RequestTemplate requestTemplate) {
    String token = this.authService.getJwToken();
    requestTemplate.header("Authorization", "Bearer " + token);
  }
}
