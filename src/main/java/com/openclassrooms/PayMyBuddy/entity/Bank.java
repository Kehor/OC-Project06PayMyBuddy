package com.openclassrooms.PayMyBuddy.entity;

import java.util.Date;

public class Bank {
    /*
    id int [pk, increment]
  user_id int
  description varchar(255)
  amount decimal(8,2)
  created_at datetime [default: `now()`]

     */

    private int id;

    private int userId;

    private String description;

    private float amount;

    private Date createdAt;

    public Bank(){
    }

    public Bank(int id, int userId, String description, float amount, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
