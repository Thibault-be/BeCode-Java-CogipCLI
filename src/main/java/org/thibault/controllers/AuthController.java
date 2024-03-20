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
import org.thibault.services.AuthService;

@RestController
public class AuthController {
  
  private final ApiProxy apiProxy;
  private AuthService authService;
  
  @Autowired
  public AuthController(ApiProxy apiProxy, AuthService authService) {
    this.apiProxy = apiProxy;
    this.authService = authService;
  }
  
  @PostMapping ("/login")
  public ResponseEntity<AuthResponseDTO> login(@RequestHeader("Authorization") String authorization,
                                               @RequestBody UserCredentials userCredentials) {
    AuthResponseDTO authResponseDTO = this.apiProxy.login(authorization, userCredentials).getBody();
    this.authService.setJwToken(authResponseDTO.getAccessToken());
    System.out.println(authResponseDTO.getAccessToken());
    return this.apiProxy.login(authorization, userCredentials);
  }
}
