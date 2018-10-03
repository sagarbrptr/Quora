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

    <title>Blog Home - Start Bootstrap Template</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">

  </head>

  <body>

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <a class="navbar-brand" href="#">PICT QUORA</a>
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">Home</a>
          </li>
          <li>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</li>
          <li>

            <form class="form-inline" action="search_question.jsp">
              <input class="form-control mr-sm-2" type="text" placeholder="Search for..." name="question_to_be_searched">
              <button class="btn btn-success" type="submit">Go</button>
            </form>
          </li>
        </ul>

        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="#">Login
                <span class="sr-only">(current)</span>
              </a>
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

          <h1 class="my-4">TRENDING QUESTIONS

          </h1>
           
          <% ArrayList<question_answer> result = question_answer.search_question_answer("");
          
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
                            
                            out.println("<p class='card-text'>"+
                                            result.get(i).ans.answer + " " + 
                                          "</p>");
                                 
                        
                            
                            out.println("<a href='" + "display_answer.jsp?que_id="+
                                            result.get(i).que.que_id +"' class='btn btn-primary'>"
                                            +"Read More &rarr;</a>"+ 
                                        "</div>"+
                                        "<div class='card-footer text-muted'>"
                                           + "Posted by "
                                           + "<a href='#'>"
                                           +    result.get(i).que.username
                                           +   "</a>"
                                           +"</div>"
                                    +"</div>");

                        }
		}
           %>
          

          <!-- Pagination -->
          <ul class="pagination justify-content-center mb-4">
            <li class="page-item">
              <a class="page-link" href="#">&larr; Older</a>
            </li>
            <li class="page-item disabled">
              <a class="page-link" href="#">Newer &rarr;</a>
            </li>
          </ul>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

          <!-- Search Widget -->
          <br><br>
            <h5><button type="button" class="btn btn-danger btn-lg btn-block">ASK QUESTION</button>
            </h5>



          <!-- Categories Widget -->
          <div class="card my-4">
            <h5 class="card-header">Categories</h5>
            <div class="card-body">
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Admissions</a>
                    </li>
                    <li>
                      <a href="#">Fees</a>
                    </li>
                    <li>
                      <a href="#">Cut-off</a>
                    </li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled mb-0">
                    <li>
                      <a href="#">Confessions</a>
                    </li>
                    <li>
                      <a href="#">Competitive-Coding</a>
                    </li>

                  </ul>
                </div>
              </div>
            </div>
          </div>

          

        </div>

      </div>
      <!-- /.row -->

    </div>
    <!-- /.container -->

    <!-- Footer -->
    <footer class="py-5 bg-dark">
      <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Your Website 2018</p>
      </div>
      <!-- /.container -->
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  </body>

</html>
