package org.thibault.controllers;

import org.springframework.web.bind.annotation.*;
import org.thibault.enums.CompanyType;
import org.thibault.model.CompanyDTO;
import org.thibault.model.joindto.JoinCompanyDTO;
import org.thibault.proxy.ApiProxy;

import java.util.List;

@RestController
public class CompanyController {
  
  private final ApiProxy apiProxy;
  
  public CompanyController(ApiProxy apiProxy){
    this.apiProxy = apiProxy;
  }
  
  @GetMapping("/companies")
  public List<JoinCompanyDTO> getAllCompanies(){
    return this.apiProxy.getAllCompanies();
  }
  
  @GetMapping ("/companies/search")
  public List<JoinCompanyDTO> searchCompaniesByFilters(
          @RequestParam (required = false) Integer id,
          @RequestParam (required = false) String name,
          @RequestParam (required = false) String country,
          @RequestParam (required = false) String vat,
          @RequestParam (required = false) CompanyType type){
    return apiProxy.searchCompaniesByFilters(
            id, name, country, vat, type);
  }
  
  @PostMapping ("/companies/add")
  public CompanyDTO addCompany(@RequestBody CompanyDTO companyDTO){
    return this.apiProxy.addCompany(companyDTO);
  }
  
  @PutMapping("/companies/{id}")
  public CompanyDTO updateCompany(@PathVariable("id") int id,
                                  @RequestBody CompanyDTO companyDTO){
    return this.apiProxy.updateCompany(id, companyDTO);
  }
  
  @DeleteMapping ("/companies/{id}")
  public String deleteCompany(@PathVariable("id") int id){
    return this.apiProxy.deleteCompany(id);
  }
}
