package org.thibault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.command.annotation.CommandScan;


@CommandScan
@ComponentScan
@SpringBootApplication
public class CogipCLI {
  
  public static void main(String[] args) {
    
    SpringApplication.run(CogipCLI.class, args);
    
  }
}
