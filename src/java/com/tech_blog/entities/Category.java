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
public class Category {
    
    private int c_id;
    private String name;
    private String description;

    public Category() {
    }
    
    

    public Category(int c_id, String name, String description) {
        this.c_id = c_id;
        this.name = name;
        this.description = description;
    }
    
    //getters and setters

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
