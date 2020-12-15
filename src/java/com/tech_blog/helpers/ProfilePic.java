/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author Sumit Kumar
 */
public class ProfilePic {
    
    public static boolean deleteFile(String path)
    {
        boolean b = false;
        
        try 
        {
            File f = new File(path);
            b = f.delete();  // it returns true if file or dictory deleted successfully if not it return false
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return b;
    }
    
    public static boolean saveFile(InputStream is, String path)
    {
        boolean b = false;
        
        try 
        {
            
            byte by[] = new byte[is.available()];
            
            is.read(by); // it reads data from "is" and pute into "by" array
            
            // now write into file from "by" array
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(by);
            
            fos.flush();
            fos.close();
            b = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return b;
    }
    
}
