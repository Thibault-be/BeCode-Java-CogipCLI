package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.model.CreateUserDTO;
import org.thibault.model.UserDTO;
import org.thibault.proxy.ApiProxy;

import java.util.List;

@RestController
public class UserController {
  
  private final ApiProxy apiProxy;
  
  public UserController(ApiProxy apiProxy){
    this.apiProxy = apiProxy;
  }
  
  @GetMapping("/users")
  public List<UserDTO> getAllUsers(){
    System.out.println("getting all users");
    return apiProxy.getAllUsers();
  }
  
  @GetMapping("/users/{id}")
  public UserDTO getUserById(@PathVariable("id") int id){
    return apiProxy.getUserById(id);
  }
  
  @PostMapping ("/users")
  public UserDTO addUser(@RequestBody CreateUserDTO createUserDTO){
    return this.apiProxy.addUser(createUserDTO);
  }
  
  @DeleteMapping ("/users/{id}")
  public String deleteUser(@PathVariable("id") int id){
    return this.apiProxy.deleteUser(id);
  }
  
  
}
