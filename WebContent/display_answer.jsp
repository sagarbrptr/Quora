<%-- 
    Document   : display_answer
    Created on : 1 Oct, 2018, 11:56:45 PM
    Author     : sagar
--%>

<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>PICT QUORA</title>
    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">

  </head>

  <body>
	<%
		
			if(session.getAttribute("message")!=null)
			{
				
    			out.println(session.getAttribute("message"));
    			session.removeAttribute("message");
    		}
    	%>
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
	<form class="d-none d-md-inline-block form-inline mr-auto ml-0 ml-md-5 my-2 my-md-0" action="search_question.jsp" method="post">
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
               <%
               if((request.getSession(false) == null) || (session.getAttribute("username")==null) )
				{
					//out.println("IGI");
					out.println("<a class=\"btn btn-outline-primary text-light\" href='login.jsp' >Login</a>");
					out.println("<a class=\"btn btn-outline-primary text-light\" href='register.jsp' >Register</a>");
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

        <%
            out.println("<br><h2 class='card-title'>"); 
            int que_id_integer=Integer.parseInt(request.getParameter("que_id"));
            		  
            question q = question.display_single_question(que_id_integer); 
                out.println(q.question);
            out.println("</h2>");
            
            ArrayList<answer> result = answer.display_answer(que_id_integer);
          
                if(result.size() == 0)
                {
                	out.println("No Answers for the given question");
                	session.setAttribute("que_id_integer",request.getParameter("que_id"));		// !!!!! SAGAR WAS HERE !!!!
                			out.println("<br><br>   <a href='add_answer.jsp' class='btn btn-primary'>Add Answer &rarr;</a>");
                        
						//out.println(request.getParameter("que_id"));
						
						
				}	
                
				else
				{
					for(int i=0;i<result.size();i++)
					{
                            //<!-- Blog Post -->
                            out.println(
                                    "<div class='card mb-4'>"+
                                        "<div class='card-body'>"
                                         );
                                        
                            
                            
                            out.println("<table><tr><td width=\"90%\">"+
                            				"<p class='card-text'>"+
                                            result.get(i).answer + " " + 
                                          "</p></td>");
                            if(session.getAttribute("username")!=null&&session.getAttribute("username").equals(result.get(i).username))
                            {
                       
                            	out.println("<td width=\"5%\"><form action='modify_answer.jsp' method= post style='display:inline-block;'>"
                                  	+	"<input type='hidden' name='type' value='1'>"	//1 for modify answer
                                  	+	"<input type='hidden' name='ans_id' value='"+result.get(i).ans_id +"'>"
                                  	+	"<input type='hidden' name='answer' value='"+result.get(i).answer +"'>"
                                  			+	"<input type='hidden' name='que_id' value='"+que_id_integer +"'>"
                                   +	"<button  type='submit' style=' border:none;' class='fa fa-edit w3-xxxlarge '></button></form></td>");
                            }
                            out.println("</tr></table>");     
                        
                            
                            out.println("</div>"+
                                        "<div class='card-footer text-muted'>"
                                        		 + "Posted by "
                                                 + "<b>"
                                                 +    result.get(i).username
                                                 +   "</b> on "
                                                 + 	result.get(i).Date +
                                                 "<br>"
                                           +	"<form action='vote.jsp' method= post style='display:inline-block;'>"
                                         	+	"<input type='hidden' name='type' value='2'>"
                                         	+ 	"<input type='hidden' name='que_id' value='"+request.getParameter("que_id") +"'>"
                                         	+	"<input type='hidden' name='ans_id' value='"+result.get(i).ans_id +"'>"
                                          +	"<button type='submit' style=' margin: 0px 0px 0px 500px; border:none;' class='fa fa-thumbs-up w3-xlarge'></button>"
                                         	+	answer.get_up_vote(result.get(i).ans_id)+
                                       		   "</form>"
                                          
                                       	+	   "<form action='vote.jsp' method= post style='display:inline-block;'>"
                                        	+	"<input type='hidden' name='type' value='3'>"
                                        			+ 	"<input type='hidden' name='que_id' value='"+request.getParameter("que_id") +"'>"
                                        	+	"<input type='hidden' name='ans_id' value='"+result.get(i).ans_id +"'>"
                                         +	"<button type='submit' style=' margin:0px 0px 0px 10px; border:none;' class='fa fa-thumbs-down w3-xlarge'></button>"
                                       		  +	answer.get_down_vote(result.get(i).ans_id)+
                                      		   "</form>"
                                          +"</div>"
                                   +"</div>");
                            
     

                        }
			
						//out.println(request.getParameter("que_id"));
						
						session.setAttribute("que_id_integer",request.getParameter("que_id"));
						
					//request.setPa						
						//out.println("<form action='add_answer.jsp' method='GET' style='display:inline-block;'"
							//	+ 	"<input type='hidden' name='que_id' value='"+request.getParameter("que_id") +"'>"
								//+ "<button type='submit' class='btn btn-primary'> Add Answer </button></form>");
						
                        out.println("<a href='add_answer.jsp' class='btn btn-primary'>Add Answer &rarr;</a><br>");
		}
                
                
        %>
         
          

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

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Search Widget -->
         <br><br>
 			<h5><button type="button" class="btn btn-danger btn-lg btn-block" 
            onclick="location.href = 'add_question.jsp';">ASK QUESTION</button>
            </h5>



          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">Keywords</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Admission">Admission</a>
                    </li>
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Fees">Fees</a>
                    </li>
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Cut-off">Cut-off</a>
                    </li>
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Campus">Campus</a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Faculty">Faculty</a>
                    </li>
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Placement">Placement</a>
                    </li>
					 <li>
                      <a href="search_question.jsp?question_to_be_searched=Assignment">Assignment</a>
                    </li>
                    <li>
                      <a href="search_question.jsp?question_to_be_searched=Event">Event</a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
			<ul><br><br><br><br><br><br><br><br></ul>
          

        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark" style="bottom: 0; width: 100%;">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; PICT Quora 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
