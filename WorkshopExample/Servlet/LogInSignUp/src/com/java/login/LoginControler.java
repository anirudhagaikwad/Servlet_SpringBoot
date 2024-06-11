package com.java.login;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import com.java.databaseDAO.LoginDAO;

@SuppressWarnings({"serial" ,"unused"})
@WebServlet({"/LoginControler"})
public class LoginControler extends HttpServlet
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html;charset=UTF-8");;
		LoginDAO lobj=new LoginDAO();
		String name=req.getParameter("username");
		String pass=req.getParameter("password");
		  
		if(lobj.check(name, pass))
		{
			RequestDispatcher rd=req.getRequestDispatcher("Welcome.html");
			rd.forward(req, res);
			
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.include(req, res);
			//res.getWriter().print("Wrong username and password");
		}
	}//end method
	
}//end class