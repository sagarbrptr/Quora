<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <% int done = answer.insert_answer(1,1,"answer to 1 how are you pict link 2?");
			if(done == 1)			
				out.println("Answer inserted successfully");			
			
			else
				out.println("Error while answer insertion");
            ArrayList<answer> a= answer.display_answer(1);
			
			if(a.size() == 0)
			{
				out.println("No match for given keyword");
			}
			
			else
			{
				for(int i=0;i<a.size();i++)
				{
                                        out.println(a.get(i).username + " " + a.get(i).answer + " " + a.get(i).upvote + " " + a.get(i).downvote + "<br>");
					
				}
			}
            
         %>
         
        <%/*int done = question.insert_question(1,"how are you pict?");
			if(done == 1)			
				out.println("Question inserted successfully");			
			
			else
				out.println("Error while question insertion");*/
			
			
				
			ArrayList<question> q= question.search_question("");
			
			if(q.size() == 0)
			{
				out.println("No match for given keyword");
			}
			
			else
			{
				for(int i=0;i<q.size();i++)
				{
                                        out.println(q.get(i).username + " " + q.get(i).question + " " + q.get(i).upvote + " " + q.get(i).downvote + "<br>");
					
				}
			}
				
		%>


        
    </body>
</html>