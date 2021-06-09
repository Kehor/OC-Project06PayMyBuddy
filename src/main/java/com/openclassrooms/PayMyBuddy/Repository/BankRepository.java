package com.openclassrooms.PayMyBuddy.Repository;

import com.openclassrooms.PayMyBuddy.Entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
}
