package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.enums.Currency;
import org.thibault.enums.InvoiceStatus;
import org.thibault.enums.InvoiceType;
import org.thibault.model.InvoiceDTO;
import org.thibault.model.joindto.JoinInvoiceDTO;
import org.thibault.proxy.ApiProxy;

import java.util.List;

@RestController
public class InvoiceController {
  
  private final ApiProxy apiProxy;
  
  public InvoiceController(ApiProxy apiProxy){
    this.apiProxy = apiProxy;
  }
  
  @GetMapping("/invoices")
  public List<JoinInvoiceDTO> getAllInvoices(){
    return this.apiProxy.getAllInvoices();
  }
  
  @GetMapping ("/invoices/search")
  public List<JoinInvoiceDTO> searchInvoicesByFilters(
          @RequestParam (required = false) Integer id,
          @RequestParam (required = false) String companyName,
          @RequestParam (required = false) String invoiceNumber,
          @RequestParam (required = false) Currency currency,
          @RequestParam (required = false) InvoiceType type,
          @RequestParam (required = false) InvoiceStatus status,
          @RequestParam (required = false) String contactName
  ){
    return this.apiProxy.searchInvoicesByFilters(id, companyName, invoiceNumber, currency, type, status, contactName);
  }
  
  @PostMapping("/invoices")
  public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoice){
    return this.apiProxy.addInvoice(invoice);
  }
  
  @PutMapping ("invoices/{id}")
  public InvoiceDTO updateInvoice(
          @PathVariable("id") int id,
          @RequestBody InvoiceDTO invoiceDTO){
    return this.apiProxy.updateInvoice(id, invoiceDTO);
  }
}
