package org.thibault.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.ContactController;
import org.thibault.model.CompanyDTO;
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
          if (id == null && firstname == null && lastname == null && phone == null && email == null && companyId == null){
            getAllContacts();
          }
          
          break;
        case ("post"):
          break;
        case ("put"):
          break;
        case ("delete"):
          break;
      }
      return null;
    }
    
    private void getAllContacts(){
      this.contactController.getAllContacts()
              .forEach(contact -> System.out.println(contact));
    }
    
    private void getContactsByFilters(){}
    
    private void updateContact(){}
    
    private void deleteContact(){}
    
  }
  

  
  
  

