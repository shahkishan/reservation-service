package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<AvailableSlotsResponse> getAvailableSlots(LocalDate date);
    List<ActiveReservationsResponse> getActiveBookings(LocalDate date);
    ActiveReservationsResponse getActiveReservation(String id);
    ReservationResponse createReservation(CreateReservationRequest createReservationRequest);
    ReservationResponse updateReservation(UpdateReservationRequest updateReservationRequest);
    ReservationResponse deleteReservation(String id);
}
