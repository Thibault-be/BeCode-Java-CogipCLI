package org.thibault.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.model.AuthResponseDTO;
import org.thibault.model.UserCredentials;
import org.thibault.proxy.ApiProxy;

@RestController
public class AuthController {
  
  @Autowired
  private final ApiProxy apiProxy;
  
  public AuthController(ApiProxy apiProxy) {
    this.apiProxy = apiProxy;
  }
  
  @PostMapping ("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestBody UserCredentials userCredentials){
    return this.apiProxy.login(userCredentials);
  }
  
  
}
