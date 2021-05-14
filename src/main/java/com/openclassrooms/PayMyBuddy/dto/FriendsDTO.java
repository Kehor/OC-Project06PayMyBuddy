package com.openclassrooms.PayMyBuddy.dto;

import java.util.Date;

public class FriendsDTO {

    private  int id;

    private String name;

    private Date lastTransaction;

    public FriendsDTO() {
    }

    public FriendsDTO(int id, String name, Date lastTransaction) {
        this.id = id;
        this.name = name;
        this.lastTransaction = lastTransaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(Date lastTransaction) {
        this.lastTransaction = lastTransaction;
    }
}
