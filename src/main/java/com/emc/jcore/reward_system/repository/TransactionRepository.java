package com.emc.jcore.reward_system.repository;

import com.emc.jcore.reward_system.entity.RewordTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 *JPA reposistory added
 */
public interface TransactionRepository extends JpaRepository<RewordTransaction, Long> {
    List<RewordTransaction> findByCustomerId(String customerId);
}
