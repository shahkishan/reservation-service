package com.restaurant.tablebookingservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class UpdateReservationRequest {
    @NotBlank(message = "Reservation Id cannot be empty")
    private String id;
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Contact cannot be empty")
    private String contact;
    @NotNull(message = "Date cannot be empty")
    private LocalDate reservationDate;
    @NotBlank(message = "Time cannot be empty")
    private String reservationTime;
    @NotBlank(message = "Table Name cannot be empty")
    private String tableName;

    public UpdateReservationRequest() {
    }

    public UpdateReservationRequest(String id, String name, String contact, LocalDate reservationDate, String reservationTime, String tableName) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
