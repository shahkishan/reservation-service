package com.restaurant.tablebookingservice.dto;

import java.time.LocalDate;
import java.util.Date;

public class ActiveReservationsResponse {
    private String reservationId;
    private String tableName;
    private LocalDate reservationDate;
    private String reservationTime;
    private String name;
    private String contact;

    public ActiveReservationsResponse() {
    }

    public ActiveReservationsResponse(String reservationId,
                                      String tableName,
                                      LocalDate reservationDate,
                                      String reservationTime,
                                      String name,
                                      String contact) {
        this.reservationId = reservationId;
        this.tableName = tableName;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.name = name;
        this.contact = contact;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
