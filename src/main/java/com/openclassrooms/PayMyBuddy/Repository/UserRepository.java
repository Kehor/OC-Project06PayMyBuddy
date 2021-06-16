package com.openclassrooms.PayMyBuddy.repository;

import com.openclassrooms.PayMyBuddy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findOneByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
    public List<User> findAllUserByEmailLike(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user u set name =?2, password =?3, iban =?4 where u.id = ?1", nativeQuery = true)
    public void updateUser(Long userId, String name, String password, String iban);
}
