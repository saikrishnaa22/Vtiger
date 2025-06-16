package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection con;
	
	public Connection getdatabaseconnection(String url, String username, String password ) throws SQLException
	{
		
	Driver dobj = new  Driver();
	DriverManager.registerDriver(dobj);
	con=DriverManager.getConnection(url,username,password);
	return con;
	
	}
	
	public ResultSet executeSelectQuery(String query , int index) throws SQLException
	{
		
		Statement stmt= con.createStatement();
		ResultSet result= stmt.executeQuery(query);
		
		return result;
		
		
	}
	
	public int executeUpdateQuery(String updatequery) throws SQLException
	{
		Statement stmt= con.createStatement();	
		int res = stmt.executeUpdate(updatequery);
		return res;
		
	}
	
	public void closeDataBaseconnection() throws SQLException
	{
		con.close();	
	}
	
	
	public Connection getdatabaseconnection() throws SQLException
	{
		
	Driver dobj = new  Driver();
	DriverManager.registerDriver(dobj);
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advproject", "root", "sonu");
	return con;
	
	}
	
	
	
	

}
