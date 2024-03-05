package org.thibault.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
}
