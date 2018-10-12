<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="admin.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	if(request.getParameter("type").equals("0"))	//flag que
	{
		int que_id=Integer.parseInt(request.getParameter("que_id"));
		
		int res=Admin.flag_question(que_id);
		out.println("Flagged Question Successfully");
		session.setAttribute("message","<div class=\"alert alert-success alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		+	"<strong>Successfully</strong> deleted the question </div>");
		response.sendRedirect("admin_dashboard.jsp");
	}
	if(request.getParameter("type").equals("1"))	//flag ans
	{
		int ans_id=Integer.parseInt(request.getParameter("ans_id"));
		
		int res=Admin.flag_answer(ans_id);
		out.println("Flagged Answer Successfully");
		session.setAttribute("message","<div class=\"alert alert-success alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		+	"<strong>Successfully</strong> deleted the answer </div>");
		response.sendRedirect("admin_display_answer.jsp?que_id="+request.getParameter("que_id"));
	}
	if(request.getParameter("type").equals("2"))	//flag user
	{
		int user_id=Integer.parseInt(request.getParameter("user_id"));
		
		int res=Admin.flag_user(user_id);
		out.println("Flagged User Successfully");
		session.setAttribute("message", "<div class=\"alert alert-success alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
				+	"<strong>Successfully</strong> deleted the user </div>");
		response.sendRedirect("admin_user_display.jsp");
	}
	
%>

</body>
</html>