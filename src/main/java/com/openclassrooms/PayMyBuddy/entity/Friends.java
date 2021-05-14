package com.openclassrooms.PayMyBuddy.entity;

import java.util.List;

public class Friends {

    private int user;

    private List<Integer> friend;

    public Friends(){
    }

    public Friends(int user, List<Integer> friend) {
        this.user = user;
        this.friend = friend;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public List<Integer> getFriend() {
        return friend;
    }

    public void setFriend(List<Integer> friend) {
        this.friend = friend;
    }

}
