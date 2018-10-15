package database.jsp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class question 
{
	public String username, question, Date;
	public int que_id,upvote, downvote,user_id;
	
	public void question()
	{
		username = question = Date=" ";
		upvote = downvote = user_id = 0;
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
                System.out.println("ERROR question.java in insert_question!!!\n");
                return 0;			// print error
            }
    }
    
    public static ArrayList<question> search_question(String keyword)		// keyword == text coming from search bar
    {    																	   // result = array of object(has to be passed to constructor first)	
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String search_questio_query = "SELECT * FROM Question WHERE LOWER(question) LIKE "+"LOWER('%"+keyword+"%');";
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
            	 
            	 if(rs.getInt(6) == 0)		//if not flagged, then only add in result set
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
	                 DateFormat df = new SimpleDateFormat("dd MMMM yyyy ");
	                   
	                 java.util.Date date;
	                 date = new java.util.Date(rs.getTimestamp(7).getTime());
	                 res.Date=df.format(date);
	                 
	                 result.add(res);
	                 
	                 len++;
            	 }
                 
             }

             con.close();             
             return result;				// return  length of array (number of results matched)
        }
        
        catch(Exception e)
        {
            System.out.println("ERROR question.java in search question !!!\n");
            //return null;
            return result;
        }
    }

    public static question display_single_question(int que_id) // when a question is selected from search results 
    {													  // its que_id will be passed here
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String display_question_query = "SELECT que_id,question,user_id,up_vote,down_vote,flag,que_date FROM Question WHERE que_id = "+"'"+que_id+"';";
        
        question result = new question();
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(display_question_query);
             
             while(rs.next())
             {
            	 if(rs.getInt(6) == 0)	//if not flagged, then only add in result set
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
	            	 DateFormat df = new SimpleDateFormat("dd MMMM yyyy ");
	                   
	                 java.util.Date date;
	                 date = new java.util.Date(rs.getTimestamp(7).getTime());
	                 result.Date=df.format(date);
            	 }
             }
             
             con.close();
             return result;
        }
        
        catch(Exception e)
        {
            System.out.println("ERROR question.java  in display of one question!!!\n");
            return result;
            //return 0;
        }
    }
    
    public static int up_vote(int user_id, int que_id )
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String check = "select up_vote from question_vote where user_id = '"+user_id+"' and que_id = '"+que_id+"';";

        
        String vote_question = "insert into question_vote(que_id,user_id,up_vote) values('"+que_id+"','"+user_id+"',1) "
        		+ "on duplicate key update  up_vote = values(up_vote);";
        
        try
        {
        	int no_user=1;
        	int curr_vote = -1;
        	
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet checker =   query.executeQuery(check);
             
             while(checker.next())
             {
            	 no_user = 0;
            	 
            	 curr_vote = checker.getInt(1);
             }
             
             if(curr_vote == 1)
             {
            	 System.out.println("Already voted");
            	 con.close();
            	 return -1; 				//
             }	
             
             else
            	 query.executeUpdate(vote_question);
             con.close();
             return 1;
             
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR question.java  in up vote!!!\n");
            //return null;
            return 0;
        }
                
    }
    
    public static int down_vote(int user_id, int que_id )
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String check = "select down_vote from question_vote where user_id = '"+user_id+"' and que_id = '"+que_id+"';";

        
        String vote_question = "insert into question_vote(que_id,user_id,down_vote) values('"+que_id+"','"+user_id+"',1) "
        		+ "on duplicate key update  down_vote = values(down_vote);";
        
        try
        {
        	int no_user=1;
        	int curr_vote = -1;
        	
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet checker =   query.executeQuery(check);
             
             while(checker.next())
             {
            	 no_user = 0;
            	 
            	 curr_vote = checker.getInt(1);
             }
             
             if(curr_vote == 1)
             {
            	 System.out.println("Already voted");
            	 con.close();
            	 return -1; 				//
             }	
             
             else
            	 query.executeUpdate(vote_question);
             con.close();
             return 1;
             
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR question.java  in down_vote!!!\n");
            //return null;
            return 0;
        }
                
    }
    public static int get_up_vote(int que_id)
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select sum(up_vote)  from question_vote where que_id='"+que_id+"';";
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
        	 ResultSet rs = query.executeQuery(get_vote);
             while(rs.next())
             {
            	 int ret_val=rs.getInt(1);
            	 con.close();
            	 return ret_val;
             }
             con.close(); 
             return 0;
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR question.java  in get_up_vote!!!\n");
            //return null;
            return 0;
        }
    }
    public static int get_down_vote(int que_id)
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select sum(down_vote)  from question_vote where que_id='"+que_id+"';";
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
        	 ResultSet rs = query.executeQuery(get_vote);
             while(rs.next())
             {
            	 int ret_val=rs.getInt(1);
            	 con.close();
            	 return ret_val;
             }
             con.close(); 
             return 0;
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR question.java  in get_down_vote!!!\n");
            //return null;
            return 0;
        }
    }
    
    public static int modify_question(int que_id, String question)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            
            java.sql.Time sqlDate = new java.sql.Time(new java.util.Date().getTime());
            
            String sql_insert_query= "UPDATE Question SET question = '"+question+"', que_date = (select CURRENT_TIMESTAMP)  WHERE que_id = '"+que_id+"';";                       

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
                System.out.println("ERROR question.java in modify_question!!!\n");
                return 0;			// print error
            }
    }
    
    public static int get_todays_question()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select count(*) from Question where DATE(que_date)=DATE(CURRENT_TIMESTAMP) and flag=0;";
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
        	 ResultSet rs = query.executeQuery(get_vote);
             while(rs.next())
             {
            	 int ret_val=rs.getInt(1);
            	 con.close();
            	 return ret_val;
             }
             con.close(); 
             return 0;
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR question.java  in get_todays_question!!!\n");
            //return null;
            return 0;
        }
    }
    public static int get_total_question()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select count(*) from Question where flag=0;";
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
        	 ResultSet rs = query.executeQuery(get_vote);
             while(rs.next())
             {
            	 int ret_val=rs.getInt(1);
            	 con.close();
            	 return ret_val;
             }
             con.close(); 
             return 0;
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR question.java  in get_total_question!!!\n");
            //return null;
            return 0;
        }
    }
    
}


