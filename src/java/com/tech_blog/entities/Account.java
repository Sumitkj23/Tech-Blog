/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.entities;

import java.sql.*;

/**
 *
 * @author Sumit Kumar
 */
public class Account {
    
    private int id;
    private String username;
    private String email;
    private String password;
    private String gender;
    private String about;
    private String reg_date;
    private String profile;

    public Account() {
    }

    public Account(String username, String email, String password, String gender, String about, String reg_date) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.about = about;
        this.reg_date = reg_date;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    
    

}