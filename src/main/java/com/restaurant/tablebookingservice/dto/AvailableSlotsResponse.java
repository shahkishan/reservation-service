package com.restaurant.tablebookingservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class AvailableSlotsResponse {
    private String tableName;
    private LocalDate availableDate;
    private String availableTime;

    @JsonIgnore
    private int tableId;
    @JsonIgnore
    private int slotId;


    public AvailableSlotsResponse() {
    }

    public AvailableSlotsResponse(String tableName, LocalDate availableDate, String availableTime, int tableId, int slotId) {
        this.tableName = tableName;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
        this.tableId = tableId;
        this.slotId = slotId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LocalDate getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    @JsonIgnore
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    @JsonIgnore
    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
