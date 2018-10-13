package admin.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import database.jsp.answer;
import database.jsp.question;
import database.jsp.question_answer;

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
	
	public static ArrayList<question_answer> flagged_question_answer(String keyword)
    {
        String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        question_answer ob=new question_answer();
        ob.callProcedure();		// Procedure Calling is done here
        String search_questio_query = "SELECT * FROM Question WHERE LOWER(question) LIKE "+"LOWER('%"+keyword+"%') order by up_vote desc,down_vote asc;";
        ArrayList<question_answer> result = new ArrayList<question_answer>();
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	 Statement query = con.createStatement();
             ResultSet rs = query.executeQuery(search_questio_query);   
             
             //System.out.println("IGI\n");
             
             int len=0;
             while(rs.next() )			
             {
            	 
            	 if(rs.getInt(6)==1)
            	 {
            	 
	                 System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "
	                 +rs.getString(3)+" "+rs.getInt(4));                
	                 
	//----------------------------------------------------------------------------------------------------------------------------                 
	                 String get_username = "SELECT username FROM User WHERE user_id = "+"'"+rs.getInt(2)+"';";		// obtain username from user_id
	                 
	                 Statement sub_query = con.createStatement();
	                 ResultSet username_rs =  sub_query.executeQuery(get_username); 
	                 
	                 question q= new question();
	                 while(username_rs.next())
	                	 q.username = username_rs.getString(1);
	//----------------------------------------------------------------------------------------------------------------------------                 
	                 
	                 q.que_id = rs.getInt(1);
	                 q.question = rs.getString(3);
	                 q.upvote = rs.getInt(4);
	                 q.downvote = rs.getInt(5);
	                 
	                //Add one answer to be displayed
	                
	                answer a=new answer();
	                
	                //get username of answer
	                 
	
	                //get one ans from que_id by upvote desc,downvote asc
	                
	                String que_id_string = Integer.toString(rs.getInt(1));
	                
	                String get_ans_query = "SELECT * FROM Answer where que_id = '"+
	                        que_id_string + "' order by up_vote desc, down_vote asc;";
	                
	                sub_query = con.createStatement();
	                
	                ResultSet get_one_ans =  sub_query.executeQuery(get_ans_query);
	                
	                //int got_answer=0;
	                 
	                while(get_one_ans.next())		
	                {	                		                
	                	
	                	if(get_one_ans.getInt(7)==1)
	                	{
	                		
	                		int count=1;
		                    a.answer = get_one_ans.getString(4);
		                    a.ans_id = get_one_ans.getInt(1);
		                    a.upvote = get_one_ans.getInt(5);
		                    a.downvote = get_one_ans.getInt(6);
		                    
		                    String get_username_ans = "SELECT username,admin FROM User WHERE user_id = "+"'"+ get_one_ans.getInt(3)+"';";		// obtain username from user_id_ans
		                 
		                    Statement sub_sub_query = con.createStatement();
		                    ResultSet username_ans =  sub_sub_query.executeQuery(get_username_ans); 
		
		                    while(username_ans.next())
		                    {
		                   	 if(username_ans.getInt(2) == 0)
		                   		 a.username = username_ans.getString(1);
		                   	 
		                   	 else
		                   		 a.username = "admin";
		                    }
		                    
		                    System.out.println(count);
		                    count++;
	                	}
	                }
	                
	                 len++;
	                 // Question_Answer Wrapped object
	                
	                question_answer obj= new question_answer();
	                
	                obj.que = q;
	                obj.ans = a;
	                
	                result.add(obj);
            	 }
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
