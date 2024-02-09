package org.thibault.model;

import java.util.List;

public class Contact {
  
  private String firstName;
  private String lastName;
  private String email;
  private Company company;
  private List<Invoice> invoices;
  
  public Contact(String firstName, String lastName, String email, Company company, List<Invoice> invoices) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.company = company;
    this.invoices = invoices;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Company getCompany() {
    return company;
  }
  
  public void setCompany(Company company) {
    this.company = company;
  }
  
  public List<Invoice> getInvoices() {
    return invoices;
  }
  
  public void setInvoices(List<Invoice> invoices) {
    this.invoices = invoices;
  }
}
