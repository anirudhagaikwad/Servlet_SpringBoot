package com.java.database;

import  java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet;
import java.lang.Class;


public class CreateDatabaseTable
{
	
	private final String url="jdbc:mysql://localhost/schoolDB";
	private final String usr="root";
	private final String pass="root";
	private final String dbDriver="com.mysql.cj.jdbc.Driver";
	private Connection con=null;
	private PreparedStatement ps=null;
	
	// Create Database schoolDB
	
    public void createDB()
    {
    	try
    	{
    		Class.forName(dbDriver);
    		con=DriverManager.getConnection("jdbc:mysql://localhost",usr,pass);
    		String sql="create database schoolDB";
    		ps=con.prepareStatement(sql);
    		ps.executeUpdate();
    		System.out.println("Sucessfully.."+sql);
    		
    	}
    	catch(SQLException|ClassNotFoundException e)
    	{
    		
    		System.out.println("Exception in CreateDatabaseTable.createDB():::"+e);
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
    			System.out.println("Exception in CreateDatabaseTable.createDB()Finally Block:::"+e);
    			
    		}
    	}
    	
    }//end Method
    
    // created Table registerLoginTbl(id,user,passwd,email)
    
    public void createTbl()
    {
    	try
    	{
    		Class.forName(dbDriver);
    		con=DriverManager.getConnection(url,usr,pass);
    		String str="create table registerLoginTbl(user varchar(10),passwd varchar(10),email varchar(20))";
    		ps=con.prepareStatement(str);
    		ps.executeUpdate();
    		System.out.println("sucessfully..."+str);
    		
    	}
    	catch(SQLException|ClassNotFoundException e)
    	{
    		System.out.println("Exception in CreateDatabaseTable.createTbl():::"+e);
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
    			System.out.println("Exception in CreateDatabaseTable.createTbl() finally Block:::"+e);
    		}
    	}
    }//end method
}//end class