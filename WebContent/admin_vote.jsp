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
	if(request.getParameter("type").equals("0"))	//up vote	question
	{
		int que_id=Integer.parseInt(request.getParameter("que_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=question.up_vote(user_id,que_id);
		response.sendRedirect("admin_dashboard.jsp");
	}
	if(request.getParameter("type").equals("1"))	//down vote	question
	{
		int que_id=Integer.parseInt(request.getParameter("que_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=question.down_vote(user_id,que_id);
		response.sendRedirect("admin_dashboard.jsp");
	}
	if(request.getParameter("type").equals("2"))	//up vote answer
	{
		int ans_id=Integer.parseInt(request.getParameter("ans_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=answer.up_vote(user_id,ans_id);
		response.sendRedirect("admin_display_answer.jsp?que_id="+request.getParameter("que_id"));
	}
	if(request.getParameter("type").equals("3"))	//down vote	answer
	{
		int ans_id=Integer.parseInt(request.getParameter("ans_id"));
		int user_id=(int)session.getAttribute("user_id");
		int res=answer.down_vote(user_id,ans_id);
		response.sendRedirect("admin_display_answer.jsp?que_id="+request.getParameter("que_id"));
	}
%>

</body>
</html>