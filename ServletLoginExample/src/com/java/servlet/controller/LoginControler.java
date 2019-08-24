package com.java.servlet.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.java.servlet.DAO.LoginDAO;

@SuppressWarnings({"serial"})
@WebServlet("/LoginControler")
public class LoginControler extends HttpServlet
{
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		LoginDAO obj=new LoginDAO();
		//obj.createDB();
		//obj.createTbl();
		//obj.insertValueTbl();
		
		String user=req.getParameter("num1");
		String pass=req.getParameter("num2");
		
		com.java.servlet.beanModule.BeanLogin bean =new com.java.servlet.beanModule.BeanLogin();
		bean.setUser(user);
		bean.setPasswd(pass);
		
		
		
		if(obj.check(bean))
		{
			RequestDispatcher rd=req.getRequestDispatcher("WelcomeLogin.html");
					rd.forward(req, res);
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.forward(req, res);
		}
	}
	
}
