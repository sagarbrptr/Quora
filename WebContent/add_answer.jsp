<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 

<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>ADD ANSWER</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">
	
	<script type="text/javascript">
	function validateForm() {
	    var x = document.forms["answer_form"]["answer"].value;
	    if (x == ""|| x==" ") {
	        alert("Answer can't be null ");
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
          </li>
          <li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
          <li>
	<form class="d-none d-md-inline-block form-inline mr-auto ml-0 ml-md-5 my-2 my-md-0" action="admin_search_question.jsp" method="post">
        <div class="input-group">
         

              <input type="text" class="form-control" name="question_to_be_searched"
              placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
              <div class="input-group-append">
	            <button type="submit" class="btn btn-primary" >
	              <i class="fa fa-search"></i>
	            </button>
	          </div>

            
          
        </div>
      </form>
          </li>
        </ul>

        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
           

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
		out.println("<a class=\"btn btn-outline-primary text-light\" href='login.jsp' >Login</a>");
		out.println(
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

          &nbsp&nbsp&nbsp
          <div class="container">
 <% 
 	    				    			
     
 	  if(request.getMethod().equals("GET"))
	  {
 		 String que_id = (String) session.getAttribute("que_id_integer");
         int que_id_int = Integer.parseInt(que_id);	
           		  
           question q = question.display_single_question(que_id_int); 
               out.println("<h1 class='my-4'>"+q.question+
               "</h1><br>");
		  	out.println("<form action='add_answer.jsp' method='POST' name='answer_form' onsubmit='return validateForm()'>" + 		 
		    			"<div class='form-group'>"+
		      			"<textarea class='form-control' rows='5' id='comment' name=\"answer\"></textarea>"
		      +"<!-- input type = 'hidden' name='calling_page' value='question_page'-->"+
		    "</div>"+
		    "<button type='submit' class='btn btn-primary btn-lg'>SUBMIT</button>"+		  
		  "</form>");		  	
	  }
  
	  else
	  {
		  
		  String que_id = (String) session.getAttribute("que_id_integer");
	         int que_id_int = Integer.parseInt(que_id);	
		  int done_insertion = answer.insert_answer((int)session.getAttribute("user_id"),que_id_int,request.getParameter("answer"));
			
			if(done_insertion == 1)
			{
				out.println("Answer Inserted Successfully");
				session.setAttribute("message","<div class=\"alert alert-success alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+	"<strong>Successfully</strong> inserted Answer </div>");
			}
			
			if(done_insertion == 0)
			{
				out.println("Error occured during answer insertion");
				session.setAttribute("message","<div class=\"alert alert-danger alert-dismissible\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+	"Answer insertion <strong>failed.</strong> Please try again after some time.</div>");
			}  
			//session.removeAttribute("que_id_integer");
			if(session.getAttribute("admin")!=null)
				response.sendRedirect("admin_display_answer.jsp?que_id="+que_id_int);
			else
				response.sendRedirect("display_answer.jsp?que_id="+que_id_int);
	  }
  %>
  </div>
  </div>
  </div>

         
          <!-- Pagination -->
          

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Search Widget -->
          <br><br>
           



      <!-- /.row -->

    </div>
    <!-- /.container -->
 

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    

  </body>

</html>