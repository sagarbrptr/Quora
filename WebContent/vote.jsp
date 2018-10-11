<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	if((request.getSession(false) == null) || (session.getAttribute("username")==null) )
	{
		//out.println("IGI");
		session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		+	"Please <strong>Login</strong> first</div>");
		response.sendRedirect("login.jsp");
		out.println("<a class=\"btn btn-outline-primary text-light\" href='login.jsp' >Login</a>");
		out.println(
	         "<span class='sr-only'>(current)</span>"
	         +"</a>");
		 				
	}
	else if(request.getParameter("type").equals("0"))	//up vote	question
	{
		int que_id=Integer.parseInt(request.getParameter("que_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=question.up_vote(user_id,que_id);
		out.println("Up Voted Inserted Successfully");
	
		if(res==-1)
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Cannot upvote again!</strong></div>");
		else
			session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Successfully</strong> upvoted </div>");

		response.sendRedirect("index.jsp");
	}
	else if(request.getParameter("type").equals("1"))	//down vote	question
	{
		int que_id=Integer.parseInt(request.getParameter("que_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=question.down_vote(user_id,que_id);
		if(res==-1)
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Cannot downvote again!</strong></div>");
		else
			session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Successfully</strong> downvoted </div>");
		response.sendRedirect("index.jsp");
	}
	else if(request.getParameter("type").equals("2"))	//up vote answer
	{
		int ans_id=Integer.parseInt(request.getParameter("ans_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=answer.up_vote(user_id,ans_id);
		if(res==-1)
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Cannot upvote again!</strong></div>");
		else
			session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Successfully</strong> upvoted! </div>");
		response.sendRedirect("display_answer.jsp?que_id="+request.getParameter("que_id"));
	}
	else if(request.getParameter("type").equals("3"))	//down vote	answer
	{
		int ans_id=Integer.parseInt(request.getParameter("ans_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=answer.down_vote(user_id,ans_id);
		if(res==-1)
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Cannot downvote again!</strong></div>");
		else
			session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Successfully</strong> downvoted</div>");
		response.sendRedirect("display_answer.jsp?que_id="+request.getParameter("que_id"));
	}
%>

</body>
</html>