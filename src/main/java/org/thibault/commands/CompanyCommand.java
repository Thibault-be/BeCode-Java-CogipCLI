package org.thibault.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;
import org.thibault.controllers.CompanyController;
import org.thibault.enums.CompanyType;
import org.thibault.enums.converters.EnumConverter;
import org.thibault.model.CompanyDTO;
import org.thibault.model.UserDTO;
import org.thibault.model.joindto.JoinCompanyDTO;

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
                                         @Option(longNames = "type", shortNames = 't') String type,
                                         @Option(longNames = "json", shortNames = 'j') String json
  ) {
    CompanyType companyType = new EnumConverter().convertStringToCompanyType(type);
    
    switch (crud) {
      case ("get"):
        if (id == null && name == null && country== null && vat == null && type == null){
          getAllCompanies(json);
        }else{
          getCompaniesByFilters(id, name, country, vat, companyType, json);
        }
        break;
      case("post"):
        addCompany(name, country, vat, companyType);
        break;
      case("put"):
        updateCompany(id, name, country, vat, companyType, json);
        break;
      case("delete"):
        deleteCompany(id);
        break;
    }
    return null;
  }
  
  private void getAllCompanies(String json){
    List<JoinCompanyDTO> companies = this.companyController.getAllCompanies();
    printCompanyList(companies, json);
    
    //this.companyController.getAllCompanies().forEach(joinCompanyDTO -> System.out.println(joinCompanyDTO));
  }
  
  private void getCompaniesByFilters(Integer id, String name, String country, String vat, CompanyType type, String json){
    List<JoinCompanyDTO> filteredCompanies =  this.companyController.searchCompaniesByFilters(id, name, country, vat, type);
    printCompanyList(filteredCompanies, json);
    
    //this.companyController.searchCompaniesByFilters(id, name, country, vat, type).forEach(joinCompanyDTO -> System.out.println(joinCompanyDTO));
  }
  
  private void addCompany(String name, String country, String vat, CompanyType type){
    CompanyDTO companyDTO = new CompanyDTO(name, country, vat, type);
    this.companyController.addCompany(companyDTO);
    System.out.println("Company " + companyDTO.getName() + " was successfully added.");
  }
  
  private void updateCompany(int id, String name, String country, String vat, CompanyType type, String json){
    System.out.println("Old information:");
    getCompaniesByFilters(id, null,null, null,null, json);
    
    CompanyDTO companyToUpdate = new CompanyDTO(name, country, vat, type);
    this.companyController.updateCompany(id, companyToUpdate);
    
    System.out.println("\nNew information:");
    getCompaniesByFilters(id, null,null, null,null, json);
  }
  
  private void deleteCompany(int id){
    try {
      System.out.println(this.companyController.deleteCompany(id));
    } catch (FeignException.NotFound ex){
      System.out.println(ex.contentUTF8());
    }
  }
  
  private void printCompanyList(List<JoinCompanyDTO> companies, String json){
    if (json != null && (json.equals("j") || json.equals("json"))){
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      System.out.println(gson.toJson(companies));
    } else {
      System.out.println("+------+------------------------+------------------+-------------------------+------------+");
      System.out.println("|  id  |         Company        |      Country     |           vat           |    Type    |");
      System.out.println("+------+------------------------+------------------+-------------------------+------------+");
      for (JoinCompanyDTO company : companies) {
        System.out.printf("|  %2s  |  %20s  |  %14s  |  %21s  |  %8s  |\n",
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getVat(),
                company.getType().name());
        System.out.println("+------+------------------------+------------------+-------------------------+------------+");
      }
    }
  }
}
