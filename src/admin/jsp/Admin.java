package admin.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Admin 
{
	public static int flag_question(int que_id)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            
            String que_id_string = Integer.toString(que_id);
            
            String sql_insert_query= "UPDATE Question SET flag=1 where que_id='"+que_id_string+"';";                      

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,user,pass);

                Statement query = con.createStatement();
                query.executeUpdate(sql_insert_query);                
                                               
                con.close();                
                return 1;			// print successful
            }

            catch(Exception e)
            {
                System.out.println("ERROR!!!\n");
                return 0;			// print error
            }
    }
	public static int flag_answer(int ans_id)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            
            String ans_id_string = Integer.toString(ans_id);
            
            String sql_insert_query= "UPDATE Answer SET flag=1 where ans_id='"+ans_id_string+"';";                      

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,user,pass);

                Statement query = con.createStatement();
                query.executeUpdate(sql_insert_query);                
                                               
                con.close();                
                return 1;			// print successful
            }

            catch(Exception e)
            {
                System.out.println("ERROR!!!\n");
                return 0;			// print error
            }
    }
	public static int flag_user(int user_id)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            
            String user_id_string = Integer.toString(user_id);
            
            String sql_insert_query= "UPDATE User SET flag=1 where user_id='"+user_id_string+"';";                      

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,user,pass);

                Statement query = con.createStatement();
                query.executeUpdate(sql_insert_query);                
                                               
                con.close();                
                return 1;			// print successful
            }

            catch(Exception e)
            {
                System.out.println("ERROR!!!\n");
                return 0;			// print error
            }
    }
}
