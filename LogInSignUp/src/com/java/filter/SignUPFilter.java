package com.java.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;


@SuppressWarnings("unused") // @SuppressWarnings({ "serial", "unused" })
@WebFilter("/RegisterControler")
public class SignUPFilter implements Filter
{
	
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException
	{
		//HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;

    	String uname=req.getParameter("usernamesignup"); // usernamesignup
    	String email=req.getParameter("emailsignup");
    	String passwd=req.getParameter("passwordsignup");
    	String confPasswd=req.getParameter("passwordsignup_confirm");
		
		if((uname.length()>4)&&(uname.length()<8))
		{
			if((passwd.length()>7)&&(passwd.length()<9))
		{
				chain.doFilter(req, res);	
								
		} }
		else
		{
			response.sendRedirect("registerLoginError.jsp");
			
		}
	}
	
	/*
	public void distroy()
	{ }
	
	public void init(FilterConfig fconfig)throws ServletException
	{ }
*/
}