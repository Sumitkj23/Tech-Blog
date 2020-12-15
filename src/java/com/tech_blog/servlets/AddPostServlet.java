/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.servlets;

import com.tech_blog.entities.Account;
import com.tech_blog.entities.Message;
import com.tech_blog.entities.Post;
import com.tech_blog.helpers.ProfilePic;
import db_obj.Post_obj;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Sumit Kumar
 */

@MultipartConfig
public class AddPostServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String id = request.getParameter("p_id");
            String title = request.getParameter("ptitle");
            String content = request.getParameter("pcontent");
            String code = request.getParameter("pcode");
            
            Part p = request.getPart("pimg");
            String img = p.getSubmittedFileName();
            
            //set date
            Date d =new Date();
            SimpleDateFormat df = new SimpleDateFormat("dd MMMM, yyyy");
            String postDate = df.format(d);
            
            // get user email
            HttpSession s = request.getSession();
            Account acc = (Account)s.getAttribute("current_user");
            String email = acc.getEmail();
            
            Post post = new Post(title, content, code, img, postDate, id, email);
            
            
            Post_obj addpost = new Post_obj();
            
            
            if(addpost.savePost(post))
            {
                
                // get path for choosen image 
                String path = request.getRealPath("/")+"blog_pics"+File.separator+img;
                
                // we have the method for saving images, name as "saveFile" in class "ProfilePic"
                
                if(ProfilePic.saveFile(p.getInputStream(), path))
                    System.out.print("done..........");
                else
                    System.out.println("no.............");
                
                out.println("post successfully");
                
            }
            else
                System.out.println("something went wrong");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
