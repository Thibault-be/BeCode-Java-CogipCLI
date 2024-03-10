package org.thibault.enums.converters;

import org.thibault.enums.CompanyType;
import org.thibault.enums.UserRole;

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
    return null;
  }
  
  public CompanyType convertStringToCompanyType(String type){
    if (type == null) return null;
    if (type.equalsIgnoreCase("client")) return CompanyType.CLIENT;
    if (type.equalsIgnoreCase("provider")) return CompanyType.PROVIDER;
    return null;
  }
  
  
  
}
