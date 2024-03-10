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
  public List<InvoiceDTO> getAllInvoices(){
    return this.apiProxy.getAllInvoices();
  }
  
//  @GetMapping("invoices/{id}")
//  public Invoice getInvoiceById(@PathVariable("id") int id){
//    return this.invoiceService.getInvoiceById(id);
//  }
  
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
  public ResponseEntity<String> addInvoice(@RequestBody InvoiceDTO invoice){
    this.apiProxy.addInvoice(invoice);
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body("Invoice with number " + invoice.getInvoiceNumber() + " was successfully added" );
  }
  
  @PutMapping ("invoices/{id}")
  public ResponseEntity<InvoiceDTO> updateInvoice(
          @PathVariable int id,
          @RequestBody InvoiceDTO invoice){
    InvoiceDTO updatedInvoice = this.apiProxy.updateInvoice(id, invoice);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(updatedInvoice);
  }
  
  
}
