<%@page import="com.tech_blog.entities.Account"%>
<%@page import="db_obj.Like_obj"%>
<%@page import="java.util.List"%>
<%@page import="com.tech_blog.entities.Post"%>
<%@page import="db_obj.Post_obj"%>


<!-- get session -->
<%
    Account curr_user = (Account) session.getAttribute("current_user");
%>


    <div class="row">
    <% 
        // for shows the loader so, thread sleep for 1 sec
        Thread.sleep(1000);

        //for calling the method getAllPost or getPostById
        Post_obj obj = new Post_obj();
        
        //for storing the posts
        List<Post> posts = null;
        
        // fetech the value of category id
        int cid = Integer.parseInt(request.getParameter("cid"));
        
        // it means not any category selected so, show all post
        if(cid == 0)
        {
            posts = obj.getAllPost();
        }
        else
        {
             posts = obj.getPostByCatId(Integer.toString(cid));
        }
        
        // check post are available or not
        if(posts.size() == 0)
        {
            out.println("<h4 class='display-3 text-center'>No Posts are available in this category... </h4>");
            return ;
        }
        
        for(Post p: posts)
        {
            
    %>
    
        <div class="col-md-6 mt-2">
            <div class="card">
                <img class="card-img-top" src="blog_pics/<%= p.getPic() %>" alt="post image">
                <div class="card-body">
                    <b><%= p.getTitle() %></b>
                    <p><%= p.getContent() %></p>
                </div>
                <div class="card-footer text-center">
                    <!-- fetch session for knowing who is login, we are already fetch session and all information in 'ac' -->
                            
                            <!-- pass email as string because email having '.' symbol -->
                            <!-- show total likes...    Like_obj having method "countLikesOnPost(int p_id)" -->
                            <!-- in spane tag class="like_counter" for display updated likes -->
                            <!-- refer <script src="js/likeModule.js"> -->
                        
                    <%
                        Like_obj like = new Like_obj();
                    %>
                    <a href="#!" onclick="doLike(<%= p.getP_id() %>, '<%= curr_user.getEmail() %>')" class="btn btn-outline-primary btn-sm mr-sm-3"> <i class="fa fa-thumbs-o-up"></i><span class="like_counter"><%= like.countLikesOnPost(p.getP_id()) %></span></a>
                    <a href="#" class="btn btn-outline-success btn-sm mr-sm-3"> <i class="fa fa-commenting-o"></i><span>20</span></a>
                    
                    <!-- with the help of url-rewriting we pass the post id and go to the show_blog page -->
                    <a href="show_blog.jsp?post_id=<%= p.getP_id() %>" class="btn btn-primary btn-sm">Read More</a>
                </div>
            </div>    
        </div>
        
    <%
        
        }

    %>
    
    </div>
    
    <!-- js for like module -->
    <script src="js/likeModule.js"></script>