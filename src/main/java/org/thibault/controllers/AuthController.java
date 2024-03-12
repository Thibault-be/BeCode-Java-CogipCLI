package org.thibault.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.model.LoginDetails;
import org.thibault.proxy.ApiProxy;

@RestController
public class AuthController {
  
  private ApiProxy apiProxy;
  
  public AuthController(ApiProxy apiProxy) {
    this.apiProxy = apiProxy;
  }
  
  @PostMapping ("/api/login")
  public String login(@RequestBody LoginDetails loginDetails){
    return apiProxy.login(loginDetails);
  }
}
