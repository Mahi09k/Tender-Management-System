package com.model;

import java.math.BigDecimal;

public class Bid {
    private int bidId;
    private int tenderId;
    private int userId;
    private BigDecimal bidAmount;
    private String bidStatus;

    public Bid(int bidId, int tenderId, int userId, BigDecimal bidAmount, String bidStatus) {
        this.bidId = bidId;
        this.tenderId = tenderId;
        this.userId = userId;
        this.bidAmount = bidAmount;
        this.bidStatus = bidStatus;
    }

    // Getters and Setters
    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }
}
