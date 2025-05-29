package com.emc.jcore.reward_system.controller;

import com.emc.jcore.reward_system.entity.RewordTransaction;
import com.emc.jcore.reward_system.model.RewardResponse;
import com.emc.jcore.reward_system.service.RewardService;

import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/api/rewards")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    @PostMapping
    public List<RewardResponse> calculateRewards(@RequestBody List<RewordTransaction> rewordTransactions) throws InvalidRequestStateException {
        return rewardService.calculateRewards(rewordTransactions);
    }
}
