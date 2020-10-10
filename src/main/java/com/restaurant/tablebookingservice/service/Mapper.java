package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.dto.ActiveReservationsResponse;
import com.restaurant.tablebookingservice.dto.AvailableSlotsResponse;
import com.restaurant.tablebookingservice.dto.CreateReservationRequest;
import com.restaurant.tablebookingservice.dto.UpdateReservationRequest;
import com.restaurant.tablebookingservice.entity.Reservation;
import com.restaurant.tablebookingservice.entity.Slot;
import com.restaurant.tablebookingservice.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
                slotsCache.get(createReservationRequest.getReservationTime()),
                tablesCache.get(createReservationRequest.getTableName()));
    }

    public Reservation toReservation(UpdateReservationRequest updateReservationRequest) {
        return new Reservation(updateReservationRequest.getId(),
                updateReservationRequest.getName(),
                updateReservationRequest.getContact(),
                updateReservationRequest.getReservationDate(),
                slotsCache.get(updateReservationRequest.getReservationTime()),
                tablesCache.get(updateReservationRequest.getTableName()));
    }

    public ActiveReservationsResponse toActiveReservation(Reservation reservation) {
        return new ActiveReservationsResponse(reservation.getId(),
                reservation.getTable().getTableName(),
                reservation.getReservationDate(),
                reservation.getSlot().getSlotTime(),
                reservation.getName(),
                reservation.getContact());

    }

    public List<AvailableSlotsResponse> toAvailableSlots(List<Reservation> reservations, LocalDate date) {
        List<Slot> slots = slotsCache.getSlots();
        List<Table> tables = tablesCache.getTables();

        Set<AvailableSlotsResponse> allSlots = tables.stream()
                .flatMap(table -> slots.stream()
                        .map(slot -> new AvailableSlotsResponse(table.getTableName(),
                                date,
                                slot.getSlotTime(),
                                table.getId(),
                                slot.getId()))
                ).collect(Collectors.toSet());

        Set<AvailableSlotsResponse> existingSlots = reservations.stream()
                .map(reservation ->
                        new AvailableSlotsResponse(reservation.getTable().getTableName(),
                                reservation.getReservationDate(),
                                reservation.getSlot().getSlotTime(),
                                reservation.getTable().getId(),
                                reservation.getSlot().getId())
                ).collect(Collectors.toSet());

        allSlots.removeIf(slot -> existingSlots.stream().anyMatch(existingSlot ->
                slot.getTableId() == existingSlot.getTableId() && slot.getSlotId() == existingSlot.getSlotId()));

        return allSlots.stream()
                .sorted(Comparator.comparing(AvailableSlotsResponse::getTableId)
                        .thenComparing(AvailableSlotsResponse::getSlotId))
                .collect(Collectors.toList());
    }
}
