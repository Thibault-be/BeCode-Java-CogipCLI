package org.thibault.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.commands.exceptionresolver.IdEmptyException;
import org.thibault.commands.exceptionresolver.LoginStatusException;
import org.thibault.commands.exceptionresolver.WrongMethodException;
import org.thibault.controllers.ContactController;
import org.thibault.model.CompanyDTO;
import org.thibault.model.ContactDTO;
import org.thibault.model.joindto.JoinContactDTO;
import org.thibault.services.AuthService;

import java.util.List;


  @Command(group= "Contact commands", description = "Commands related to contact data.")
  public class ContactCommand {
    
    private final ContactController contactController;
    private final AuthService authService;
    
    public ContactCommand(ContactController contactController, AuthService authService ) {
      this.contactController = contactController;
      this.authService = authService;
    }
    
    @Command(command = "contact", description = "Fetch contact information from the database")
    public List<CompanyDTO> getCompanyData(@Option(longNames = "crud", required = true) String crud,
                                           @Option(longNames = "id", shortNames = 'i') Integer id,
                                           @Option(longNames = {"firstname", "first"}, shortNames = 'f') String firstname,
                                           @Option(longNames = {"lastname", "last"}, shortNames = 'l') String lastname,
                                           @Option(longNames = "phone", shortNames = 'p') String phone,
                                           @Option(longNames = "email", shortNames = 'e') String email,
                                           @Option(longNames = {"companyId", "compId"}) Integer companyId,
                                           @Option(longNames = {"companyName", "compName"}, shortNames = 'c') String companyName,
                                           @Option(longNames = "json", shortNames = 'j') String json) {
      
      if (authService.getJwToken() == null){
        throw new LoginStatusException("Please login first.");
      }
      switch (crud) {
        case ("get"):
          if (id == null && firstname == null && lastname == null && phone == null && companyName == null){
            getAllContacts(json);
          } else {
            getContactsByFilters(id, firstname, lastname, phone, companyName, json);
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
        default:
          throw new WrongMethodException("Please enter a correct command: get, post, put or delete");
      }
      return null;
    }
    
    private void getAllContacts(String json){
      List<JoinContactDTO> contacts = this.contactController.getAllContacts();
      printContactsList(contacts, json);
    }
    
    private void getContactsByFilters(Integer id, String firstname, String lastname, String phone, String companyName, String json){
      List<JoinContactDTO> filteredContacts = this.contactController.getContactsByFilters(id, firstname, lastname, phone, companyName);
      printContactsList(filteredContacts, json);
    }
    
    private void addContact(String firstname, String lastname, String phone, String email, Integer companyId){
      ContactDTO contactToAdd = new ContactDTO(firstname, lastname, phone, email, companyId);
      System.out.println(this.contactController.addContact(contactToAdd));
    }
    
    private void updateContact(Integer id, String firstname, String lastname, String phone, String email, Integer companyId){
      
      if (id == null || !(id instanceof Integer)) throw new IdEmptyException("Please enter the id of the contact that you want to update");
      ContactDTO contactToUpdate = new ContactDTO(firstname, lastname, phone, email, companyId);
      System.out.println(this.contactController.updateContact(id, contactToUpdate));
    }
    
    private void deleteContact(int id){
      System.out.println(this.contactController.deleteContact(id));
    }
    
    private void printContactsList(List<JoinContactDTO> contacts, String json) {
      if (json != null && (json.equals("j") || json.equals("json"))) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(contacts));
      } else {
        System.out.println("+---------------+-------------------+--------------------------------------+----------------+---------------------+");
        System.out.println("|  First name   |     Last name     |                Email                 |     Phone      |        Company      |");
        System.out.println("+---------------+-------------------+--------------------------------------+----------------+---------------------+");
        for (JoinContactDTO contact : contacts) {
          
          System.out.printf("| %12s  | %15s   | %36s  | %12s  | %18s  |  ",
                  contact.getFirstname(),
                  contact.getLastname(),
                  contact.getEmail(),
                  contact.getPhone(),
                  contact.getCompanyName());
          System.out.println("\n+---------------+-------------------+--------------------------------------+----------------+---------------------+");
        }
      }
    }
  }
  

  
  
  

