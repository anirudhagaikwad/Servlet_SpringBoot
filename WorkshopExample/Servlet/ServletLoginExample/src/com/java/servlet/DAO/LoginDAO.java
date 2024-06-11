package com.java.servlet.DAO;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.servlet.beanModule.BeanLogin;

import java.lang.Class;

/*
 * Database created ::: value of i = 1
* Table created sucessfully..value of i= 0
* Table Value Inserted sucessfully..value of i= 1
 * 
 * 
 */

public class LoginDAO
{
	private String usr="root";  //MYSQL username
	private String pass="root"; // MYSQL password
	private String dbDriver="com.mysql.cj.jdbc.Driver"; //MYSQL 8.0 Driver
	private String url="jdbc:mysql://localhost/logindb";
	Connection con=null;
	PreparedStatement ps=null;
	
	public void createDB()
	{
		try
		{
			Class.forName(dbDriver);
			con=DriverManager.getConnection("jdbc:mysql://localhost",usr,pass);
			String sql="create database logindb";
			ps=con.prepareStatement(sql);
			int i=ps.executeUpdate();
			System.out.println("Database created ::: value of i = "+i);
			/* if(i!=0)
			{
			System.out.println("logindb :: database created sucessfully....");
		}
			else { System.out.println("logindb :: not created....");} */
		}
		catch(SQLException|ClassNotFoundException e)
		{
			System.out.println("have issue in LoginDAO.createDB() :::"+e);
		}
		finally
		{
			try
			{
				if((ps!=null)&&(con!=null))
						{
					ps.close();
					con.close();
						}
			}
			catch(SQLException e)
			{
				System.out.println("have issue in LoginDAO.createDB() Finnaly Block :::");
			}
		}
		
	}// end createDB() Method
	
	public void createTbl()
	{
		try
		{
			Class.forName(dbDriver);
			con=DriverManager.getConnection(url,usr,pass);
			String sql="create table logintbl(user varchar(15),passwd varchar(10))";
			ps=con.prepareStatement(sql);
			int i=ps.executeUpdate();
			System.out.println("Table created sucessfully..value of i= "+i);
		}
		catch(SQLException|ClassNotFoundException e)
		{
			System.out.println("have issue in LoginDAO.createTbl() ::: "+e);
		}
		finally
		{
			try
			{
				if((ps!=null)&&(con!=null))
				{
					ps.close();
					con.close();
				}
			}
			catch(SQLException e)
			{
				System.out.println("have issue in LoginDAO.createTbl() finally block ::: "+e);
			}
		}
		
	}//end createTbl() Method
	
	public void insertValueTbl1()
	{
		try
		{
		java.util.Scanner sc=new java.util.Scanner(System.in);
		System.out.println("Enter Username::");
		String u=sc.nextLine();
		System.out.println("Enter Username::");
		String p=sc.nextLine();
		Class.forName(dbDriver);
		con=DriverManager.getConnection(url,usr,pass);
		String sql="insert into logindb.logintbl(user,passwd) value("+u+","+p+")";
		ps=con.prepareStatement(sql);
		int i=ps.executeUpdate();
		System.out.println("Table Value Inserted sucessfully..value of i= "+i);
		sc.close();
		}
		catch(SQLException|ClassNotFoundException e)
		{
			System.out.println("have issue in LoginDAO.insertValueTbl() ::: "+e);
		}
		finally
		{
			try
			{
				if((ps!=null)&&(con!=null))
				{
					ps.close();
					con.close();
				}
			}
			catch(SQLException e) {
			System.out.println("have issue in LoginDAO.insertValueTbl() finally block ::: "+e);
		}
		}
		
		
	}// end insertValueTbl1() method
	
	public void insertValueTbl()
	{
		try
		{
		
		Class.forName(dbDriver);
		con=DriverManager.getConnection(url,usr,pass);
		String sql="insert into logindb.logintbl(user,passwd) value('anirudha','password')";
		ps=con.prepareStatement(sql);
		int i=ps.executeUpdate();
		System.out.println("Table Value Inserted sucessfully..value of i= "+i);
		
		}
		catch(SQLException|ClassNotFoundException e)
		{
			System.out.println("have issue in LoginDAO.insertValueTbl() ::: "+e);
		}
		finally
		{
			try
			{
				if((ps!=null)&&(con!=null))
				{
					ps.close();
					con.close();
				}
			}
			catch(SQLException e) {
			System.out.println("have issue in LoginDAO.insertValueTbl() finally block ::: "+e);
		}
		}
		
		
	}// end insertValueTbl() method
	
	public boolean check(BeanLogin b)
	{
		boolean status=false;
		
		try
		{
			Class.forName(dbDriver);
			con=DriverManager.getConnection(url,usr,pass);
			String sql="select user,passwd from logindb.logintbl where user=? and passwd=?";
			ps=con.prepareStatement(sql);
			ps.setString(1,b.getUser());
			ps.setString(2,b.getPasswd());
			ResultSet rs=ps.executeQuery();
			status=rs.next();
			rs.close();
		}
		catch(SQLException|ClassNotFoundException e)
		{
			System.out.println("have issue in LoginDAO.check(str,str) ::: "+e);
		}
		finally
		{
			try
			{
				if((ps!=null)&&(con!=null))
				{
					ps.close();
					con.close();
				}
			}
			catch(SQLException e) {
			System.out.println("have issue in LoginDAO.check(str,str) finally block ::: "+e);
		}
		}
	return status;	
	}//end check(Str,Str) Method
	
}// end LoginDAO{} class