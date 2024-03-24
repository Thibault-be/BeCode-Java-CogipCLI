package org.thibault.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.AuthController;
import org.thibault.controllers.InvoiceController;
import org.thibault.enums.Currency;
import org.thibault.enums.InvoiceStatus;
import org.thibault.enums.InvoiceType;
import org.thibault.enums.converters.EnumConverter;
import org.thibault.model.InvoiceDTO;
import org.thibault.model.joindto.JoinContactDTO;
import org.thibault.model.joindto.JoinInvoiceDTO;

import java.math.BigDecimal;
import java.util.List;

@Command (group = "Invoice commands", description = "Commands related to invoices.")
public class InvoiceCommand {
  
  private final InvoiceController invoiceController;
  
  @Autowired
  private final AuthController authController;
  
  @Autowired
  public InvoiceCommand(InvoiceController invoiceController, AuthController authController){ //, AuthResponseDTO authResponseDTO) {
    this.invoiceController = invoiceController;
    this.authController = authController;
  }
  
  @Command(command = "invoice", description = "Commands for the invoices.")
  public void getInvoiceData(@Option (longNames = "crud", required = true) String crud,
                             @Option (longNames= "id", shortNames = 'i') Integer id,
                             @Option (longNames = {"companyName", "company", "comp"}) String companyName,
                             @Option (longNames = {"companyId", "compId"}) Integer companyId,
                             @Option (longNames = {"contactName", "contact", "cont"}) String contactName,
                             @Option (longNames = {"contactId", "contId"}) Integer contactId,
                             @Option (longNames = {"invoiceNumber","invoice", "inv"}) String invoiceNumber,
                             @Option (longNames = {"value", "val"}, shortNames = 'v')BigDecimal value,
                             @Option (longNames = {"currency", "cur"}, shortNames = 'c') String currency,
                             @Option (longNames = {"invoiceType", "type"}, shortNames = 't') String type,
                             @Option (longNames = {"invoiceStatus", "status"}, shortNames = 's') String status,
                             @Option(longNames = "json", shortNames = 'j') String json){
    
    Currency currencyEnum = new EnumConverter().converStringToCurrency(currency);
    InvoiceType typeEnum = new EnumConverter().convertStringToInvoiceType(type);
    InvoiceStatus statusEnum = new EnumConverter().convertStringToInvoiceStatus(status);
    
    switch (crud){
      case("get"):
        if (id == null && companyName == null && contactName == null && invoiceNumber == null &&
            value == null && currencyEnum == null && typeEnum  == null && statusEnum  == null){
          getAllInvoices(json);
        } else{
          getInvoicesByFilters(id, companyName, invoiceNumber, currencyEnum, typeEnum, statusEnum, contactName, json);
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
  
  private void getAllInvoices(String json){
    List<JoinInvoiceDTO> invoices = this.invoiceController.getAllInvoices();
    printInvoicesList(invoices, json);
  }
  
  private void getInvoicesByFilters(Integer id, String companyName, String invoiceNumber, Currency currency,
                                    InvoiceType type, InvoiceStatus status, String contactName, String json){
    List<JoinInvoiceDTO> filteredInvoices = this.invoiceController.searchInvoicesByFilters(id, companyName,  invoiceNumber, currency,
            type, status, contactName);
    printInvoicesList(filteredInvoices, json);
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
  
  private void printInvoicesList(List<JoinInvoiceDTO> invoices, String json){
    if (json != null && (json.equals("j") || json.equals("json"))){
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      System.out.println(gson.toJson(invoices));
    } else {
      System.out.println("+----------+------------+------------+----------+-----------+--------------------+---------------------+");
      System.out.println("|  Number  |   Value    |  Currency  |  Status  |   Type    |      Company       |       Contact       |");
      System.out.println("+----------+------------+------------+----------+-----------+--------------------+---------------------+");
      for (JoinInvoiceDTO invoice : invoices) {
        System.out.printf("| %7s  | %10.2f |     %3s    |   %4s   | %8s  | %17s  |  %18s |\n", invoice.getInvoiceNumber(),
                                                                          invoice.getValue(),
                                                                          invoice.getCurrency().name(),
                                                                          invoice.getStatus().name(),
                                                                          invoice.getInvoiceType().name(),
                                                                          invoice.getCompanyName(),
                                                                          invoice.getContact()
                                                                                  );
        System.out.println("+----------+------------+------------+----------+-----------+--------------------+---------------------+");
      }
    }
  }
}
