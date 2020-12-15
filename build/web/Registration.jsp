<%-- 
    Document   : Registration
    Created on : 21 Apr, 2020, 11:04:14 PM
    Author     : Sumit Kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>

        <!-- attatch bootstrap -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <!-- font awesome icons --- w3school.com -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


    </head>
    <body style="background-color: #3d5afe">

        <%@include file="navbar.jsp" %>

        <main class="p-5">
            <div class="container">
                <div class="col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header  text-white text-center" style="background-color: #2196f3">
                            <span class="fa fa-user-plus fa-3x"></span> 
                            <h3>Register Here</h3>
                        </div>
                        <div class="card-body">

                            <!-- form bootstrap -->
                            <form id="reg_form" action="RegisterServlet" method="post">

                                <div class="form-group">
                                    <label for="user_name">Username</label>
                                    <input type="text" name="u_name" class="form-control" id="user_name" aria-describedby="emailHelp" placeholder="Enter username">
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input type="email" name="e_mail" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>

                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" name="p_word" class="form-control" id="exampleInputPassword1" placeholder="Password">
                                </div>

                                <div class="form-group">
                                    <label for="gender">Select Gender</label>
                                    <br>
                                    <input type="radio" id="gender" name="gender" value="Male">Male &nbsp; &nbsp;
                                    <input type="radio" id="gender" name="gender" value="Female">Female
                                </div>

                                <div class="form-group">
                                    <textarea name="about" class="form-control" id="exampleInputPassword1" placeholder="Enter somethimg about yourself"></textarea>
                                </div>

                                <div class="form-group form-check">
                                    <input type="checkbox" name="check" class="form-check-input" id="exampleCheck1">
                                    <label class="form-check-label" for="exampleCheck1">Agree terms and conditions</label>
                                </div>

                                <div class="container text-center" id="loader" style="display: none">
                                    <span class="fa fa-refresh fa-spin fa-3x"></span>
                                    <h3>Plese Wait!</h3>
                                </div>
                                <br>
                                <button type="submit" class="btn btn-primary" id="submit_btn">Submit</button>
                            </form>


                        </div>
                    </div>
                </div>
            </div>
        </main>

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


        <!-- ajax for proper register messages -->

        <script>
            $(document).ready(function ()
            {
                console.log("loaded...");

                $('#reg_form').on('submit', function (event)
                {
                    event.preventDefault();

                    // store form's data
                    let form = new FormData(this);

                    //hide submit button and show loader
                    $('#submit_btn').hide();
                    $('#loader').show();

                    // send register servlet
                    $.ajax({
                        url: "RegisterServlet",
                        type: 'POST',
                        data: form,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data)

                            //hide submit button and show loader
                            $('#submit_btn').show();
                            $('#loader').hide();

                            if (data.trim() === 'Successfully Registered...')
                            {
                                //show confirm alert
                                swal("Registration Successfully!", "Redirecting login page...", "success");
                                
                                window.location = 'Login.jsp';
                                

                            } else
                                swal(data);

                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            console.log(jqXHR)

                            //hide submit button and show loader
                            $('#submit_btn').show();
                            $('#loader').hide();

                            //show confirm alert
                            swal("Something Went Wrong...", "Try Again Later");

                        },
                        processData: false,
                        contentType: false
                    });

                });
            });
        </script>


    </body>
</html>
