import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thibault.controllers.InvoiceController;
import org.thibault.model.InvoiceDTO;
import org.thibault.proxy.ApiProxy;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {
  
  @Mock
  private ApiProxy apiProxy;
  
  @InjectMocks
  private InvoiceController invoiceController;
  
  @Test
  void getAllInvoicesIsCalled(){
    invoiceController.getAllInvoices();
    verify(apiProxy).getAllInvoices();
  }
  
  @Test
  void getInvoicesByFiltersIsCalled(){
    invoiceController.searchInvoicesByFilters(null, "BeCode", null, null,null,null,null);
    verify(apiProxy).searchInvoicesByFilters(null, "BeCode", null, null,null,null,null);
  }
  
  @Test
  void addInvoiceIsCalled() {
    InvoiceDTO invoice = new InvoiceDTO();
    invoiceController.addInvoice(invoice);
    verify(apiProxy).addInvoice(invoice);
  }
  
  @Test
  void updateInvoiceIsCalled() {
    int id = 666;
    InvoiceDTO invoice = new InvoiceDTO();
    invoiceController.updateInvoice(id, invoice);
    verify(apiProxy).updateInvoice(id, invoice);
  }
}
