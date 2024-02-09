package org.thibault.model;

import java.util.Date;

public class Invoice {
  
  private String number;
  private Date date;
  private Company company;
  private Contact contact;
  
  public Invoice(String number, Date date, Company company, Contact contact) {
    this.number = number;
    this.date = date;
    this.company = company;
    this.contact = contact;
  }
  
  public String getNumber() {
    return number;
  }
  
  public void setNumber(String number) {
    this.number = number;
  }
  
  public Date getDate() {
    return date;
  }
  
  public void setDate(Date date) {
    this.date = date;
  }
  
  public Company getCompany() {
    return company;
  }
  
  public void setCompany(Company company) {
    this.company = company;
  }
  
  public Contact getContact() {
    return contact;
  }
  
  public void setContact(Contact contact) {
    this.contact = contact;
  }
}
