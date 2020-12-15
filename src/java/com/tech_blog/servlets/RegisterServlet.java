/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.servlets;

import com.tech_blog.entities.Account;
import db_obj.Account_obj;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sumit Kumar
 */
@MultipartConfig
public class RegisterServlet extends HttpServlet {

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
            
            // fetch all data
                String username = request.getParameter("u_name");
                String email = request.getParameter("e_mail");
                String pass_word = request.getParameter("p_word");
                String gender = request.getParameter("gender");
                String about = request.getParameter("about");
                
                if(about.trim().equals(""))     // set by default message if about is empty
                    about = "Hey! I am Using Techblog";
                
                // set current date
                Date date = new Date();
                SimpleDateFormat dateformate = new SimpleDateFormat("dd MMMM, yyyy  hh:mm a");
                String currentDate = dateformate.format(date);
                
                
                String check = request.getParameter("check");
                
                if(username.length()<3)
                    out.println("Plese Enter Valid Username Having Atleast 3 Characters");
                else if(email.isEmpty())
                    out.println("Plese Enter Valid Email");
                else if(pass_word.isEmpty())
                    out.println("Plese Enter Valid Password");
                else if(gender == null)
                    out.println("Plese Select Gender");
                else if (check == null)
                    out.println("Plese Agree Terms and Condition");
                
                else
                {
                    Account_obj acc = new Account_obj();

                    Account ac = new Account(username, email, pass_word, gender, about, currentDate);

                    if(acc.addUser(ac))
                        out.println("Successfully Registered...");
                    else
                        out.println("\n \n Username Already Exists");
                }

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
