/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech_blog.entities;

/**
 *
 * @author Sumit Kumar
 */
public class Post {
    
    private int p_id;
    private String title;
    private String content;
    private String code;
    private String pic;
    private String p_date;
    private String cat_id;
    private String email_id;

    public Post() {
    }

    // for getting Post
    public Post(int p_id, String title, String content, String code, String pic, String cat_id, String email_id, String p_date) {
        this.p_id = p_id;
        this.title = title;
        this.content = content;
        this.code = code;
        this.pic = pic;
        this.cat_id = cat_id;
        this.email_id = email_id;
        this.p_date = p_date;
    }

    
    //save post
    public Post(String title, String content, String code, String pic, String post_date, String cat_id, String email_id) {
        this.title = title;
        this.content = content;
        this.code = code;
        this.pic = pic;
        this.cat_id = cat_id;
        this.email_id = email_id;
        this.p_date = post_date;
    }

    
    //  getters and setters

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getP_date() {
        return p_date;
    }

    public void setP_date(String p_date) {
        this.p_date = p_date;
    }

    
    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
    
}
