package org.thibault.proxy;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
  @Bean
  public RequestInterceptor requestInterceptor() {
    return new AuthInterceptor();
  }
}

