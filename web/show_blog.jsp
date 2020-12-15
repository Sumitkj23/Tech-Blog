<%-- 
    Document   : show_blog
    Created on : 28 Apr, 2020, 10:46:44 PM
    Author     : Sumit Kumar
--%>

<%@page import="db_obj.Like_obj"%>
<%@page import="db_obj.Account_obj"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech_blog.entities.Category"%>
<%@page import="com.tech_blog.entities.Post"%>
<%@page import="db_obj.Post_obj"%>
<%@page import="com.tech_blog.entities.Account"%>
<%@page errorPage="Error_page.jsp" %>        <!-- show error.jsp page -->

<!-- validation, means if user are login then he/she look login.jsp page otherwise it will redirecting to Login.jsp page -->
<%
    Account ac = (Account) session.getAttribute("current_user");

    if (ac == null) {
        response.sendRedirect("Login.jsp");
    }
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- get Post id -->
<%
    String pid = request.getParameter("post_id");

    // fetch all the data of selected post by using method "getPostById"
    Post_obj get_post = new Post_obj();
    Post show_post = get_post.getPostByPostId(pid);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= show_post.getTitle()%></title>

        <!-- attatch bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- font awesome icons --- w3school.com -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        
        <!-- css for class :- post_title, post_content, post_code -->
        <style>
            
            body
            {
                background: url(img/IMG_20200112_231256.jpg);
                background-size: cover;
                background-attachment: fixed;
            }
            
            .post_title
            {
                font-weight: 100;
                font-size: 30px;
            }
            
            .post_content
            {
                font-size: 25px
            }
            
            .user_emailid
            {
                font-size: 20px;
                margin-left: 5px;
            }
            
            .row_user
            {
                border: 1px solid #e2e2e2;
                padding-top: 10px
            }
            
        </style>
        

    </head>
    <body>


        <!-- navbar -->

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#"> <span class="fa fa-asterisk"></span>Tech Blog</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp"><span class="fa fa-bank"></span>Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="fa fa-check-square-o"></span>Categories
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Programming Language</a>
                            <a class="dropdown-item" href="#">Data Structure</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Project</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#"> <span class="fa fa-child"></span>Contact Us</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#add_post_modal"> <span class="fa fa-address-card-o"></span>Post</a>
                    </li>


                </ul>

                <ul class="navbar-nav mr-right">

                    <!-- data-toggle and data-target attatch with Username button(a tag...)-->

                    <li class="nav-item">
                        <a class="nav-link" href="#!" data-toggle="modal" data-target="#exampleModal"> <span class="fa fa-user-circle"> <%= ac.getUsername()%> </span> </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="LogoutServlet"> <span class="fa fa-user-circle"></span>Logout</a>
                    </li>
                </ul>

            </div>
        </nav>
        <!-- end navbar -->
        
        
        <!-- profile model -->

        <!-- Button trigger modal -->
        <!-- <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
          Launch demo modal
        </button> -->

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header text-white" style="background-color: #2196f3">
                        <h4 class="modal-title" id="exampleModalLabel"> TechBlog </h4>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <!-- write here user name -->
                        <div class="container text-center">
                            <img src="profile_pics/<%= ac.getProfile()%>" class="img-fluid" style="border-radius:50%; max-width: 150px">
                            <h4> <%= ac.getUsername()%> </h4>

                            <div id="profile_details">
                                <!-- user details -->
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th scope="row">Id</th>
                                            <td> <%= ac.getId()%> </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Email</th>
                                            <td> <%= ac.getEmail()%> </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">Status</th>
                                            <td> <%= ac.getAbout()%> </td>
                                        </tr>

                                        <!--
                                        <tr>
                                          <th scope="row">Register Date</th>
                                          <td>  </td>
                                        </tr>
                                        -->
                                    </tbody>
                                </table>
                            </div>
                            <!-- profile edit -->
                            <div id="profile_edit" style="display : none">
                                <h3 class="mt-3"> Plese Edit Carefully</h3>

                                <!-- enctype for mutidata(text,images...) -->

                                <form action ="EditServlet" method="post" enctype="multipart/form-data">
                                    <table class="table">
                                        <tr>
                                            <td>Id</td>
                                            <td> <%= ac.getId()%> </td>
                                        </tr>
                                        <tr>
                                            <td>Username</td>
                                            <td> <%= ac.getUsername()%> </td>
                                        </tr>
                                        <tr>
                                            <td>Gender</td>
                                            <td> <%= ac.getGender()%> </td>
                                        </tr>
                                        <tr>
                                            <td>Email</td>
                                            <td> <input type="email" class="form-control" name="profile_email" value="<%= ac.getEmail()%>"> </td>
                                        </tr>
                                        <tr>
                                            <td>Password</td>
                                            <td> <input type="password" class="form-control" name="profile_pw" value="<%= ac.getPassword()%>"> </td>
                                        </tr>
                                        <tr>
                                            <td>About</td>
                                            <td> 
                                                <textarea rows="3" type="text" class="form-control" name="profile_about"><%= ac.getAbout()%></textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Change Profile Picture</td>
                                            <td> 
                                                <input type="file" name="profile_img" class="form-control">
                                            </td>
                                        </tr>

                                    </table>
                                    <div class="container">
                                        <button type="submit" class="btn btn-outline-success">Save</button>
                                    </div>
                                </form>

                            </div>            


                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="edit_button" type="button" class="btn btn-primary">Edit</button> &nbsp; &nbsp;
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- end profile modal -->                                 


        <!-- post modal -->

        <div class="modal fade" id="add_post_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Post Details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">

                        <form id="addPost" action="AddPostServlet" method="post">
                            <div class="form-group">
                                <select class="form-control" name="p_id">
                                    <option selected disabled>--- Select Category ---</option>
                                    
                                    <%
                                        Post_obj post = new Post_obj();
                                        ArrayList<Category> list = post.getCategories();
                                        for(Category c:list)
                                        {
                                    %>
                                    <option value="<%= c.getC_id() %>"> <%= c.getName() %> </option>
                                    <%
                                        }
                                        
                                    %>
                                </select>
                            </div>

                            <div class="form-group">
                                <input name="ptitle" class="form-control" type="text" placeholder="Enter Post Title" required>
                            </div>

                            <div class="form-group">
                                <textarea name="pcontent" class="form-control" style="height : 160px" placeholder="Enter Your Content" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <textarea name="pcode" class="form-control" style="height : 170px" placeholder="Enter Your Program (If Any)"></textarea>
                            </div>
                            <div class="form-group">
                                <label>Choose Your Image</label> &nbsp;&nbsp;
                                <input name="pimg" type="file" class="form-control">
                            </div>
                                
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary">Post</button> &nbsp; &nbsp; &nbsp;
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            </div>
                        </form>

                    </div>
                    
                </div>
            </div>
        </div>


        <!-- end post modal -->




        <!-- main content of the body -->

        <div class="container">
            <div class="row my-4">
                <div class="col-md-8 offset-md-2">
                    
                    <div class="card">
                        <div class="card-header">
                            <h4 class="post_title"><%= show_post.getTitle() %></h4>
                        </div>
                        
                        <div class="card-body">
                            <img class="card-img-top my-2" src="blog_pics/<%= show_post.getPic() %>" alt="post image">
                            
                            <div class="row my-3   row_user">
                                <div class="col-md-8">
                                    <!-- fetch username -->
                                    <% 
                                        Account_obj obj = new Account_obj();
                                        Account user = obj.getUserByEmailId(show_post.getEmail_id());
                                    %>
                                    <!-- show username -->
                                    <p class="user_emailid"> Posted By :-  <a href=""> <%= user.getUsername() %> </a> </p>
                                </div>
                                <div class="col-md-4">
                                    <!-- time -->
                                    
                                    <p class="user_emailid"> <%= show_post.getEmail_id() %></p>
                                </div>
                            </div>
                
                            <p class="post_content"><%= show_post.getContent() %></p>
                                            <br> <br>
                            <div class="post_code">
                                <pre><%= show_post.getCode() %></pre>
                            </div>
                        </div>
                        <div class="card-footer"> 
                            <!-- fetch session for knowing who is login, we are already fetch session and all information in 'ac' -->
                            
                            <!-- pass email as string because email having '.' symbol -->
                            <!-- show total likes...    Like_obj having method "countLikesOnPost(int p_id)" -->
                            <!-- in spane tag class="like_counter" for display updated likes -->
                            <!-- refer <script src="js/likeModule.js"> -->
                            <%
                                Like_obj postlike = new Like_obj();
                            %>
                            <a href="#!" onclick="doLike(<%= show_post.getP_id() %>, '<%= ac.getEmail() %>')" class="btn btn-outline-primary btn-sm mr-sm-3"> <i class="fa fa-thumbs-o-up"></i><span class="like_counter"> <%= postlike.countLikesOnPost(show_post.getP_id() ) %> </span></a>
                            <a href="#" onclick="hii()" class="btn btn-outline-success btn-sm mr-sm-3"> <i class="fa fa-commenting-o"></i><span>20</span></a>
                    
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>

        <!-- end main content of the body -->

        
    </body>
    
    
    <!-- attatch jquery official website -->
        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

        <!-- attatch js bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

        <!-- ajax for sweetalert cdn -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

        
        <!-- edit form js... -->
        <script>

            $(document).ready(function ()
            {
                let editStatus = false;

                $('#edit_button').click(function ()
                {
                    if (editStatus == false)
                    {
                        $('#profile_details').hide();
                        $('#profile_edit').show();
                        $(this).text("Back")
                        editStatus = true;
                    } else
                    {
                        $('#profile_details').show();
                        $('#profile_edit').hide();
                        $(this).text("Edit")
                        editStatus = false;
                    }
                })
            });

        </script>

        <!-- add post js -->
        <script>
            
            $(document).ready(function()
            {
               $('#addPost').on('submit', function(event)
               {
                   // this code runs when form is submitted
                   event.preventDefault();
                   
                   console.log("submitted");
                   
                    // now stors the form data
                   let form = new FormData(this);
                   
                   // now requesting to server
                   
                   $.ajax({
                       url : "AddPostServlet",
                       type : 'POST',
                       data : form,
                       success: function (data, textStatus, jqXHR) {
                        // success
                        //show confirm alert 
                        
                        if(data.trim()=='post successfully')
                            swal("Good job!", "Post Successfully saved...", "success");
                        else
                            swal("Error!", "Something went wrong...", "error");
                            
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        // error
                         swal("Error!", "Something went wrong...", "error");
                    },
                    
                    // because form having photos so processData and contentType should be se as false
                    processData: false,
                    contentType: false
                   });
                   
               });
            });
            
        </script>
        
        
        <!-- attatch my own js file -->
        <script src="js/likeModule.js">
            
            
            
            function hii()
            {
                console.log("hii");
            }
        </script>
        
    
    
</html>
