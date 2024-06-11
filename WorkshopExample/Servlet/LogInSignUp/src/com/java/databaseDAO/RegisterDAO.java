package com.java.databaseDAO;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Class;

import com.java.beanModule.RegisterBean;

//Database : schoolDB
//Table : registerLoginTbl(id,user,passwd,email)
//Table : loginTbl

public class RegisterDAO
{
	Connection con=null;
	PreparedStatement ps=null;
	
	public void connectionManager()
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost/schoolDB","root","root");
		}
		catch(SQLException|ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}//end method

	
	
	public int insertValue(RegisterBean b)
	{
		int status=0;
		try
		{
		connectionManager();
		String sql="insert into schoolDB.registerLoginTbl(user,passwd,email) value(?,?,? )";
		ps=con.prepareStatement(sql);
		ps.setString(1,b.getName());
		ps.setString(2,b.getPasswd());
		ps.setString(3,b.getEmail());
		status=ps.executeUpdate();
		
		}
		
		catch(SQLException e)
		{
			
			System.out.println("Exception in RegisterDAO.insertValue()::::"+e);
		}
		
		finally
		{
			try
			{
				if(ps!=null&&con!=null)
				{
					ps.close();
					con.close();
									}
							}
			catch(SQLException e)
			{
				
				System.out.println("Exception in RegisterDAO.insertValue()Finally Block::::"+e);
			}
		}
		return status;
	}//end method
	
	/*
	public void testName(RegisterBean name)
	{
		String s=name.getName();
		System.out.println("Input value is :::"+s);
	
	}
	*/

}//end class
	
