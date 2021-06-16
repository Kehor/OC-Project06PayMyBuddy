package com.openclassrooms.PayMyBuddy.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="transactions")
public class Transactions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="sender_id")
    private Long senderId;

    @Column(name="receiver_id")
    private Long receiverId;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private float amount;

    @Column(name="created_at")
    private Date createdAt;

    public Transactions(){
    }

    public Transactions(Long id, Long senderId, Long receiverId, String description, float amount, Date createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.description = description;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
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
