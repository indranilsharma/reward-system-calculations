package com.emc.jcore.reward_system;

import com.emc.jcore.reward_system.entity.RewordTransaction;
import com.emc.jcore.reward_system.model.RewardResponse;
import com.emc.jcore.reward_system.service.RewardService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RewardSystemApplicationTests {
    private final RewardService rewardService = new RewardService();

    @Test
    void testCalculateRewards_singleCustomerMultipleTransactions() {
        List<RewordTransaction> rewordTransactions = List.of(
                new RewordTransaction("C001", 120, LocalDate.of(2025, 3, 15)),
                new RewordTransaction("C001", 75, LocalDate.of(2025, 3, 20))
        );

        List<RewardResponse> responses = rewardService.calculateRewards(rewordTransactions);

        assertEquals(1, responses.size());
        RewardResponse response = responses.get(0);
        assertEquals("C001", response.getCustomerId());
        assertEquals(115, response.getTotalPoints());
        assertEquals(115, response.getMonthlyPoints().get("2025-03"));
    }

    @Test
    void testCalculateRewards_noPointsUnder50() {
        List<RewordTransaction> rewordTransactions = List.of(
                new RewordTransaction("C002", 45, LocalDate.of(2025, 4, 10))
        );

        List<RewardResponse> responses = rewardService.calculateRewards(rewordTransactions);

        assertEquals(1, responses.size());
        RewardResponse response = responses.get(0);
        assertEquals("C002", response.getCustomerId());
        assertEquals(0, response.getTotalPoints());
    }
/**
 *TC added for null value 
 */
    @Test
    void testCalculateRewards_nullDate_shouldThrowException() {
        RewordTransaction tx = new RewordTransaction("C003", 100, null);

        assertThrows(NullPointerException.class, () -> {
            rewardService.calculateRewards(List.of(tx));
        });
    }
}
