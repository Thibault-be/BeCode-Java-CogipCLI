package org.thibault.proxy;

import feign.RequestInterceptor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thibault.controllers.AuthController;

@Configuration
public class FeignClientConfig {
  
  //AuthController authController;
  
  //@Autowired
  //public FeignClientConfig(AuthController authController) {
  //  this.authController = authController;
  // }
  
  @Bean
  public RequestInterceptor requestInterceptor() {
    return new AuthInterceptor();
  }
  

}
  

  
//  @Bean
//  public RequestInterceptor requestInterceptor(){
//    String accessToken = this.authController.getAuthResponseDTO().getAccessToken();
//    return new AuthInterceptor(accessToken);

