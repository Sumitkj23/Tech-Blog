/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_obj;

import com.tech_blog.entities.Category;
import com.tech_blog.entities.Post;
import com.tech_blog.helpers.javaConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sumit Kumar
 */
public class Post_obj {
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    public Post_obj() {
        this.conn = javaConnect.connectDb();
    }
    
    
    // function for fetch categories from db
    public ArrayList<Category> getCategories()
    {
        
        ArrayList<Category> alist;
        alist = new ArrayList<>();
        
        try 
        {
            String sql = "select * from Categories";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next())
            {
                int c_id = rs.getInt("C_Id");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                Category c = new Category(c_id, name, description);
                alist.add(c);
            }
            
            pst.close();
            rs.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return alist;
        
    }

    
    public boolean savePost(Post p)
    {
        boolean b =false;
        
        try
        {
            String sql = "insert into Posts(Title, Content, Code, Pic, Date, Cat_Id, EmailId) values(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, p.getTitle());
            pst.setString(2, p.getContent());
            pst.setString(3, p.getCode());
            pst.setString(4, p.getPic());
            pst.setString(5, p.getP_date());
            pst.setString(6, p.getCat_id());
            pst.setString(7, p.getEmail_id());
            pst.executeUpdate();
            
            b = true;
            pst.close();
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return b;
    }
    
    public List<Post> getAllPost()
    {
        List<Post> list = new ArrayList<>();
        
        // fetch all post
        String sql = "select * from Posts";
        try
        {
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               int pid = Integer.parseInt(rs.getString("P_Id"));
               String title = rs.getString("Title");
               String content = rs.getString("Content");
               String code = rs.getString("Code");
               String pic = rs.getString("Pic");
               String date = rs.getString("Date");
               String catid = rs.getString("Cat_Id");
               String emailid = rs.getString("EmailId");
               
               Post p = new Post(pid, title, content, code, pic, catid, emailid, date);
               list.add(p);
           }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return list;
    }
    
    
    public List<Post> getPostByCatId(String catId)
    {
        List<Post> list = new ArrayList<>();
        
        // fetch all post
        String sql = "select * from Posts where Cat_Id='"+catId+"'";
        try
        {
           pst = conn.prepareStatement(sql);
           rs = pst.executeQuery();
           
           while(rs.next())
           {
               int pid = Integer.parseInt(rs.getString("P_Id"));
               String title = rs.getString("Title");
               String content = rs.getString("Content");
               String code = rs.getString("Code");
               String pic = rs.getString("Pic");
               String date = rs.getString("Date");
               //String catid = rs.getString("Cat_Id");
               String emailid = rs.getString("EmailId");
               
               Post p = new Post(pid, title, content, code, pic, catId, emailid, date);
               list.add(p);
           }
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return list;
    }
    
    public Post getPostByPostId(String p_id)
    {
        Post p = null;
        
        String sql = "select * from Posts where P_Id='"+p_id+"'";
        try
        {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                int pid = rs.getInt("P_Id");
                String title = rs.getString("Title");
                String content = rs.getString("Content");
                String code = rs.getString("Code");
                String img = rs.getString("Pic");
                String catid = rs.getString("Cat_Id");
                String email = rs.getString("EmailId");
                String date = rs.getString("Date");
                
                p = new Post(pid, title, content, code, img, catid, email, date);
                        
                pst.close();
                rs.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return p;
    }
    
    
}
