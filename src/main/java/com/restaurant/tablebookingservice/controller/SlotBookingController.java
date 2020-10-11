package com.restaurant.tablebookingservice.controller;

import com.restaurant.tablebookingservice.dto.*;
import com.restaurant.tablebookingservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class SlotBookingController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/availableSlots")
    public ResponseEntity<List<AvailableSlotsResponse>> getAvailableSlots(@RequestParam("date") String date) {
        return ResponseEntity.ok(reservationService.getAvailableSlots(LocalDate.parse(date)));
    }

    @GetMapping("/reservations/{date}")
    public ResponseEntity<List<ActiveReservationsResponse>> getActiveReservations(@PathVariable("date") String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(reservationService.getActiveBookings(localDate));
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ActiveReservationsResponse> getReservationById(@PathVariable("id") String id) {
        return ResponseEntity.ok(reservationService.getActiveReservation(id));
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@Valid @RequestBody CreateReservationRequest createReservationRequest) {
        ReservationResponse reservation = reservationService.createReservation(createReservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservations")
    public ResponseEntity<ReservationResponse> updateReservation(@Valid @RequestBody UpdateReservationRequest updateReservationRequest) {
        ReservationResponse reservation = reservationService.updateReservation(updateReservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponse> deleteReservation(@PathVariable("id") String id) {
        return ResponseEntity.ok(reservationService.deleteReservation(id));
    }
}
