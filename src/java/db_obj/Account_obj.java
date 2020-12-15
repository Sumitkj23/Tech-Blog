/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_obj;

import com.tech_blog.entities.Account;
import com.tech_blog.helpers.javaConnect;
import java.sql.*;

/**
 *
 * @author Sumit Kumar
 */
public class Account_obj {

    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public Account_obj() {
        conn = javaConnect.connectDb();
    }

    // insert account in database or create user...
    public boolean addUser(Account user) {
        boolean b = false;
        /*try {
            ResultSet rs;
            String s = "select Id from Account where Username=?";
            pst = conn.prepareStatement(s);
            pst.setString(1, user.getUsername());
            rs = pst.executeQuery();
            if (rs.next()) {
                return b;
            } else {
                conn.close();
                pst.close();*/
        try{
                conn = javaConnect.connectDb();
                String sql = "insert into Account(Username, Email, Password, Gender, About, Reg_date) values(?,?,?,?,?,?)";
                pst = conn.prepareStatement(sql);
                pst.setString(1, user.getUsername());
                pst.setString(2, user.getEmail());
                pst.setString(3, user.getPassword());
                pst.setString(4, user.getGender());
                pst.setString(5, user.getAbout());
                pst.setString(6, user.getReg_date());
                pst.executeUpdate();
                b = true;
            }
         catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                pst.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return b;
    }
    
    //get user or login function
    
    public Account getUserInfo(String email, String password)
    {
        Account acc = null;
        
        
        try
        {
            conn = javaConnect.connectDb();
            String sql = "select * from Account where Email=? and Password=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if(rs.next())
            {
                acc = new Account();
                
                // set data from db to acc
                acc.setId(rs.getInt("Id"));
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setEmail(email);
                acc.setGender(rs.getString("Gender"));
                acc.setAbout(rs.getString("About"));
                acc.setProfile(rs.getString("Profile"));
                acc.setReg_date(rs.getString("Reg_date"));
                
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                pst.close();
                rs.close();
            } catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
        
        return acc;
    }
    
    public boolean updateUser(Account acc)
    {
        boolean b = false;
        try
        {
            conn = javaConnect.connectDb();
            String sql = "update Account set Email=?, Password=?, About=?, Profile=? where Id='"+acc.getId()+"'";
            pst = conn.prepareStatement(sql);
            pst.setString(1, acc.getEmail());
            pst.setString(2, acc.getPassword());
            pst.setString(3, acc.getAbout());
            pst.setString(4, acc.getProfile());
            
            pst.executeUpdate();
            
            b = true;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try 
            {
                pst.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return b;
    }
    
    
    public Account getUserByEmailId(String emailid)
    {
        Account acc = null;
        String sql = "select * from Account where Email='"+emailid+"' ";
        try
        {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next())
            {
                acc = new Account();
                
                acc.setId(rs.getInt("Id"));
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setEmail(emailid);
                acc.setGender(rs.getString("Gender"));
                acc.setAbout(rs.getString("About"));
                acc.setProfile(rs.getString("Profile"));
                acc.setReg_date(rs.getString("Reg_date"));
                
                
                pst.close();
                rs.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return acc;
    }
    
    
}
