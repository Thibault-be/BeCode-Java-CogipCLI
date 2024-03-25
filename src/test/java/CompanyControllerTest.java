import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thibault.controllers.CompanyController;
import org.thibault.model.CompanyDTO;
import org.thibault.proxy.ApiProxy;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {
  
  @Mock
  private ApiProxy apiProxy;
  
  @InjectMocks
  private CompanyController companyController;
  
  @Test
  void getAllCompaniesIsCalled(){
    companyController.getAllCompanies();
    verify(apiProxy).getAllCompanies();
  }
  
  @Test
  void getCompaniesByFiltersIsCalled(){
    companyController.searchCompaniesByFilters(null, "BeCode", null,null, null);
    verify(apiProxy).searchCompaniesByFilters(null, "BeCode", null,null, null);
  }
  
  @Test
  void addCompanyIsCalled() {
    CompanyDTO company = new CompanyDTO();
    companyController.addCompany(company);
    verify(apiProxy).addCompany(company);
  }
  
  @Test
  void updateCompanyIsCalled() {
    int id = 666;
    CompanyDTO company = new CompanyDTO();
    companyController.updateCompany(id, company);
    verify(apiProxy).updateCompany(id, company);
  }
  
  @Test
  void deleteCompanyIsCalled(){
    int id = 666;
    companyController.deleteCompany(id);
    verify(apiProxy).deleteCompany(id);
  }
}
