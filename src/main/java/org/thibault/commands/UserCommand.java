package org.thibault.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.ExceptionResolver;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.UserController;
import org.thibault.enums.UserRole;
import org.thibault.enums.converters.EnumConverter;
import org.thibault.model.CreateUserDTO;
import org.thibault.model.UserDTO;

import java.util.List;

@Command (group="User commands", description = "Commands related to user data.")
public class UserCommand {
  
  @Autowired
  private UserController userController;
  
  @Command(command = "user", description = "Fetch information from the database")
  public void getData(@Option(longNames = "crud", required = true) String crud,
                      @Option(longNames= "id", shortNames = 'i', required = false, description = "Filter based on id.") Integer id,
                      @Option(longNames = "user", shortNames = 'u', required = false, description = "Filter based on username.") String username,
                      @Option(longNames = "password", shortNames = 'p', required = false, description = "Password for new user") String password,
                      @Option(longNames = "role", shortNames = 'r', required = false, description = "Filter based on role.") String role,
                      @Option(longNames = "json", shortNames = 'j', required = false, description = "print as Json") String json) {
        
    switch (crud) {
      case ("get"):
        if(id == null && username == null && role == null) {
          getAllUsers(json);
        } else {
          getUsersByFilters(id, username, role, json);
        }
        break;
      case ("post"):
        UserRole userRolePost = new EnumConverter().convertStringToRole(role);
        addUser(username, password, userRolePost);
        break;
      case("put"):
        UserRole userRolePut = new EnumConverter().convertStringToRole(role);
        updateUser(id, username, password, userRolePut);
        break;
      case("delete"):
        deleteUser(id);
        break;
    }
  }
  
  private void getAllUsers(String json) {
    List<UserDTO> users = this.userController.getAllUsers();
    printUserList(users, json);
  }
  
  private void getUsersByFilters(Integer id, String username, String role, String json){
    List<UserDTO> filteredUsers = this.userController.getUsersByFilters(id, username, role);
    printUserList(filteredUsers, json);
  }
  
  private void addUser(String username, String password, UserRole role){
    CreateUserDTO newUser = new CreateUserDTO(username, password, role);
    this.userController.addUser(newUser);
    System.out.println("User " +username+ " was created.");
  }
  
  private void updateUser(int id, String username, String password, UserRole role){
    CreateUserDTO userToUpdate = new CreateUserDTO(username, password, role);
    this.userController.updateUser(id, userToUpdate);
    System.out.println("User with id " + id + " was updated.");
  }
  
  private void deleteUser(int id){
    this.userController.deleteUser(id);
    System.out.println("User with id " + id + " has been deleted.");
  }
  
  
  private void printUserList(List<UserDTO> users, String json){
    if (json != null && (json.equals("j") || json.equals("json"))){
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      System.out.println(gson.toJson(users));
    } else {

      System.out.println("+------+----------------+-------------+");
      System.out.println("|  ID  |     Username   |      Role   |");
      System.out.println("+------+----------------+-------------+");
      for (UserDTO user : users) {
        System.out.printf("|  %2d  | %12s   |  %10s |\n", user.getId(), user.getUsername(),user.getRole().name());
        System.out.println("+------+----------------+-------------+");
      }
    }
  }
}

