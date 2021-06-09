package com.openclassrooms.PayMyBuddy.Dto;


public class TransactionsDTO {

    private Long id;

    private String senderName;

    private String receiverName;

    private String description;

    private float amount;

    public TransactionsDTO() {}

    public TransactionsDTO(Long id, String senderName, String receiverName, String description, float amount) {
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.description = description;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
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
}
