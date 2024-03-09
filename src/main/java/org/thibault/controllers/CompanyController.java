package org.thibault.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thibault.enums.CompanyType;
import org.thibault.model.CompanyDTO;
import org.thibault.proxy.ApiProxy;

import java.util.List;

@RestController
public class CompanyController {
  
  private final ApiProxy apiProxy;
  
  public CompanyController(ApiProxy apiProxy){
    this.apiProxy = apiProxy;
  }
  
  @GetMapping("/companies")
  public List<CompanyDTO> getAllCompanies(){
    return this.apiProxy.getAllCompanies();
  }
  
  @GetMapping ("/companies/search")
  public List<CompanyDTO> searchCompaniesByFilters(
          @RequestParam (required = false) Integer id,
          @RequestParam (required = false) String name,
          @RequestParam (required = false) String country,
          @RequestParam (required = false) String vat,
          @RequestParam (required = false) CompanyType type){
    return apiProxy.searchCompaniesByFilters(
            id, name, country, vat, type);
  }



}
