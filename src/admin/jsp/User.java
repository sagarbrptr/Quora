package admin.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class User {
	public String username,type;
    public int user_id;
    
    public void User()
    {
        username=type="";
        user_id=0;
    }
    
    public static ArrayList<User> get_all_users()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        
        String get_user_query = "SELECT * from User ;";      
        ArrayList<User> result = new ArrayList<User>();
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(get_user_query);   
             
             //System.out.println("IGI\n");
             
             int len=0;
             while(rs.next())
             {
            	 
            	 if(rs.getInt(5) == 0) //if not flagged, then only add in result set
	            {
            		 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" " );   
	                 
	
	                 User res= new User();
	                
	                 
	                 res.user_id = rs.getInt(1);
	                 res.username = rs.getString(2);
	                 if(rs.getInt(4)==1)
	                	 res.type = "ADMIN";
	                 else
	                	 res.type = "USER";
	                 
	                 result.add(res);
	                 
	                 len++;
            	 }
                 
             }

             con.close();             
             return result;				// return  length of array (number of results matched)
        }
        
        catch(Exception e)
        {
            System.out.println("ERROR in getting Users!!!\n");
            //return null;
            return result;
        }
    }
    
    public static ArrayList<User> get_all_flagged_users()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        
        String get_user_query = "SELECT * from User ;";      
        ArrayList<User> result = new ArrayList<User>();
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(get_user_query);   
             
             //System.out.println("IGI\n");
             
             int len=0;
             while(rs.next())
             {
            	 
            	 if(rs.getInt(5) == 1) //if flagged, then only add in result set
	            {
            		 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" " );   
	                 
	
	                 User res= new User();
	                
	                 
	                 res.user_id = rs.getInt(1);
	                 res.username = rs.getString(2);
	                 if(rs.getInt(4)==1)
	                	 res.type = "ADMIN";
	                 else
	                	 res.type = "USER";
	                 
	                 result.add(res);
	                 
	                 len++;
            	 }
                 
             }

             con.close();             
             return result;				// return  length of array (number of results matched)
        }
        
        catch(Exception e)
        {
            System.out.println("ERROR in getting Users!!!\n");
            //return null;
            return result;
        }
    }
    
}
