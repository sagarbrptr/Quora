<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="admin.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin - Dashboard</title>

    <!-- Bootstrap core CSS-->
    <link href="admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Page level plugin CSS-->
    <link href="admin/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="admin/css/sb-admin.css" rel="stylesheet">

  </head>

  <body id="page-top">
  <%
  if((request.getSession(false) == null) || (session.getAttribute("username")==null) )
	{
		//out.println("IGI");
		response.sendRedirect("login.jsp");
			 				
	}

	else if(session.getAttribute("admin")==null)
	{
		response.sendRedirect("index.jsp");
	}
  %>
	
    <nav class="navbar navbar-expand navbar-dark bg-dark static-top">

      <a class="navbar-brand mr-1" href="admin_dashboard.jsp">PICT QUORA ADMIN</a>

      <%
      /*<button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
      </button>*/
      %>

      <!-- Navbar Search -->
      <form class="d-none d-md-inline-block form-inline mr-auto ml-0 ml-md-5 my-2 my-md-0" action="admin_search_question.jsp" method="post">
        <div class="input-group">
         

              <input type="text" class="form-control" name="question_to_be_searched"
              placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
	            <button type="submit" class="btn btn-primary" >
	              <i class="fas fa-search"></i>
	            </button>
	          </div>

            
          
        </div>
      </form>
	
		<% out.println("<form method='get' action='Logout'>"
			 				    +
			 				    "<button class=\"btn btn-outline-primary text-light\" type='submit' value='Logout' >Logout"	+"</button>"
			 				  
			 				 +"</form>");%>
      <%/*<!-- Navbar -->
      <ul class="navbar-nav ml-auto ml-md-0">
        <li> <a class='nav-link' href='Logout'>Logout
				                <span class='sr-only'>(current)</span>
				                </a> </li>
      </ul>*/%>

    </nav>
	
    <div id="wrapper">

      <!-- Sidebar -->
      <ul class="sidebar navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="admin_dashboard.jsp">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
          </a>
        </li>
        
        <li class="nav-item">
          <a class="nav-link" href="index.jsp">
            <i class="fas fa-home"></i>
            <span>Homepage</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="admin_user_display.jsp">
            <i class="fa fa-user"></i>
            <span>Users</span></a>
        </li>
          <li class="nav-item">
          <a class="nav-link" href="admin_deleted_user_display.jsp">
            <i class="fas fa-user-alt-slash"></i>
            <span>Deleted Users</span></a>
        </li>
        
         <li class="nav-item active">
          <a class="nav-link" href="admin_deleted_question_display.jsp">
            <i class="fas fa-exclamation-circle"></i>
            <span>Deleted Question</span></a>
        </li>
        
      </ul>

      <div id="content-wrapper">

        <div class="container-fluid">
		 <!-- Icon Cards-->
          <div class="container">

      

        <!-- Blog Entries Column -->
        <div class="col-md-10">
			<%
		
			if(session.getAttribute("message")!=null)
			{
				
    			out.println(session.getAttribute("message"));
    			session.removeAttribute("message");
    		}
    	%>
          <h1 class="my-4">DELETED QUESTIONS

          </h1>
           
          <% ArrayList<question_answer> result = Admin.flagged_question_answer("");
          
                if(result.size() == 0)
                {
                	out.println("No match for given keyword");
		}	
		else
		{
			for(int i=0;i<result.size();i++)
			{
                            //<!-- Blog Post -->
                            out.println(
                                    "<div class='card mb-4'>"+
                                        "<div class='card-body'>"+
                                         "<h2 class='card-title'>");
                                        
                            out.println(result.get(i).que.question + " " + "</h2>");
                            
                            
                            out.println("<p class='card-text'>");
                            /*if(result.get(i).ans.answer==null)
                            	out.println("No answer for the question.");
                           	else                
                           		out.println(result.get(i).ans.answer);*/
                            out.println(" " + "</p>");
                                 
                        
                            
                            /*out.println("<a href='" + "admin_display_answer.jsp?que_id="+
                                            result.get(i).que.que_id +"' class='btn btn-primary'>"
                                            +"Read More &rarr;</a>"); */
                             out.println  ("</div>"+
                                        "<div class='card-footer text-muted'>"
                                           + "Posted by "
                                           + "<b>"
                                           +    result.get(i).que.username
                                           +   "</b><br>"
                                        		   
                                          	
                                           +"</div>"
                                    +"</div>");

                        }
		}           %>
          

        <!-- Pagination -->
          <ul class="pagination justify-content-center mb-4">
            <li class="page-item">
              <br>
            </li>
            <li class="page-item disabled">
              <br>
            </li>
          </ul>
          
        </div>
         
		</div> <!-- row -->
        </div>
        <!-- /.container-fluid -->

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright © PICT Quora 2018</span>
            </div>
          </div>
        </footer>

      </div>
      <!-- /.content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="login.html">Logout</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="admin/vendor/jquery/jquery.min.js"></script>
    <script src="admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Page level plugin JavaScript-->
    <script src="admin/vendor/chart.js/Chart.min.js"></script>
    <script src="admin/vendor/datatables/jquery.dataTables.js"></script>
    <script src="admin/vendor/datatables/dataTables.bootstrap4.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="admin/js/sb-admin.min.js"></script>

    <!-- Demo scripts for this page-->
    <script src="admin/js/demo/datatables-demo.js"></script>
    <script src="admin/js/demo/chart-area-demo.js"></script>

  </body>

</html>
