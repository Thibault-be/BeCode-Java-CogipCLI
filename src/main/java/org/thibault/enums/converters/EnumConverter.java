package org.thibault.enums.converters;

import org.thibault.commands.exceptionresolver.EnumException;
import org.thibault.enums.*;

public class EnumConverter {
  
  public UserRole convertStringToRole(String role){
    if (role == null) return null;
    if (role.equalsIgnoreCase("admin")){
      return UserRole.ADMIN;
    }
    if (role.equalsIgnoreCase("accountant")){
      return UserRole.ACCOUNTANT;
    }
    if (role.equalsIgnoreCase("intern")){
      return UserRole.INTERN;
    }
    throw new EnumException("When filtering for 'Role', please make sure to enter 'admin', accountant' or 'intern'.");
  }
  
  public CompanyType convertStringToCompanyType(String type){
    if (type == null) return null;
    if (type.equalsIgnoreCase("client")) return CompanyType.CLIENT;
    if (type.equalsIgnoreCase("provider")) return CompanyType.PROVIDER;
    throw new EnumException("When filtering for 'Company Type', please make sure to enter 'client' or 'provider'.");
  }
  
  public Currency converStringToCurrency(String currency){
    if (currency == null) return null;
    if (currency.equalsIgnoreCase("eur")) return Currency.EUR;
    if (currency.equalsIgnoreCase("usd")) return Currency.USD;
    throw new EnumException("When filtering for 'Currency', please make sure to enter 'usd' or 'eur'.");
  }
  
  public InvoiceStatus convertStringToInvoiceStatus(String status){
    if (status == null) return null;
    if (status.equalsIgnoreCase("open")) return InvoiceStatus.OPEN;
    if (status.equalsIgnoreCase("paid")) return InvoiceStatus.PAID;
    throw new EnumException("\"When filtering for 'Invoice Status', please make sure to enter 'paid' or 'open'.");
  }
  
  public InvoiceType convertStringToInvoiceType (String type){
    if (type == null) return null;
    if (type.equalsIgnoreCase("incoming")) return InvoiceType.INCOMING;
    if (type.equalsIgnoreCase("outgoing")) return InvoiceType.OUTGOING;
    throw new EnumException("When filtering for 'Invoice Type', please make sure to enter 'incoming' or 'outgoing'.");
  }
}
