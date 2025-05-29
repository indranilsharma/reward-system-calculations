package com.emc.jcore.reward_system.service;

import com.emc.jcore.reward_system.entity.RewordTransaction;
import com.emc.jcore.reward_system.model.RewardResponse;
import com.emc.jcore.reward_system.repository.TransactionRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RewardService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<RewardResponse> calculateRewards(List<RewordTransaction> rewordTransactions) throws InvalidRequestStateException {
        if (rewordTransactions == null || rewordTransactions.isEmpty()) {
            throw new InvalidRequestStateException("Transaction list is empty or null.");
        }

        Map<String, Map<String, Integer>> customerMonthlyPoints = new LinkedHashMap<>();
        Map<String, Integer> customerTotalPoints = new LinkedHashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        List<RewardResponse> responses = new ArrayList<>();
        for (RewordTransaction tx : rewordTransactions) {
            String customerId = tx.getCustomerId();
            String month = tx.getDate().format(formatter);
            int points = calculatePoints(tx.getAmount());

            customerMonthlyPoints
                    .computeIfAbsent(customerId, k -> new HashMap<>())
                    .merge(month, points, Integer::sum);

            customerTotalPoints.merge(customerId, points, Integer::sum);
        }


        for (String customerId : customerMonthlyPoints.keySet()) {
            RewardResponse response = new RewardResponse();
            response.setCustomerId( customerId);
            response.setMonthlyPoints(customerMonthlyPoints.get(customerId));
            response.setTotalPoints(customerTotalPoints.get(customerId));
            responses.add(response);
        }

        return responses;
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points += 50;
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }
        return points;
    }
}
