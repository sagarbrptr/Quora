package com.login;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet 
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		LoginDao dao=new LoginDao();
		if(dao.check(uname, pass))
		{
			HttpSession session=request.getSession();
			
			session.setAttribute("username",uname);
			session.setAttribute("session_check",1);
			int user_id=dao.getuser_id(uname, pass);
			session.setAttribute("user_id",user_id);
			if(dao.isadmin(uname, pass))
			{
				session.setAttribute("admin", 1);
				System.out.println("in admin check done");
				session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+	"<strong>Login successful</strong></div>");
				response.sendRedirect("admin_dashboard.jsp");
				return;
			}
			else
			{
				session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+	"<strong>Login successful</strong></div>");
				response.sendRedirect("index.jsp");
				return;
			}
		}
		else
		{
			HttpSession session=request.getSession();
			session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
					+	"<strong>Incorrect Username or Password </strong></div>");
			response.sendRedirect("login.jsp");
		}
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("session_check");
		session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
				+	"<strong>Logged out</strong></div>");
		response.sendRedirect("index.jsp");
	}
	
}