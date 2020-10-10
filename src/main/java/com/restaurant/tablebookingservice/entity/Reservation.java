package com.restaurant.tablebookingservice.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "reservations")
public class Reservation {
    @Id
    private String id;
    private String name;
    private String contact;
    @Column(name = "reservation_date")
    private LocalDate reservationDate;
//    @Column(name = "slot_id")
//    private Integer slotId;
//    @Column(name = "table_id")
//    private Integer tableId;

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private Slot slot;

    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    public Reservation() {
    }

    public Reservation(String id, String name, String contact, LocalDate reservationDate, Slot slot, Table table) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.reservationDate = reservationDate;
        this.slot = slot;
        this.table = table;
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

//    public Integer getSlotId() {
//        return slotId;
//    }
//
//    public void setSlotId(Integer slotId) {
//        this.slotId = slotId;
//    }
//
//    public Integer getTableId() {
//        return tableId;
//    }
//
//    public void setTableId(Integer tableId) {
//        this.tableId = tableId;
//    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}

