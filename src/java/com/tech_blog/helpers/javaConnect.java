/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sumit Kumar
 */
public class javaConnect {
    
    private static Connection conn;
    public static Connection connectDb()
    {
        try
        {
            // driver class loaded...
            Class.forName("org.sqlite.JDBC");
            
            // create a connection...
            conn = DriverManager.getConnection("jdbc:sqlite:F:\\programs\\servlet\\Tech_Blog\\tech_blog.sqlite");
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }
    
}

