package com.restaurant.tablebookingservice.controller;

import com.restaurant.tablebookingservice.dto.AvailableSlotsResponse;
import com.restaurant.tablebookingservice.dto.CreateReservationRequest;
import com.restaurant.tablebookingservice.dto.ReservationResponse;
import com.restaurant.tablebookingservice.dto.UpdateReservationRequest;
import com.restaurant.tablebookingservice.service.SlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SlotBookingController {


    @Autowired
    private SlotsService slotsService;

    @GetMapping("/availableSlots")
    public ResponseEntity<AvailableSlotsResponse> getAvailableSlots(@RequestParam("date") String date) {
        return null;
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody CreateReservationRequest createReservationRequest) {
        ReservationResponse reservation = slotsService.createReservation(createReservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @PutMapping("/reservations")
    public ResponseEntity<ReservationResponse> updateReservation(@RequestBody UpdateReservationRequest updateReservationRequest) {
        ReservationResponse reservation = slotsService.updateReservation(updateReservationRequest);
        return ResponseEntity.ok(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<ReservationResponse> deleteReservation(@PathVariable("id") String id) {
        return ResponseEntity.ok(slotsService.deleteReservation(id));
    }
}
