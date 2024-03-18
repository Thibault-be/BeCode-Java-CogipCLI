package org.thibault.model;

public class AuthResponseDTO {
  
  private String accessToken;
  private String TokenType;
  
  public AuthResponseDTO(String accessToken, String TokenType) {
    this.accessToken = accessToken;
    this.TokenType = TokenType;
  }
  
  public String getAccessToken() {
    return accessToken;
  }
  
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  
  public String getTokenType() {
    return TokenType;
  }
  
  public void setTokenType(String tokenType) {
    TokenType = tokenType;
  }
}
