package org.thibault.commands;


import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.CompanyController;
import org.thibault.enums.CompanyType;
import org.thibault.enums.converters.EnumConverter;
import org.thibault.model.CompanyDTO;

import java.sql.SQLOutput;
import java.util.List;

@Command (group= "Company commands", description = "Commands related to company data.")
public class CompanyCommand {
  
  @Autowired
  private CompanyController companyController;
  
  @Command (command = "company", description = "Fetch company information from the database")
  public List<CompanyDTO> getCompanyData(@Option(longNames = "crud", required = true) String crud,
                                         @Option(longNames = "id") Integer id,
                                         @Option(longNames = "name", shortNames = 'n') String name,
                                         @Option(longNames = "country", shortNames = 'c') String country,
                                         @Option(longNames = "vat", shortNames = 'v') String vat,
                                         @Option(longNames = "type", shortNames = 't') String type) {
    CompanyType companyType = new EnumConverter().convertStringToCompanyType(type);
    
    switch (crud) {
      case ("get"):
        if (id == null && name == null && country== null && vat == null && type == null){
          getAllCompanies();
        }else{
          getCompaniesByFilters(id, name, country, vat, companyType);
        }
        break;
      case("post"):
        addCompany(name, country, vat, companyType);
        break;
      case("put"):
        updateCompany(id, name, country, vat, companyType);
        break;
      case("delete"):
        deleteCompany(id);
        break;
    }
    return null;
  }
  
  private void getAllCompanies(){
    this.companyController.getAllCompanies().forEach(companyDTO -> System.out.println(companyDTO));
  }
  
  private void getCompaniesByFilters(Integer id, String name, String country, String vat, CompanyType type){
    this.companyController.searchCompaniesByFilters(id, name, country, vat, type)
            .forEach(companyDTO -> System.out.println(companyDTO));
  }
  
  private void addCompany(String name, String country, String vat, CompanyType type){
    CompanyDTO companyDTO = new CompanyDTO(name, country, vat, type);
    this.companyController.addCompany(companyDTO);
    System.out.println("Company " + companyDTO.getName() + " was successfully added.");
  }
  
  private void updateCompany(int id, String name, String country, String vat, CompanyType type){
    System.out.println("Old information:");
    getCompaniesByFilters(id, null,null, null,null);
    
    CompanyDTO companyToUpdate = new CompanyDTO(name, country, vat, type);
    this.companyController.updateCompany(id, companyToUpdate);
    
    System.out.println("\nNew information:");
    getCompaniesByFilters(id, null,null, null,null);
  }
  
  private void deleteCompany(int id){
    try {
      System.out.println(this.companyController.deleteCompany(id));
    } catch (FeignException.NotFound ex){
      System.out.println(ex.contentUTF8());
    }
  }
  
}
