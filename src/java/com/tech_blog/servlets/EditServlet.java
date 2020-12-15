/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.servlets;

import com.tech_blog.entities.Account;
import com.tech_blog.entities.Message;
import com.tech_blog.helpers.ProfilePic;
import db_obj.Account_obj;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            // form data should be multipart so inform this info... add this "@MultipartConfig"
            
            String email = request.getParameter("profile_email");
            String password = request.getParameter("profile_pw");
            String about = request.getParameter("profile_about");
            
            // for receving image
            Part imageinfo = request.getPart("profile_img");
            String img = imageinfo.getSubmittedFileName();
            
            // get the user from session
            HttpSession s = request.getSession();
            Account acc = (Account)s.getAttribute("current_user");
            
            
            // now set info...
            
            acc.setEmail(email);
            acc.setPassword(password);
            acc.setAbout(about);
            
            /*
               delete previous pic
                    hold previos image...
               String pre_img = acc.getProfile();
                    now set path...
                String pre_path = "request.getRealPath("/")+"profile_pics"+File.separator+pre_img";
                ProfilePic.deleteFile(pre_path);
            
            now the previous uploaded file is deleted...
            */
            
            
            // check image choosen or not... if image not choosen then do nothing 
            // if image choosen then "setProfile"
            
            if(img != "")
                acc.setProfile(img);
            
            
            // Account_obj having function for update user info...
            Account_obj obj = new Account_obj();
            boolean b = obj.updateUser(acc);
            
            // create Message reference for printing message...
            Message m;
            
            if(b)
            {
                //out.println("User updated");
                m = new Message("User Updated Successfully... \n", "success", "alert-success");
                        
                // store this message in session
                //HttpSession s = request.getSession(); "we have already session's object"
                s.setAttribute("msg", m);
                
                // now goes to img directory
                
                String path = request.getRealPath("/")+"profile_pics"+File.separator+acc.getProfile();
                
                
                // check image is not chhosen then do nothing...
                // if image choosen then delete previous image and set choosen image...
                if(img != "")
                {
                    
                    // now delete this file
                    ProfilePic.deleteFile(path);
                
                    if(ProfilePic.saveFile(imageinfo.getInputStream(), path))
                    {
                        //out.println("Profile pic updated");
                        
                        m = new Message("Profile Pic Updated Successfully\n", "success", "alert-success");
                        
                        // store this message in session
                        //HttpSession s = request.getSession();  "we have already session's object"
                        s.setAttribute("msg", m);

                    }
                    else
                    {
                        // out.println("profile pic not updated");
                        
                        m = new Message("OOps! Profile Pic Not Updated... Plese Try Again Later!!!\n", "error", "alert-danger");
                        
                        // store this message in session
                        //HttpSession s = request.getSession(); "we have already session's object"
                        s.setAttribute("msg", m);
        
                    }

                }
                else
                {
                    // out.println("progile pic not change");
                    
                    m = new Message("Profile Pic Not Changed... \n", "error", "alert-danger");
                        
                    // store this message in session
                    //HttpSession s = request.getSession(); "we have already session's object"
                    s.setAttribute("msg", m);
                }
                
            }
            else
            {
                out.println("User not updated");
                m = new Message("OOps! Something Went Wrong... User Info Not Updated... Plese Try Again Later!!!\n", "error", "alert-danger");

                // store this message in session
                //HttpSession s = request.getSession(); "we have already session's object"
                s.setAttribute("msg", m);
            }
            
            // redirect into Profile.jsp page
            response.sendRedirect("Profile.jsp");
            
            out.println("</body>");
            out.println("</html>");
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
