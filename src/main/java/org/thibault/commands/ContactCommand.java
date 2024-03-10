package org.thibault.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.ContactController;
import org.thibault.model.CompanyDTO;
import org.thibault.model.ContactDTO;

import java.util.List;


  @Command(group= "Contact commands", description = "Commands related to contact data.")
  public class ContactCommand {
    
    private final ContactController contactController;
    
    public ContactCommand(ContactController contactController) {
      this.contactController = contactController;
    }
    
    @Command(command = "contact", description = "Fetch contact information from the database")
    public List<CompanyDTO> getCompanyData(@Option(longNames = "crud", required = true) String crud,
                                           @Option(longNames = "id", shortNames = 'i') Integer id,
                                           @Option(longNames = {"firstname", "first"}, shortNames = 'f') String firstname,
                                           @Option(longNames = {"lastname", "last"}, shortNames = 'l') String lastname,
                                           @Option(longNames = "phone", shortNames = 'p') String phone,
                                           @Option(longNames = "email", shortNames = 'e') String email,
                                           @Option(longNames = {"companyId", "company"}, shortNames = 'c') Integer companyId) {
      
      switch (crud) {
        case ("get"):
          if (id == null && firstname == null && lastname == null && phone == null && companyId == null){
            getAllContacts();
          } else {
            getContactsByFilters(id, firstname, lastname, phone, companyId);
          }
          break;
        case ("post"):
          addContact(firstname, lastname, phone, email, companyId);
          break;
        case ("put"):
          updateContact(id, firstname, lastname, phone, email, companyId);
          break;
        case ("delete"):
          deleteContact(id);
          break;
      }
      return null;
    }
    
    private void getAllContacts(){
      this.contactController.getAllContacts()
              .forEach(contact -> System.out.println(contact));
    }
    
    private void getContactsByFilters(Integer id, String firstname, String lastname, String phone, Integer companyId){
      this.contactController.getContactsByFilters(id, firstname, lastname, phone, companyId)
              .forEach(System.out::println);
    }
    
    private void addContact(String firstname, String lastname, String phone, String email, Integer companyId){
      ContactDTO contactToAdd = new ContactDTO(firstname, lastname, phone, email, companyId);
      System.out.println(this.contactController.addContact(contactToAdd));
    }
    
    
    private void updateContact(int id, String firstname, String lastname, String phone, String email, Integer companyId){
      ContactDTO contactToUpdate = new ContactDTO(firstname, lastname, phone, email, companyId);
      System.out.println(this.contactController.updateContact(id, contactToUpdate));
    }
    
    private void deleteContact(int id){
      System.out.println(this.contactController.deleteContact(id));
    }
    
  }
  

  
  
  

