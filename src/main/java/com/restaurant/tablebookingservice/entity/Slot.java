package com.restaurant.tablebookingservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "slots")
public class Slot {
    @Id
    private Integer id;

    @Column(name = "slot_time")
    private String slotTime;

    public Slot() {
    }

    public Slot(int id, String slotTime) {
        this.id = id;
        this.slotTime = slotTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String time) {
        this.slotTime = time;
    }
}
