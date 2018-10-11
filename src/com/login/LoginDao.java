package com.login;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class LoginDao {
	String sql="select * from User where username=? and password=?";
	String url = "jdbc:mysql://localhost:3306/quora";
    String user = "GOD" ;
    String password = "Test#123" ;
    public boolean check(String uname, String pass)
    {
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
            	return true;
            	
            }
           
        }

        catch(Exception e)
        {
          e.printStackTrace();
        }
    	
    	return false;
    }
    public int getuser_id(String uname, String pass)
    {
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
            	return rs.getInt(1);
            	
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
            		return false;
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