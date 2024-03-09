package org.thibault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

// @EnableCommand (Example.class) - add each command class separately7
@CommandScan
@SpringBootApplication
public class CogipCLI {
  
  public static void main(String[] args) {
    SpringApplication.run(CogipCLI.class, args);
    
  }
}
