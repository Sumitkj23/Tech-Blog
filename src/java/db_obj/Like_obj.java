/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_obj;

import com.tech_blog.helpers.javaConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Sumit Kumar
 */
public class Like_obj {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public Like_obj()
    {
        this.conn = javaConnect.connectDb();
    }
    
    public boolean insertLike(int p_id, String email)
    {
        boolean b = false;
        String sql = "insert into Likes(PId, EmailId) values(?,?)";
        
        try
        {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, p_id);
            pst.setString(2, email);
            pst.executeUpdate();
            
            b= true;
            pst.close();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return b;
    }
    
    
    public int countLikesOnPost(int p_id)
    {
        int c = 0;
        String sql = "select count(*) from Likes where PId='"+p_id+"'";
        
        try
        {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next())
                c = rs.getInt(1);  //  we can also write this 'c = rs.getInt("count(*)")'
            
            pst.close();
            rs.close();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return c;
    }
    
    public boolean isLikedByUser(int pid, String email)
    {
        boolean b = false;
        
        String sql = "select * from Likes where PId='"+pid+"' and EmailId='"+email+"'";
        
        try
        {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next())
                b = true;
            
            pst.close();
            rs.close();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return b;
    }
    
    public boolean deleteLike(int pid, String email)
    {
        boolean b = false;
        
        String sql = "delete from Likes where PId='"+pid+"' and EmailId='"+email+"'";
        
        try
        {
            pst = conn.prepareStatement(sql);
            pst.executeUpdate();
            b = true;
            
            pst.close();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return b;
    }
    
}
