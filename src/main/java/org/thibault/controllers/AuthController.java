package org.thibault.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.model.AuthResponseDTO;
import org.thibault.model.UserCredentials;
import org.thibault.proxy.ApiProxy;

@RestController
public class AuthController {
  
  @Autowired
  private final ApiProxy apiProxy;
  
  
  private AuthResponseDTO authResponseDTO;
  
  public AuthController(ApiProxy apiProxy) {
    this.apiProxy = apiProxy;
  }
  
  @PostMapping ("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestHeader("Authorization") String authorization,
          @RequestBody UserCredentials userCredentials){
    
    this.authResponseDTO = this.apiProxy.login(authorization, userCredentials).getBody();
    return this.apiProxy.login(authorization, userCredentials);
  }
  
  public AuthResponseDTO getAuthResponseDTO() {
    return authResponseDTO;
  }
}
