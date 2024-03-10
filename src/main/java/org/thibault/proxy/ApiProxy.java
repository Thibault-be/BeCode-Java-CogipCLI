package org.thibault.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.thibault.enums.CompanyType;
import org.thibault.enums.UserRole;
import org.thibault.model.CompanyDTO;
import org.thibault.model.CreateUserDTO;
import org.thibault.model.UserDTO;

import java.util.List;

@Component
@FeignClient(name = "cogip", url="${name.service.url}")
public interface ApiProxy {
  
  //********USER MAPPING*********//
  @GetMapping ("/users")
  List<UserDTO> getAllUsers();
  
  @GetMapping ("/users/{id}")
  UserDTO getUserById(@PathVariable("id") int id);
  
  @GetMapping ("/users/search")
  List<UserDTO> getUsersByFilters(@RequestParam (required = false) Integer id,
                                  @RequestParam (required = false) String username,
                                  @RequestParam (required = false) UserRole role);
  
  @PostMapping ("/users")
  UserDTO addUser(@RequestBody CreateUserDTO createUserDTO);
  
  @PutMapping ("/users/{id}")
  UserDTO updateUser(@PathVariable int id,
                     @RequestBody CreateUserDTO createUserDTO);
  
  @DeleteMapping ("/users/{id}")
  String deleteUser(@PathVariable("id") int id);
  
  //********COMPANY MAPPING*********//
  @GetMapping("/companies/search")
  List<CompanyDTO> searchCompaniesByFilters(
          @RequestParam (required = false) Integer id,
          @RequestParam (required = false) String name,
          @RequestParam (required = false) String country,
          @RequestParam (required = false) String vat,
          @RequestParam (required = false) CompanyType type);
  
  @GetMapping("/companies")
  List<CompanyDTO> getAllCompanies();
  
  @PostMapping ("/companies/add")
  CompanyDTO addCompany(@RequestBody CompanyDTO companyDTO);
  
  @PutMapping ("/companies/{id}")
  CompanyDTO updateCompany(@PathVariable("id") int id,
                           @RequestBody CompanyDTO companyDTO);
  
  @DeleteMapping ("/companies/{id}")
  public String deleteCompany(@PathVariable("id") int id);
  
  
}
