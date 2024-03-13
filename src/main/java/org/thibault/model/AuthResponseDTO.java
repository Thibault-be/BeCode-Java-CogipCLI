package org.thibault.model;

import org.springframework.stereotype.Component;

public class AuthResponseDTO {
  
  private String accessToken;
  //private String TokenType = "Bearer ";
  
  private String TokenType;
  
  public AuthResponseDTO(){
    System.out.println("making an authresponsedto");
  }
  
  public AuthResponseDTO(String accessToken, String TokenType) {
    
    this.accessToken = accessToken;
    this.TokenType = TokenType;
  }
  
  public String getAccessToken() {
    System.out.println("getting accessToken");
    
    return accessToken;
  }
  
  public void setAccessToken(String accessToken) {
    System.out.println("setting accesstoken");
    this.accessToken = accessToken;
  }
  
  public String getTokenType() {
    System.out.println("getting tokentype");
    return TokenType;
  }
  
  public void setTokenType(String tokenType) {
    System.out.println("setting tokentype");
    
    TokenType = tokenType;
  }
}
