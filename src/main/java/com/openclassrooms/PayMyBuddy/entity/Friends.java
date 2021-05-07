package com.openclassrooms.PayMyBuddy.entity;

import java.util.List;

public class Friends {

    private int user;

    private List<Integer> friend;

    private List<String> friendName;

    public Friends(){
    }

    public Friends(int user, List<Integer> friend, List<String> friendName) {
        this.user = user;
        this.friend = friend;
        this.friendName = friendName;
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

    public List<String> getFriendName() {
        return friendName;
    }

    public void setFriendName(List<String> friendName) {
        this.friendName = friendName;
    }
}
