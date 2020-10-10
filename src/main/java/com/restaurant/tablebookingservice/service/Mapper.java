package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.dto.CreateReservationRequest;
import com.restaurant.tablebookingservice.dto.UpdateReservationRequest;
import com.restaurant.tablebookingservice.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {
    @Autowired
    private TablesCache tablesCache;

    @Autowired
    private SlotsCache slotsCache;

    public Reservation toReservation(CreateReservationRequest createReservationRequest) {
        return new Reservation(UUID.randomUUID().toString(),
                createReservationRequest.getName(),
                createReservationRequest.getContact(),
                createReservationRequest.getReservationDate(),
                slotsCache.get(createReservationRequest.getReservationTime()).getId(),
                tablesCache.get(createReservationRequest.getTableName()).getId());
    }

    public Reservation toReservation(UpdateReservationRequest updateReservationRequest) {
        return new Reservation(updateReservationRequest.getId(),
                updateReservationRequest.getName(),
                updateReservationRequest.getContact(),
                updateReservationRequest.getReservationDate(),
                slotsCache.get(updateReservationRequest.getReservationTime()).getId(),
                tablesCache.get(updateReservationRequest.getTableName()).getId());
    }
}
