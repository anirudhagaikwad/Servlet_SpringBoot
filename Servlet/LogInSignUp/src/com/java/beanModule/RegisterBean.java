package com.java.beanModule;


public class RegisterBean
{
	 private String username;
     private String password;
     private String cnfPasswdord;
     private String email;
    
    	
     public String getName() {
        return username;
	}

     public void setName(String setNameNew) {
        username=setNameNew;
	}
     
     public String getPasswd()
     {
    	 return password;
     }
     
     public void setPasswd(String pass)
     {
    	 password=pass;
     }
     
     public String getCnfPasswd()
     {
    	 return cnfPasswdord;
     }
     
     public void setCnfPasswd(String cnfpass)
     {
    	 cnfPasswdord=cnfpass;
     }
     
     public String getEmail()
     {
    	 return email;
     }
	
     public void setEmail(String em)
     {
    	 email=em;
     }
}