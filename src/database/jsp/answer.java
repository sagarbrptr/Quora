/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.jsp;
import java.sql.*;
import java.util.*;

/**
 *
 * @author sagar
 */
public class answer {
    
    public String username, answer;
    public int upvote, downvote, ans_id;
    
    public void answer()
    {
        username=answer="";
        upvote=downvote=ans_id=0;
    }
    public static int insert_answer(int user_id,int que_id,String answer)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            
            String user_id_string = Integer.toString(user_id);
            String que_id_string = Integer.toString(que_id);
            
            String sql_insert_query= "INSERT INTO Answer (que_id,user_id,answer) VALUES("+ "'"+ que_id_string+"'," +"'"+ user_id_string+"'," + "'"+ answer +"');";                       

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
    
    public static ArrayList<answer> display_answer(int que_id)		// que_id == id coming from question
    {    																	   // result = array of object(has to be passed to constructor first)	
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String que_id_string = Integer.toString(que_id);
        
        String search_answer_query = "SELECT * from Answer where que_id='"+ que_id_string + "';";        
        ArrayList<answer> result = new ArrayList<answer>();
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(search_answer_query);   
             
             //System.out.println("IGI\n");
             
             int len=0;
             while(rs.next())
             {
                 System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+" " +rs.getInt(3)+ " "+ rs.getString(4)+"  "
                 +rs.getInt(5)+" "+rs.getInt(6));                
                 
//----------------------------------------------------------------------------------------------------------------------------                 
                 String get_username = "SELECT username FROM User WHERE user_id = "+"'"+rs.getInt(2)+"';";		// obtain username from user_id
                 
                 Statement sub_query = con.createStatement();
                 ResultSet username_rs =  sub_query.executeQuery(get_username); 
                 
                 answer res= new answer();
                 while(username_rs.next())
                	 res.username = username_rs.getString(1);
//----------------------------------------------------------------------------------------------------------------------------                 
                 
                 res.ans_id = rs.getInt(1);
                 res.answer = rs.getString(4);
                 res.upvote = rs.getInt(5);
                 res.downvote = rs.getInt(6);
                 
                 result.add(res);
                 
                 len++;
                 
             }

             con.close();             
             return result;				// return  length of array (number of results matched)
        }
        
        catch(Exception e)
        {
            System.out.println("ERROR!!!\n");
            //return null;
            return result;
        }
    }
}