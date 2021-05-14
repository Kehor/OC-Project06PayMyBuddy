package com.openclassrooms.PayMyBuddy.config;

public class DBConstants {

    public static final String SAVE_USER = "insert into user(name, email, pass, iban, balance) values(?,?,?,?,?)";
    public static final String GET_USER_INFO= "select * from user where id = ?";
    public static final String GET_USER_BY_EMAIL= "select * from user where email = ?";
    public static final String ADD_FRIENDS = "insert into friends(user_id, friend_id) values(?,?)";
    public static final String GET_FRIENDS_LIST= "select friend_id from friends where user_id = ?";
    public static final String SAVE_TRANSACTIONS= "insert into transactions(sender_id, receiver_id, description, amount) values(?,?,?,?)";
    public static final String GET_TRANSACTIONS= "select * from transactions where (sender_id = ? or receiver_id = ?) order by created_at desc";
    public static final String GET_TRANSACTIONSWithUser= "select * from transactions where (sender_id = ? and receiver_id = ?) order by created_at desc";
    public static final String SAVE_BANK_TRANSACTIONS= "insert into bank(user_id, description, amount) values(?,?,?)";
    public static final String GET_BANK_TRANSACTIONS= "select * from bank where user_id = ? order by created_at desc";

}
