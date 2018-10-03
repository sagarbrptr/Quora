package database.jsp;
import java.sql.*;
import java.util.*;


public class question 
{
	public String username, question;
	public int que_id,upvote, downvote;
	
	public void question()
	{
		username = question = " ";
		upvote = downvote = 0;
	}
	
    public static int insert_question(int user_id, String question)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            
            String user_id_string = Integer.toString(user_id);
            
            String sql_insert_query= "INSERT INTO Question (user_id,question) VALUES("+ "'"+ user_id_string+"'," + "'"+ question +"');";                       

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
    
    public static ArrayList<question> search_question(String keyword)		// keyword == text coming from search bar
    {    																	   // result = array of object(has to be passed to constructor first)	
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String search_questio_query = "SELECT * FROM Question WHERE question LIKE "+"'%"+keyword+"%';";
        ArrayList<question> result = new ArrayList<question>();
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(search_questio_query);   
             
             //System.out.println("IGI\n");
             
             int len=0;
             while(rs.next())
             {
                 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "
                 +rs.getString(3)+" "+rs.getInt(4));                
                 
//----------------------------------------------------------------------------------------------------------------------------                 
                 String get_username = "SELECT username FROM User WHERE user_id = "+"'"+rs.getInt(2)+"';";		// obtain username from user_id
                 
                 Statement sub_query = con.createStatement();
                 ResultSet username_rs =  sub_query.executeQuery(get_username); 
                 
                 question res= new question();
                 while(username_rs.next())
                	 res.username = username_rs.getString(1);
//----------------------------------------------------------------------------------------------------------------------------                 
                 
                 res.que_id = rs.getInt(1);
                 res.question = rs.getString(3);
                 res.upvote = rs.getInt(4);
                 res.downvote = rs.getInt(5);
                 
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

    public static question display_single_question(int que_id) // when a question is selected from search results 
    {													  // its que_id will be passed here
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String display_question_query = "SELECT que_id,question,user_id,up_vote,down_vote FROM Question WHERE que_id = "+"'"+que_id+"';";
        
        question result = new question();
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(display_question_query);
             
             while(rs.next())
             {
            	 
//----------------------------------------------------------------------------------------------------------------------------
            	 String get_username = "SELECT username FROM User WHERE user_id = "+"'"+rs.getInt(3)+"';";	//obtain username from user_id
                 
                 Statement sub_query = con.createStatement();
                 ResultSet username_rs =  sub_query.executeQuery(get_username); 
                 
                 while(username_rs.next())
                	 result.username = username_rs.getString(1);
//---------------------------------------------------------------------------------------------------------------------------- 
            	 
                 result.que_id = rs.getInt(1);
            	 result.question = rs.getString(2);            	 
            	 result.upvote = rs.getInt(4);
            	 result.downvote = rs.getInt(5);
             }
             
             con.close();
             return result;
        }
        
        catch(Exception e)
        {
            System.out.println("ERROR!!!\n");
            return result;
            //return 0;
        }
    }
    
}



