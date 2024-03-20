package org.thibault.model.joindto;

import java.util.List;

public class JoinContactDTO {
  
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
  private String companyName;
  private List<Integer> invoiceNumbers;
  
  public JoinContactDTO(){}
  
  public JoinContactDTO(String firstname, String lastname, String email, String phone, String companyName, List<Integer> invoiceNumbers) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.phone = phone;
    this.companyName = companyName;
    this.invoiceNumbers = invoiceNumbers;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getFirstname() {
    return firstname;
  }
  
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }
  
  public String getLastname() {
    return lastname;
  }
  
  public void setLastname(String lastname) {
    this.lastname = lastname;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getCompanyName() {
    return companyName;
  }
  
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  
  public List<Integer> getInvoiceNumbers() {
    return invoiceNumbers;
  }
  
  public void setInvoiceNumbers(List<Integer> invoiceNumbers) {
    this.invoiceNumbers = invoiceNumbers;
  }
  
  @Override
  public String toString(){
    return this.id + " - " +
            this.firstname + " " + this.lastname + " - " +
            this.phone  + " - " +
            this.email  + " - " +
            this.companyName  + " - " +
            this.invoiceNumbers;
  }
}
