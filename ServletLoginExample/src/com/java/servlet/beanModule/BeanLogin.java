package com.java.servlet.beanModule;

public class BeanLogin
{
	 private String user;
	 private String passwd;
	
//seter Method
	
	public void setUser(String u)
	{
		user=u;
	}//end setUser() method
	
	public void setPasswd(String p)
	{
		passwd=p;
	}//end setPasswd() method
	
	
//geter Method
	
	public String getUser()
	{
		return user;
	}//end getUser() method
	
	public String getPasswd()
	{
		return passwd;
	}
	
}//end BeanLogin{} class