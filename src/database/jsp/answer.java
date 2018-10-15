/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.jsp;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
/**
 *
 * @author sagar
 */
public class answer
{
    
    public String username, answer;
    public int upvote, downvote, ans_id,flag,user_id;
    public String Date;
    
    public void answer()
    {
        username=answer=Date="";
        upvote=downvote=ans_id=flag=user_id=0;
    }
    public static int insert_answer(int user_id,int que_id,String answer)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;
            System.out.println(user_id);
            System.out.println(que_id);
            System.out.println(answer);
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
                System.out.println("ERROR in insert answer!!\n");
                return 0;			// print error
            }
    }
    
    public static ArrayList<answer> display_answer(int que_id)		// que_id == id coming from question
    {    																	  	
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String que_id_string = Integer.toString(que_id);
        
        String search_answer_query = "SELECT * from Answer where que_id='"+ que_id_string + "';";    
        
        ArrayList<answer> result = new ArrayList<answer>();
        System.out.println("IGI\n"+search_answer_query);
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
            	 if(rs.getInt(7) == 0) //if not flagged, then only add in result set
	            {
            		 System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+" " +rs.getInt(3)+ " "+ rs.getString(4)+"  "
	            				 +rs.getInt(5)+" "+rs.getInt(6));                
	                 
	//----------------------------------------------------------------------------------------------------------------------------                 
	                 String get_username = "SELECT username,admin FROM User WHERE user_id = "+"'"+rs.getInt(3)+"';";		
	                 
	                 Statement sub_query = con.createStatement();
	                 ResultSet username_rs =  sub_query.executeQuery(get_username); 
	                 
	                 answer res= new answer();
	                 while(username_rs.next())
	                 {
	                	 if(username_rs.getInt(2)==0)
	                		 res.username = username_rs.getString(1);
	                	 
	                	 else
	                		 res.username = "admin";
	                 }
	//----------------------------------------------------------------------------------------------------------------------------                 
	                 
	                 res.ans_id = rs.getInt(1);
	                 res.answer = rs.getString(4);
	                 res.upvote = rs.getInt(5);
	                 res.downvote = rs.getInt(6);
	                 
	                 DateFormat df = new SimpleDateFormat("dd MMMM yyyy ");
	                   
	                 java.util.Date date;
	                 date = new java.util.Date(rs.getTimestamp(8).getTime());
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
            System.out.println("ERROR in answer display!!!\n");
            //return null;
            return result;
        }
    }
    
    public static int up_vote(int user_id, int ans_id )
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String check = "select up_vote from answer_vote where user_id = '"+user_id+"' and ans_id = '"+ans_id+"';";
                       
        
        String vote_answer = "insert into answer_vote(ans_id,user_id,up_vote) values('"+ans_id+"','"+user_id+"',1) "
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
            	 query.executeUpdate(vote_answer);
             
             con.close();
             
             return 1;
             
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR in adding up_vote to answer!!!\n");
            //return null;
            return 0;
        }
                
    }
    
    public static int down_vote(int user_id, int ans_id )
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String check = "select down_vote from answer_vote where user_id = '"+user_id+"' and ans_id = '"+ans_id+"';";

        
        String vote_answer = "insert into answer_vote(ans_id,user_id,down_vote) values('"+ans_id+"','"+user_id+"',1) "
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
            	 con.close();
            	 System.out.println("Already voted");
            	 return -1; 				//
             }	
             
             else
            	 query.executeUpdate(vote_answer);
             
             con.close();
             return 1;
             
        }
        
        catch(Exception E)
        {
        	System.out.println("ERROR in adding down_vote to answer!!!\n");
            //return null;
            return 0;
        }
                
    }
    public static int get_up_vote(int ans_id)
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select sum(up_vote)  from answer_vote where ans_id='"+ans_id+"';";
        
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
        	System.out.println("ERROR in get up vote!!!\n");
            //return null;
            return 0;
        }
    }
    public static int get_down_vote(int ans_id)
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select sum(down_vote)  from answer_vote where ans_id='"+ans_id+"';";
        
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
        	System.out.println("ERROR in get down vote!!!\n");
            //return null;
            return 0;
        }
    }
    
    public static int modify_answer(int ans_id,String answer)		// user_id == coming from login(session) 
    {																			// question == coming from ask_question page    	
        String url = "jdbc:mysql://localhost:3306/quora";
            String user = "GOD" ;
            String pass = "Test#123" ;                       
            System.out.println(answer);                       
            
        	//java.sql.Time sqlDate = new java.sql.Time(new java.util.Date().getTime());
            
            String sql_insert_query= "UPDATE Answer SET answer = '"+answer+"', ans_date = "+" (select CURRENT_TIMESTAMP) WHERE ans_id = '"+ans_id+"';";                       
                       

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url,user,pass);

                Statement query = con.createStatement();
                query.executeUpdate(sql_insert_query);                
                System.out.println("in mod ans fn");                               
                con.close();                
                return 1;			// print successful
            }

            catch(Exception e)
            {
                System.out.println("ERROR in modify answer!!\n");
                return 0;			// print error
            }
    }
    
    public static int get_todays_answer()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select count(*) from Answer where DATE(ans_date)=DATE(CURRENT_TIMESTAMP) and flag=0;";
        
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
        	System.out.println("ERROR in get todays answer!!!\n");
            //return null;
            return 0;
        }
    }
    
    public static int get_total_answer()
    {
    	String url = "jdbc:mysql://localhost:3306/quora";
        String user = "GOD" ;
        String pass = "Test#123" ;
        
        String get_vote = "select count(*) from Answer where flag=0;";
        
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
        	System.out.println("ERROR in get todays answer!!!\n");
            //return null;
            return 0;
        }
    }
    
}


