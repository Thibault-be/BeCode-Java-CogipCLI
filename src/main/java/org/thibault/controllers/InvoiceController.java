package org.thibault.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thibault.enums.Currency;
import org.thibault.enums.InvoiceStatus;
import org.thibault.enums.InvoiceType;
import org.thibault.model.InvoiceDTO;
import org.thibault.proxy.ApiProxy;

import java.util.List;

@RestController
public class InvoiceController {
  
  private final ApiProxy apiProxy;
  
  public InvoiceController(ApiProxy apiProxy){
    this.apiProxy = apiProxy;
  }
  
  
  @GetMapping("/invoices")
  public List<InvoiceDTO> getAllInvoices(){ //@RequestHeader("Authorization") String authorization){
    //System.out.println(authorization);
    return this.apiProxy.getAllInvoices() ; //authorization);
  }
  
  @GetMapping ("/invoices/search")
  public List<InvoiceDTO> searchInvoicesByFilters(
          @RequestParam(required = false) Integer id,
          @RequestParam (required = false) Integer companyId,
          @RequestParam (required = false) String invoiceNumber,
          @RequestParam (required = false) Currency currency,
          @RequestParam (required = false) InvoiceType type,
          @RequestParam (required = false) InvoiceStatus status
  ){
    return this.apiProxy.searchInvoicesByFilters(id, companyId, invoiceNumber, currency, type, status);
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
