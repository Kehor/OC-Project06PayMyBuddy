package com.openclassrooms.PayMyBuddy.Repository;

import com.openclassrooms.PayMyBuddy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findOneByEmail(String email);

    public User findByName(String name);

    @Query("SELECT u FROM User u WHERE u.email LIKE %?1%")
    public List<User> findAllUserByEmailLike(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user u set email =?2, password =?3, iban =?4 where u.id = ?1", nativeQuery = true)
    public void updateUser(Long userId, String email, String password, String iban);
}
