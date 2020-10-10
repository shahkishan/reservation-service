package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.dto.*;

public interface SlotsService {
    AvailableSlotsResponse getAvailableSlots(String date);
    ActiveReservationsResponse getActiveBookings(String date);
    ReservationResponse createReservation(CreateReservationRequest createReservationRequest);
    ReservationResponse updateReservation(UpdateReservationRequest updateReservationRequest);
    ReservationResponse deleteReservation(String id);
}
