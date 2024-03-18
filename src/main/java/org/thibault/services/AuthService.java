package org.thibault.services;

public class AuthService {
  
  String jwToken;
  
  public AuthService (String jwToken){
    this.jwToken = jwToken;
  }
  
  public String getJwToken() {
    return jwToken;
  }
  
  public void setJwToken(String jwToken) {
    this.jwToken = jwToken;
  }
}
