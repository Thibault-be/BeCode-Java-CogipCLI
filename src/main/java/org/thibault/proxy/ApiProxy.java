package org.thibault.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.thibault.model.CreateUserDTO;
import org.thibault.model.UserDTO;

import java.util.List;

@FeignClient(name = "cogip", url="${name.service.url}")
public interface ApiProxy {
  
  @GetMapping ("/users")
  List<UserDTO> getAllUsers();
  
  @GetMapping ("/users/{id}")
  UserDTO getUserById(@PathVariable("id") int id);
  
  @PostMapping ("/users")
  UserDTO addUser(@RequestBody CreateUserDTO createUserDTO);
  
  @DeleteMapping ("users/{id}")
  String deleteUser(@PathVariable("id") int id);
  
}
