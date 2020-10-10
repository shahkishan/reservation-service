package com.restaurant.tablebookingservice.dto;

public class AvailableSlotsResponse {
    private String tableName;
    private String availableDate;
    private String availableTime;

    public AvailableSlotsResponse() {
    }

    public AvailableSlotsResponse(String tableName, String availableDate, String availableTime) {
        this.tableName = tableName;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }
}
