import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thibault.controllers.UserController;
import org.thibault.model.CreateUserDTO;
import org.thibault.model.UserDTO;
import org.thibault.proxy.ApiProxy;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.thibault.enums.UserRole.ADMIN;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

  @Mock
  private ApiProxy apiProxy;
  
  @InjectMocks
  private UserController userController;
  
  @Test
  void getAllUsersIsCalled(){
    List<UserDTO> result = userController.getAllUsers();
    verify(apiProxy).getAllUsers();
  }
  
  @Test
  void getUsersByFiltersIsCalled(){
    List<UserDTO> result = userController.getUsersByFilters(2,"thibault", null );
    verify(apiProxy).getUsersByFilters(2,"thibault", null);
  }
  
  @Test
  void addUserIsCalled(){
    CreateUserDTO user = new CreateUserDTO("thibault", "thibault", ADMIN);
    UserDTO result = userController.addUser(user);
    verify(apiProxy).addUser(user);
  }
  
  @Test
  void updateUserIsCalled(){
    int id = 666;
    CreateUserDTO user = new CreateUserDTO("thibault", "thibault", ADMIN);
    UserDTO result = userController.updateUser(id, user);
    verify(apiProxy).updateUser(id, user);
  }
  
  @Test
  void deleteUserIsCalled(){
    int id = 666;
    userController.deleteUser(id);
    verify(apiProxy).deleteUser(id);
  }
}
