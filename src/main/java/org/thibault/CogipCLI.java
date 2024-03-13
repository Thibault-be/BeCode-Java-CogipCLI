package org.thibault;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandScan;
import org.thibault.config.ProjectConfig;

@CommandScan
@ComponentScan
@SpringBootApplication
public class CogipCLI {
  
  public static void main(String[] args) {
        SpringApplication.run(CogipCLI.class, args);
    
  }
}
