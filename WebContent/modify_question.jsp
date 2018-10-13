<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="com.login.*"%>
<%@ page import="java.util.ArrayList" %> 

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ASK QUESTION</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">
	<script type="text/javascript">
	function validateForm() {
	    var x = document.forms["question_form"]["question"].value;
	    if (x == ""|| x==" ") {
	        alert("Question can't be null ");
	        return false;
	    }
	} 
	</script>
  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <% if(session.getAttribute("admin")==null)
	  	{
	  		out.println("<a class=\"navbar-brand\" href=\"index.jsp\">PICT QUORA</a>");
	  	}
      	else
      	{
      		out.println("<a class=\"navbar-brand\" href=\"admin_dashboard.jsp\">PICT QUORA</a>");
      	}
       %> 
        <ul class="navbar-nav">
          <li class="nav-item">
         </li></ul>  

        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
               <%
	            if((request.getSession(false) == null) || (session.getAttribute("username")==null) )
					{
						//out.println("IGI");
						session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible fade show\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
		+	"Please <strong>Login</strong> first</div>");
						response.sendRedirect("login.jsp");
						out.println("<a class='nav-link' href='login.jsp'>Login"+
				                "<span class='sr-only'>(current)</span>"
				                +"</a>");
						 				
					}
				
					else
					{
						out.println("<form method='get' action='Logout'>"
			 				    +
			 				    "<button class=\"btn btn-outline-primary text-light\" type='submit' value='Logout' >Logout"	+"</button>"
			 				  
			 				 +"</form>");
					}
            %>
            </li>

          </ul>
        </div>
      </div>
    </nav>

    <!-- Page Content -->
    <div class="container">

      <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

          &nbsp&nbsp&nbsp<h1 class="my-4">Enter modified question</h1><br>
          <div class="container">
  <% 
  	if(request.getMethod().equals("POST"))
	  {
	  String original_question = request.getParameter("question");
	  //out.println(original_question);
	  		
		  	out.println("<form action='modify.jsp' method='POST' name='question_form' onsubmit='return validateForm()'>" + 		 
		    			"<div class='form-group'>"+
		      			"<textarea type=\"text\" class='form-control' rows='5' id='comment' name='question' >"+original_question+"</textarea>"
		      +"<input type = 'hidden' name='type' value='0'>" //for question
		      +	"<input type='hidden' name='que_id' value='"+request.getParameter("que_id") +"'>"
            	+
		    "</div>"+
		    "<button type='submit' class='btn btn-primary btn-lg'>SUBMIT</button>"+		  
		  "</form>"+
		"</div>"+		 
		"</div>"+
		"</div>");		  	
	  }
  
	  else
	  {
		  
	  }
  %>

         
          <!-- Pagination -->
          

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">
		<ul>
		
		<br><br><br><br>
		
		</ul>
          <!-- Search Widget -->
          <br><br>
           



      <!-- /.row -->

    </div>
    
    <!-- /.container -->
 	
 	<!-- Footer -->
 	
    <footer class="py-5 bg-dark" >
      <div class="container"  >
        <p class="m-0 text-center text-white">Copyright &copy; PICT Quora 2018</p>
      </div>
      <!-- /.container -->
    </footer>
 	

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>