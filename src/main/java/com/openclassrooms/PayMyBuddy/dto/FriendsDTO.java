package com.openclassrooms.PayMyBuddy.Dto;

import java.util.Date;

public class FriendsDTO {

    private Long id;

    private String name;

    private Date lastTransaction;

    public FriendsDTO() {
    }

    public FriendsDTO(Long id, String name, Date lastTransaction) {
        this.id = id;
        this.name = name;
        this.lastTransaction = lastTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
