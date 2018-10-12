package com.login;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LoginDao {
	
    public boolean check(String uname, String pass)
    {
    	String sql="select * from User where username=? and password=?";
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String password = "Test#123" ;
    	try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(url,user,password);

            PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
            st.setString(1, uname);
            st.setString(2, pass);                      
           
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
            	if(rs.getInt(5) == 0)
            	{
	            	con.close();
	            	return true;
            	}
            	
            	else
            	{
            		con.close();
            		return false;
            	}
            	
            }
            con.close();
            return false;
        }

        catch(Exception e)
        {
          e.printStackTrace();
        }
    	
    	return false;
    }
    public int getuser_id(String uname, String pass)
    {
    	String sql="select * from User where username=? and password=?";
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String password = "Test#123" ;
    	try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(url,user,password);

            PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
            st.setString(1, uname);
            st.setString(2, pass);                      
            
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
            	int ret_val=rs.getInt(1);
            	System.out.println("in getuser_id before conclose "+rs.getInt(1));
            	con.close();
            	
            	return ret_val;
            	
            }
           
        }

        catch(Exception e)
        {
          e.printStackTrace();
        }
    	
    	return 0;
    }
    public boolean isadmin(String uname, String pass)
    {
    	String sql="select * from User where username=? and password=?";
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String password = "Test#123" ;
    	try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection(url,user,password);

            PreparedStatement st=(PreparedStatement) con.prepareStatement(sql);
            st.setString(1, uname);
            st.setString(2, pass);                      
           
            ResultSet rs = st.executeQuery();
            if(rs.next())
            {
            	if(rs.getInt(4)==0)
            	{
            		con.close();
            		return false;
            	}
            	con.close();
            	return true;
            	
            }
           
        }

        catch(Exception e)
        {
          e.printStackTrace();
        }
    	
    	return false;
    }
}