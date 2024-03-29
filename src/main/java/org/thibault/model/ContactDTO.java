package org.thibault.model;

public class ContactDTO {
  
  private Integer id;
  private String firstname;
  private String lastname;
  private String phone;
  private String email;
  private Integer companyId;
  
  public ContactDTO() { }
  
  public ContactDTO( String firstname, String lastname, String phone, String email, Integer companyId) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;
    this.email = email;
    this.companyId = companyId;
  }
  
  public ContactDTO(int id, String firstname, String lastname, String phone, String email, Integer companyId) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phone = phone;
    this.email = email;
    this.companyId = companyId;
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
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public Integer getCompanyId() {
    return companyId;
  }
  
  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }
  
  @Override
  public String toString(){
    return this.id + " - " + this.firstname + " - " + this.lastname + " - " + this.phone + " - " + this.email + " - " + this.companyId;
  }
}
