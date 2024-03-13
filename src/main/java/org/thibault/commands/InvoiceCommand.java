package org.thibault.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.config.ProjectConfig;
import org.thibault.controllers.InvoiceController;
import org.thibault.enums.Currency;
import org.thibault.enums.InvoiceStatus;
import org.thibault.enums.InvoiceType;
import org.thibault.enums.converters.EnumConverter;
import org.thibault.model.AuthResponseDTO;
import org.thibault.model.InvoiceDTO;

import java.math.BigDecimal;

@Command (group = "Invoice commands", description = "Commands related to invoices.")
public class InvoiceCommand {
  
  
  private AuthResponseDTO authResponseDTO;
  private final InvoiceController invoiceController;
  
  @Autowired
  public InvoiceCommand(InvoiceController invoiceController, AuthResponseDTO authResponseDTO) {
    this.invoiceController = invoiceController;
    this.authResponseDTO = authResponseDTO;
  }
  
  @Command(command = "invoice", description = "Commands for the invoices.")
  public void getInvoiceData(@Option (longNames = "crud", required = true) String crud,
                             @Option (longNames= "id", shortNames = 'i') Integer id,
                             @Option (longNames = {"companyId", "company", "comp"}) Integer companyId,
                             @Option (longNames = {"contactId", "contact", "cont"}) Integer contactId,
                             @Option (longNames = {"invoiceNumber","invoice", "inv"}) String invoiceNumber,
                             @Option (longNames = {"value", "val"}, shortNames = 'v')BigDecimal value,
                             @Option (longNames = {"currency", "cur"}, shortNames = 'c') String currency,
                             @Option (longNames = {"invoiceType", "type"}, shortNames = 't') String type,
                             @Option (longNames = {"invoiceStatus", "status"}, shortNames = 's') String status){
    
    Currency currencyEnum = new EnumConverter().converStringToCurrency(currency);
    InvoiceType typeEnum = new EnumConverter().convertStringToInvoiceType(type);
    InvoiceStatus statusEnum = new EnumConverter().convertStringToInvoiceStatus(status);
    
    switch (crud){
      case("get"):
        if (id == null && companyId == null && contactId == null && invoiceNumber == null &&
            value == null && currencyEnum == null && typeEnum  == null && statusEnum  == null){
          getAllInvoices();
        } else{
          getInvoicesByFilters(id, companyId, invoiceNumber, currencyEnum, typeEnum, statusEnum);
        }
        break;
      case ("post"):
        addInvoice(companyId, contactId, invoiceNumber, value, currencyEnum, typeEnum, statusEnum);
        break;
      case ("put"):
        updateInvoice(id, companyId, contactId, invoiceNumber, value, currencyEnum, typeEnum, statusEnum);
        break;
    }
  }
  
  private void getAllInvoices(){
    System.out.println(this.authResponseDTO.getAccessToken());
    this.invoiceController.getAllInvoices()
            .forEach(invoice -> System.out.println(invoice));
  }
  
  private void getInvoicesByFilters(Integer id, Integer companyId,  String invoiceNumber,
                                     Currency currency, InvoiceType type, InvoiceStatus status){
    this.invoiceController.searchInvoicesByFilters(id, companyId,  invoiceNumber, currency,
            type, status).forEach(System.out::println);
  }
  
  private void addInvoice(int companyId, int contactId, String invoiceNumber, BigDecimal value,
                          Currency currencyEnum, InvoiceType typeEnum, InvoiceStatus statusEnum){
    InvoiceDTO invoiceToAdd = new InvoiceDTO(companyId, contactId, invoiceNumber, value, currencyEnum, typeEnum, statusEnum);
    System.out.println(this.invoiceController.addInvoice(invoiceToAdd));
  }
  
  private void updateInvoice(int id, Integer companyId, Integer contactId, String invoiceNumber, BigDecimal value,
                            Currency currencyEnum, InvoiceType typeEnum, InvoiceStatus statusEnum){
    InvoiceDTO invoiceToUpdate = new InvoiceDTO(companyId, contactId, invoiceNumber, value,
                                      currencyEnum, typeEnum, statusEnum);
    System.out.println(this.invoiceController.updateInvoice(id, invoiceToUpdate));
  }
}
