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
	else if(request.getParameter("type").equals("0"))	//modify question
	{
		int done_editing = question.modify_question(Integer.parseInt(request.getParameter("que_id")),request.getParameter("question"));
		
		if(done_editing == 1)
		{
			out.println("Question Edited Successfully");
			session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Successfully</strong> edited Question </div>");
			
		}
		
		if(done_editing == 0)
		{
			out.println("Error occured during question edition");
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"Question editing <strong>failed.</strong> Please try again after some time.</div>");
		}  
		
		if(session.getAttribute("admin")!=null)
			response.sendRedirect("admin_dashboard.jsp");
		else
			response.sendRedirect("index.jsp");
								
	}
	else if(request.getParameter("type").equals("1"))	//modify answer
	{
		 String que_id = (String) session.getAttribute("que_id_integer");
		 System.out.println("in modify.jsp answer"+que_id);
         int que_id_int = Integer.parseInt(que_id);	
		int done_editing = answer.modify_answer(Integer.parseInt(request.getParameter("ans_id")),request.getParameter("answer"));
		System.out.println("ret val in MODIFY.JSP "+done_editing);
		if(done_editing == 1)
		{
			System.out.println("Answer edited Successfully");
			session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Successfully</strong> edited Answer </div>");
		}
		
		else if(done_editing == 0)
		{
			out.println("Error occured during answer editing");
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"Answer editing <strong>failed.</strong> Please try again after some time.</div>");
		}  
		//session.removeAttribute("que_id_integer");
		if(session.getAttribute("admin")!=null)
			response.sendRedirect("admin_display_answer.jsp?que_id="+que_id);
		else
			response.sendRedirect("display_answer.jsp?que_id="+que_id);
	}
	
%>

</body>
</html>