<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="database.jsp.*"%>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="com.login.*" %>



<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Register</title>

    <!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="admin/css/sb-admin.css" rel="stylesheet">
    
    
    <script type="text/javascript">
    
    function validateForm()
    {
        var a=document.forms["Register"]["uname"].value;
        var b=document.forms["Register"]["password"].value;
        var c=document.forms["Register"]["confirm_pass"].value;        
        
        if(a==null || a=="" || a==" ")
        {
            alert("Please Fill your username");
            return false;
        }
        
        if(b==null || b=="" || b==" ")
        {
            alert("Please Fill your password");
            return false;
        }
        
        if(a==null || a=="" || a==" ")
        {
            alert("Please Re-Fill your password");
            return false;
        }
        
        if(b != c)
        {
        	alert("password does not match please re-enter");
        	return false;
        }
                       
        var emailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
		var e = document.forms["Register"]["e-mail"].value;
		if(emailformat.test(e))
		{
			return true;
		}
		else
		{
			alert("invalid email");
			return false;
		}
        
    }
    
    </script>

  </head>

  <body class="bg-dark">

    <div class="container">
    <%
		
			if(session.getAttribute("message")!=null)
			{
				
    			out.println(session.getAttribute("message"));
    			session.removeAttribute("message");
    		}
    	%>
      <div class="card card-register mx-auto mt-5">
        <div class="card-header">Register an Account</div>
        <div class="card-body">
          <form method="POST" action="Registration" onsubmit="return validateForm()" name="Register">
            <div class="form-group">
              <div class="form-label-group">                                  
                    <input type="text" id="firstName" class="form-control" placeholder="Username" 
                    required="required" autofocus="autofocus" name="uname">
                    <label for="firstName">Username</label>                                               
              </div>
            </div>
            <div class="form-group">
              <div class="form-label-group">
                <input type="email" id="inputEmail" class="form-control" placeholder="Email address"
                 required="required" name="e-mail">
                <label for="inputEmail">Email address</label>
              </div>
            </div>
            <div class="form-group">
              <div class="form-label-group">
                <input type="password" id="inputPass" class="form-control" placeholder="Password"
                 required="required" name="password">
                <label for="inputEmail">Password</label>
              </div>
            </div>
            <div class="form-group">
              <div class="form-label-group">
                <input type="password" id="inputConfirm" class="form-control" placeholder="Confirm Password"
                 required="required" name="confirm_pass">
                <label for="inputConfirm">Confirm Password</label>
              </div>
            </div>
            <!-- class="btn btn-primary btn-block" href="login.html">Register</a-->
            <input type="submit" value="Register" class="btn btn-primary btn-block">
            
          </form>
          <div class="text-center">
            <a class="d-block small mt-3" href="login.jsp">Login Page</a>
            <!-- a class="d-block small" href="forgot-password.html">Forgot Password?</a-->
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  </body>

</html>
