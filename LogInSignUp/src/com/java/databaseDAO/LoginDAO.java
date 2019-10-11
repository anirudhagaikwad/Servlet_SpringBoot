package com.java.databaseDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.lang.Class;


public class LoginDAO
{
	
	
	public boolean check(String q,String w)
	{ boolean bool=false;
		
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/schoolDB","root","root");
		String sql="select user,passwd from registerLoginTbl where user=? and passwd=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,q);
		ps.setString(2,w);
		ResultSet rs=ps.executeQuery();
		bool=rs.next();
		
		rs.close();
		ps.close();
		con.close();
	}
	catch(SQLException|ClassNotFoundException e)
	{
		System.out.println("Exception in LoginDAO.check()");
	}
		return bool;
	}//end method
}//end class