package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.model.ContactDTO;
import org.thibault.model.joindto.JoinContactDTO;
import org.thibault.proxy.ApiProxy;

import java.util.List;

@RestController
public class ContactController {
  
  private final ApiProxy apiProxy;
  
  public ContactController (ApiProxy apiProxy){
    this.apiProxy = apiProxy;
  }
  
  @GetMapping("/contacts")
  public List<JoinContactDTO> getAllContacts() {
    return this.apiProxy.getAllContacts();
  }
  
  @GetMapping ("/contacts/{id}")
  public ContactDTO getContactById(@PathVariable("id") int id){
    return this.apiProxy.getContactById(id);
  }
  
  @GetMapping ("/contacts/search")
  public List<JoinContactDTO> getContactsByFilters(
          @RequestParam(required = false) Integer id,
          @RequestParam (required = false) String firstname,
          @RequestParam (required = false) String lastname,
          @RequestParam (required = false) String phone,
          @RequestParam (required = false) String companyName
  ){
    return this.apiProxy.getContactsByFilters(id, firstname, lastname, phone, companyName);
  }
  
  @PostMapping("/contacts")
  public ContactDTO addContact(@RequestBody ContactDTO contact){
    return this.apiProxy.addContact(contact);
  }
  
  @PutMapping ("/contacts/{id}")
  public ContactDTO updateContact(@PathVariable("id") int id,
                                  @RequestBody ContactDTO contact){
    return this.apiProxy.updateContact(id, contact);
  }
  
  @DeleteMapping("/contacts/{id}")
  public String deleteContact(@PathVariable int id){
    return this.apiProxy.deleteContact(id);
  }
}
