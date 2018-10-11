package com.login;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;


@WebServlet("/Registration")
public class Registration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public Registration()
    {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
		   System.out.println("IGI");

			
			String name=request.getParameter("uname");
			String password=request.getParameter("password");
			String confirm_pass=request.getParameter("confirm_pass");
											
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quora","GOD","Test#123");
			
			String username_checker = "select username from User where username= '"+name+"';";
			Statement query = (Statement)conn.createStatement();
			HttpSession session=request.getSession();
			ResultSet rs = query.executeQuery(username_checker);
			
			while(rs.next())		// alert 
			{
				session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+	"<strong>Username</strong> already used!!! </div>");
				System.out.println("username already used !!!!");
				conn.close();
				response.sendRedirect("register.jsp");
				return;
			}
				
			
			String sql="insert into User(username,password) values(?,?)";
			PreparedStatement ps= (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.executeUpdate();
			PrintWriter out=response.getWriter();
			System.out.println("Registerd Successfully !!!!");
			
			//response.sendRedirect("index.jsp");
			LoginDao dao=new LoginDao();
			if(dao.check(name, password))
			{
				//HttpSession session=request.getSession();
				
				session.setAttribute("username",name);
				session.setAttribute("session_check",1);
				int user_id=dao.getuser_id(name, password);
				session.setAttribute("user_id",user_id);
				if(dao.isadmin(name, password))
				{
					session.setAttribute("admin", 1);
					System.out.println("in admin check done");
					response.sendRedirect("admin_dashboard.jsp");
				}
				else
				{
					session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
							+	"<strong>Registration successful</strong></div>");
					response.sendRedirect("index.jsp");
				}
			}
			else
			{
				response.sendRedirect("login.jsp");
			}
		
						
			
		}
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
					
		}
		catch (SQLException e) 
		{ 
			// TODO Auto-generated catch block
			e.printStackTrace();
						
		}
	}

}