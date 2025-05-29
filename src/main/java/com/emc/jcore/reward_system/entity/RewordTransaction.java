package com.emc.jcore.reward_system.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
/**
 * Entity class which having ID autogenearated feild  
 */
@Entity
@Table(name = "reword_transactions")
public class RewordTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
    private double amount;
    private LocalDate date;


    public RewordTransaction() {
    }

    public RewordTransaction(String customerId, double amount, LocalDate date) {
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
