package org.thibault.services;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
  
  private String jwToken;
  
  public AuthService(){  }
  
  public String getJwToken() {
    return jwToken;
  }
  
  public void setJwToken(String jwToken) {
    this.jwToken = jwToken;
  }
}
