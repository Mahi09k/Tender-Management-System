package com.model;

public class Tender {
    private int id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private double price;

    // Constructor with price
    public Tender(int id, String title, String description, String startDate, String endDate, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    // Getters and setters for all fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
