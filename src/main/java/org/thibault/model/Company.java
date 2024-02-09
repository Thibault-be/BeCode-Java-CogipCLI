package org.thibault.model;

import java.util.List;

public class Company {
  
  private String name;
  private String vat;
  private List<Invoice> invoices;
  private List<Contact> contacts;
  private String relation;
  
  public Company(String name, String vat, List<Invoice> invoices, List<Contact> contacts, String relation) {
    this.name = name;
    this.vat = vat;
    this.invoices = invoices;
    this.contacts = contacts;
    this.relation = relation;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getVat() {
    return vat;
  }
  
  public void setVat(String vat) {
    this.vat = vat;
  }
  
  public List<Invoice> getInvoices() {
    return invoices;
  }
  
  public void setInvoices(List<Invoice> invoices) {
    this.invoices = invoices;
  }
  
  public List<Contact> getContacts() {
    return contacts;
  }
  
  public void setContacts(List<Contact> contacts) {
    this.contacts = contacts;
  }
  
  public String getRelation() {
    return relation;
  }
  
  public void setRelation(String relation) {
    this.relation = relation;
  }
}
