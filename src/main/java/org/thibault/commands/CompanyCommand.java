package org.thibault.commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.CompanyController;
import org.thibault.enums.CompanyType;
import org.thibault.model.CompanyDTO;

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
    switch (crud) {
      case ("get"):
        if (id == null && name == null && country== null && vat == null && type == null){
          return companyController.getAllCompanies();
        }else{
          CompanyType companyType = returnCompanyType(type);
          return this.companyController.searchCompaniesByFilters(id, name, country, vat, companyType);
        }
    }
    return null;
  }
  
  private CompanyType returnCompanyType(String type){
    if (type == null) return null;
    return switch (type.toLowerCase()) {
      case ("null") -> null;
      case ("provider") -> CompanyType.PROVIDER;
      case ("client") -> CompanyType.CLIENT;
      default ->
        //nog exception
              null;
    };
  }
}
