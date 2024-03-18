package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.enums.UserRole;
import org.thibault.enums.converters.EnumConverter;
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
    return apiProxy.getAllUsers();
  }
  
  @GetMapping("/users/{id}")
  public UserDTO getUserById(@PathVariable("id") int id){
    return apiProxy.getUserById(id);
  }
  
  @GetMapping ("/users/search")
  public List<UserDTO> getUsersByFilters(@RequestParam (required = false) Integer id,
                                         @RequestParam (required = false) String username,
                                         @RequestParam (required = false) String role){
    UserRole userRole = new EnumConverter().convertStringToRole(role);
    return apiProxy.getUsersByFilters(id, username, userRole);
  }
  
  @PostMapping ("/users")
  public UserDTO addUser(@RequestBody CreateUserDTO createUserDTO){
    return this.apiProxy.addUser(createUserDTO);
  }
  
  @PutMapping ("/users/{id}")
  public UserDTO updateUser(@PathVariable int id,
                            @RequestBody CreateUserDTO createUserDTO){
    return this.apiProxy.updateUser(id, createUserDTO);
  }
  
  @DeleteMapping ("/users/{id}")
  public String deleteUser(@PathVariable("id") int id){
    return this.apiProxy.deleteUser(id);
  }
}
