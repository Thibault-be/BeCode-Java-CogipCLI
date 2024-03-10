package org.thibault.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.thibault.enums.*;
import org.thibault.model.*;

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
  String deleteCompany(@PathVariable("id") int id);
  
  //********CONTACT MAPPING*********//
  @GetMapping ("/contacts")
  List<ContactDTO> getAllContacts();
  
  @GetMapping ("/contacts/{id}")
  ContactDTO getContactById(@PathVariable("id") int id);
  
  @GetMapping ("/contacts/search")
  List<ContactDTO> getContactsByFilters(
          @RequestParam (required = false) Integer id,
          @RequestParam (required = false) String firstname,
          @RequestParam (required = false) String lastname,
          @RequestParam (required = false) String phone,
          @RequestParam (required = false) Integer companyId
  );
  
  @PostMapping ("/contacts")
  ContactDTO addContact(@RequestBody ContactDTO contactDTO);
  
  @PutMapping ("contacts/{id}")
  ContactDTO updateContact(@PathVariable("id") int id,
                           @RequestBody ContactDTO contactDTO);
  
  @DeleteMapping ("contacts/{id}")
  String deleteContact(@PathVariable("id") int id);
  
  
  //********INVOICE MAPPING*********//
  @GetMapping ("/invoices")
  List<InvoiceDTO> getAllInvoices();
  
//  @GetMapping("invoices/{id}")
//  InvoiceDTO getInvoiceById(@PathVariable("id") int id);
  
  @GetMapping ("/invoices/search")
  List<InvoiceDTO> searchInvoicesByFilters(
          @RequestParam (required = false) Integer id,
          @RequestParam (required = false) Integer companyId,
          @RequestParam (required = false) String invoiceNumber,
          @RequestParam (required = false) Currency currency,
          @RequestParam (required = false) InvoiceType type,
          @RequestParam (required = false) InvoiceStatus status
  );
  
  @PostMapping ("/invoices")
  ResponseEntity<String> addInvoice(@RequestBody InvoiceDTO invoice);
  
  @PutMapping ("invoices/{id}")
  InvoiceDTO updateInvoice(
          @PathVariable int id,
          @RequestBody InvoiceDTO invoice);
  
}
