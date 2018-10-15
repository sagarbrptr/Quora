/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.jsp;
import java.sql.*;
import java.util.*;
import java.sql.CallableStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sagar
 */
public class question_answer {
    public question que;
    public answer ans;
    
    public void question_answer()
    {
        que=new question();
        ans=new answer();
    }
    
    public void callProcedure()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
	    	String callp="{ CALL UPDATE_QUESTION_UP_VOTE() }";
	    	CallableStatement cst= con.prepareCall(callp);
	    	cst.executeQuery();
	    
	    	
	    	
	    	callp="{ CALL UPDATE_QUESTION_DOWN_VOTE() }";
	    	cst= con.prepareCall(callp);
	    	cst.executeQuery();
	    	
	    	
	    	
	    	callp="{ CALL UPDATE_ANSWER_UP_VOTE() }";
	    	cst= con.prepareCall(callp);
	    	cst.executeQuery();
	    	
	    	
	    	
	    	callp="{ CALL UPDATE_ANSWER_DOWN_VOTE() }";
	    	cst= con.prepareCall(callp);
	    	cst.executeQuery();
	    	System.out.println("Done Calling procedure!!!\n");
                         
        
       }
       
       catch(Exception e)
       {
           System.out.println("ERROR in call procedure!!!\n");

       }
	    	
    }
   /* public static ArrayList<question_answer> trending()
    {
        String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        
        
        String home_query = "select Question.que_id, Question.question, Question.user_id, Question.up_vote, Question.down_vote"
                + ", Answer.ans_id, Answer.answer, Answer.user_id, Answer.up_vote, Answer.down_vote "
                + "from Question,Answer where Question.que_id=Answer.que_id order by Question.up_vote desc,Question.down_vote asc,Answer.up_vote desc,Answer.down_vote asc ;";
        
        ArrayList<question_answer> result = new ArrayList<question_answer>();
        
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	Connection con = DriverManager.getConnection(url,user,pass);
        	
        	Statement query = con.createStatement();
                ResultSet rs = query.executeQuery(home_query);   
             
             //System.out.println("IGI\n");
             
             int len=0;
             while(rs.next())
             {
                 answer a= new answer();
                 question q = new question();
                 
                 q.que_id = rs.getInt(1);
                 a.ans_id = rs.getInt(6);
                 
                // System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+" " +rs.getInt(3)+ " "+ rs.getString(4)+"  "
                 //+rs.getInt(5)+" "+rs.getInt(6));                
                 
//----------------------------------------------------------------------------------------------------------------------------                 
                 //get username of question
                 String get_username_que = "SELECT username FROM User WHERE user_id = "+"'"+rs.getInt(3)+"';";		// obtain username from user_id
                 
             
                 Statement sub_query = con.createStatement();
                 ResultSet username_que =  sub_query.executeQuery(get_username_que); 
                 
                 
                 while(username_que.next())
                     q.username = username_que.getString(1);
                
                 //get username of answer
                 String get_username_ans = "SELECT username,admin FROM User WHERE user_id = "+"'"+rs.getInt(8)+"';";		// obtain username from user_id_ans
                 
                 sub_query = con.createStatement();
                 ResultSet username_ans =  sub_query.executeQuery(get_username_ans); 
                 
                 while(username_ans.next())
                 {
                	 if(username_ans.getInt(2) == 0)
                		 a.username = username_ans.getString(1);
                	 
                	 else
                		 a.username = "admin";
                 }

//----------------------------------------------------------------------------------------------------------------------------                 
                 
                //Add values to question object
                
                q.question = rs.getString(2);
                q.upvote = rs.getInt(4);
                q.downvote = rs.getInt(5);
                
                //Add values to ans object
                 
                a.answer = rs.getString(7);
                a.upvote = rs.getInt(9);
                a.downvote = rs.getInt(10);
                 
                // Question_Answer Wrapped object
                
                question_answer obj= new question_answer();
                
                obj.que = q;
                obj.ans = a;
                
                result.add(obj);
                
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
        
    }*/
    
    public static ArrayList<question_answer> search_question_answer(String keyword)
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
            	 
            	 if(rs.getInt(6)==0)
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
	                 
	                 q.user_id = rs.getInt(2);
	                 q.que_id = rs.getInt(1);
	                 q.question = rs.getString(3);
	                 q.upvote = rs.getInt(4);
	                 q.downvote = rs.getInt(5);
	                 
	                 DateFormat df = new SimpleDateFormat("dd MMMM yyyy ");
	                   
	                 java.util.Date date;
	                 date = new java.util.Date(rs.getTimestamp(7).getTime());
	                 q.Date=df.format(date);
	                 
	                 //q.Date = rs.getString(7);
	                 
	                //Add one answer to be displayed
	                
	                answer a=new answer();
	                
	                //get username of answer
	                 
	
	                //get one ans from que_id by upvote desc,downvote asc
	                
	                String que_id_string = Integer.toString(rs.getInt(1));
	                
	                String get_ans_query = "SELECT * FROM Answer where que_id = '"+
	                        que_id_string + "' order by up_vote desc, down_vote asc;";
	                
	                sub_query = con.createStatement();
	                
	                ResultSet get_one_ans =  sub_query.executeQuery(get_ans_query);
	                
	                int got_answer=0;
	                 
	                while(get_one_ans.next() && got_answer==0)		
	                {	                		                
	                	
	                	if(get_one_ans.getInt(7)==0)
	                	{
	                		got_answer = 1;
	                		
	                		int count=1;
		                    a.answer = get_one_ans.getString(4);
		                    a.ans_id = get_one_ans.getInt(1);
		                    a.upvote = get_one_ans.getInt(5);
		                    a.downvote = get_one_ans.getInt(6);
		                    a.user_id = get_one_ans.getInt(2);
		                    //System.out.println(get_one_ans.getTimestamp(8));
		                    //a.Date = get_one_ans.getString(8);
		                    
		                    
		                    
		                    
		                    
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
            System.out.println("ERROR in search_question_answer \"\"!!!\n");
            //return null;
            return result;
        }
    }
    
    public static ArrayList<question_answer> search_question_answer_date(String keyword)
    {
        String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        question_answer ob=new question_answer();
        ob.callProcedure();		// Procedure Calling is done here
        String search_questio_query = "SELECT * FROM Question WHERE LOWER(question) LIKE "+"LOWER('%"+keyword+"%') order by que_date desc,up_vote desc, down_vote asc;";
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
            	 
            	 if(rs.getInt(6)==0)
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
	                 
	                 q.user_id = rs.getInt(2);
	                 q.que_id = rs.getInt(1);
	                 q.question = rs.getString(3);
	                 q.upvote = rs.getInt(4);
	                 q.downvote = rs.getInt(5);
	                 
	                 DateFormat df = new SimpleDateFormat("dd MMMM yyyy ");
	                   
	                 java.util.Date date;
	                 date = new java.util.Date(rs.getTimestamp(7).getTime());
	                 q.Date=df.format(date);
	                 
	                 //q.Date = rs.getString(7);
	                 
	                //Add one answer to be displayed
	                
	                answer a=new answer();
	                
	                //get username of answer
	                 
	
	                //get one ans from que_id by upvote desc,downvote asc
	                
	                String que_id_string = Integer.toString(rs.getInt(1));
	                
	                String get_ans_query = "SELECT * FROM Answer where que_id = '"+
	                        que_id_string + "' order by ans_date desc,up_vote desc, down_vote asc;";
	                
	                sub_query = con.createStatement();
	                
	                ResultSet get_one_ans =  sub_query.executeQuery(get_ans_query);
	                
	                int got_answer=0;
	                 
	                while(get_one_ans.next() && got_answer==0)		
	                {	                		                
	                	
	                	if(get_one_ans.getInt(7)==0)
	                	{
	                		got_answer = 1;
	                		
	                		int count=1;
		                    a.answer = get_one_ans.getString(4);
		                    a.ans_id = get_one_ans.getInt(1);
		                    a.upvote = get_one_ans.getInt(5);
		                    a.downvote = get_one_ans.getInt(6);
		                    a.user_id = get_one_ans.getInt(2);
		                    //System.out.println(get_one_ans.getTimestamp(8));
		                    //a.Date = get_one_ans.getString(8);
		                    
		                    
		                    
		                    
		                    
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
            System.out.println("ERROR in search_question_answer_date \"\"!!!\n");
            //return null;
            return result;
        }
    }
}