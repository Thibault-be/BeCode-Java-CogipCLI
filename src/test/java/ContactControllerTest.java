import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thibault.controllers.CompanyController;
import org.thibault.controllers.ContactController;
import org.thibault.model.CompanyDTO;
import org.thibault.model.ContactDTO;
import org.thibault.proxy.ApiProxy;

import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {
  
  @Mock
  private ApiProxy apiProxy;
  
  @InjectMocks
  private ContactController contactController;
  
  @Test
  void getAllContactsIsCalled(){
    contactController.getAllContacts();
    verify(apiProxy).getAllContacts();
  }
  
  @Test
  void getCompaniesByFiltersIsCalled(){
    contactController.getContactsByFilters(null, "Thibault", null,null, null);
    verify(apiProxy).getContactsByFilters(null, "Thibault", null,null, null);
  }
  
  @Test
  void addContactIsCalled() {
    ContactDTO contact = new ContactDTO();
    contactController.addContact(contact);
    verify(apiProxy).addContact(contact);
  }
  
  @Test
  void updateContactIsCalled() {
    int id = 666;
    ContactDTO contact = new ContactDTO();
    contactController.updateContact(id, contact);
    verify(apiProxy).updateContact(id, contact);
  }
  
  @Test
  void deleteCompanyIsCalled(){
    int id = 666;
    contactController.deleteContact(id);
    verify(apiProxy).deleteContact(id);
  }
}
