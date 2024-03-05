package org.thibault.model;

  
  public class UserDTO {
    
    private int id;
    private String username;
    private String role;
    
    public UserDTO(){}
    
    public UserDTO(int id, String username, String role) {
      this.id = id;
      this.username = username;
      this.role = role;
    }
    
    public String getUsername() {
      return username;
    }
    
    public void setUsername(String username) {
      this.username = username;
    }
    
    public String getRole() {
      return role;
    }
    
    public void setRole(String role) {
      this.role = role;
    }
    
    public int getId(){
      return this.id;
    }
    
    public void setId(int id){
      this.id = id;
    }
  }
  
