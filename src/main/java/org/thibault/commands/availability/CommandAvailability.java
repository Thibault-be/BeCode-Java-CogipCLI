package org.thibault.commands.availability;

import org.springframework.stereotype.Component;

@Component
public class CommandAvailability {
  
  private boolean available;
  
  public CommandAvailability(){
    this.available = false;
  }
  
  public boolean isAvailable() {
    return available;
  }
  
  public void setAvailable(boolean available) {
    this.available = available;
  }
}
