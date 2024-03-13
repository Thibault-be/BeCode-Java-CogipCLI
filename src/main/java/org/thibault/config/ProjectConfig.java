package org.thibault.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thibault.model.AuthResponseDTO;

@Configuration
@ComponentScan("org.thibault")
@EnableFeignClients(basePackages = "org.thibault.proxy")
public class ProjectConfig {
  
  @Value("${cogip.username}")
  private String username;

  @Value("${cogip.password}")
  private String password;
  
  @Bean
  public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
    return new BasicAuthRequestInterceptor(username, password);
  }
  
//  @Bean
//  public AuthResponseDTO authResponseDTO(){
//    return new AuthResponseDTO();
//  }
//
//  @Bean
//  public AuthResponseDTO authResponseDTO() {
//    return new AuthResponseDTO();
//  }
  
}
