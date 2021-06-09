package com.openclassrooms.PayMyBuddy.Repository;

import com.openclassrooms.PayMyBuddy.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

    @Query(value = "SELECT * FROM transactions WHERE sender_id = ?1 or receiver_id = ?2 ORDER BY created_at DESC", nativeQuery = true)
    public List<Transactions> findAllBySenderIdOrReceiverIdOrderByCreatedAtDesc(Long senderId, Long receiverId);

    @Query(value = "SELECT * FROM transactions WHERE sender_id = ?1 or receiver_id = ?2 ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    public Transactions findFirstBySenderIdOrReceiverIdOrderByCreatedAtDesc(Long senderId, Long receiverId);
}
