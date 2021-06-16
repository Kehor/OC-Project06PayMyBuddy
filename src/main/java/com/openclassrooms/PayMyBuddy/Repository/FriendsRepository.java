package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {
    @Query(value = "SELECT * FROM friends WHERE user_id = ?1 or friend_id = ?1", nativeQuery = true)
    public List<Friends> findAllFriendIdByUserIdOrFriendId(Long userId);
}
