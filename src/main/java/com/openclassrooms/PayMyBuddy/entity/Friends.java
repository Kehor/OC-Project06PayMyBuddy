package com.openclassrooms.PayMyBuddy.Entity;

import javax.persistence.*;

@Entity
@Table(name="friends")
public class Friends {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name="friend_id")
    private Long friendId;

    public Friends(){
    }

    public Friends(Long id, Long userId, Long friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", userId=" + userId +
                ", friendId=" + friendId +
                '}';
    }
}
