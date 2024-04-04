package com.java.register;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.java.beanModule.RegisterBean;
import com.java.databaseDAO.RegisterDAO;

@SuppressWarnings({ "serial", "unused" })
@WebServlet("/RegisterControler")
public class RegisterControler extends HttpServlet
{	
	     
	     protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	     {
	    	 
	    	 
	    	String uname=req.getParameter("usernamesignup"); // usernamesignup
	    	String email=req.getParameter("emailsignup");
	    	String passwd=req.getParameter("passwordsignup");
	    	String confPasswd=req.getParameter("passwordsignup_confirm");
	    	// System.out.println("UserName:::"+uname);
	    	 RegisterBean bean=new RegisterBean();
	       	 bean.setName(uname);
	       	 bean.setPasswd(passwd);
	       	 bean.setCnfPasswd(confPasswd);
	       	 bean.setEmail(email);
	    	 	 
	    RegisterDAO rDAO=new RegisterDAO();
	    int status=rDAO.insertValue(bean);
	       	 if(status<0)
	       	 {
	       		 RequestDispatcher rd=req.getRequestDispatcher("registerLoginError.jsp");
	       				 rd.forward(req, res);
	       	 }
	       	 else
	       	 {
	       		
	       		 RequestDispatcher rd=req.getRequestDispatcher("index.html");
	       		 //res.getWriter().println("Register Sucessfull");
	       		 rd.forward(req, res);
	       		//res.getWriter().println("Register Sucessfull....");
	       	 }
	     }
	     
	     }