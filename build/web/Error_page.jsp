<%-- 
    Document   : Error_page
    Created on : 23 Apr, 2020, 12:52:23 PM
    Author     : Sumit Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        
        <!-- attatch bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- font awesome icons --- w3school.com -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
        
    </head>
    <body>
        
        <div class="container text-center">
            <img src="img/error.png" class="image-fluid">
            <h3 class="display-3">Sorry! Something Went Wrong</h3>
            
            <%= exception %>
            
            <a href="index.jsp" class="btn btn-lg text-white mt-3" style="background-color: #3d5afe"> Home </a>
        </div>
        
    </body>
</html>
