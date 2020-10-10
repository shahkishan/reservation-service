package com.restaurant.tablebookingservice.service.impl;

import com.restaurant.tablebookingservice.dto.*;
import com.restaurant.tablebookingservice.entity.Reservation;
import com.restaurant.tablebookingservice.repo.ReservationsRepo;
import com.restaurant.tablebookingservice.service.Mapper;
import com.restaurant.tablebookingservice.service.SlotsService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SlotServiceImpl implements SlotsService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ReservationsRepo reservationsRepo;

    Logger logger = LoggerFactory.getLogger("ReservationServiceImpl");

    @Override
    public AvailableSlotsResponse getAvailableSlots(String date) {
        return null;
    }

    @Override
    public ActiveReservationsResponse getActiveBookings(String date) {
        return null;
    }

    @Override
    public ReservationResponse createReservation(CreateReservationRequest createReservationRequest) {
        return getReservationResponse(mapper.toReservation(createReservationRequest));
    }

    @Override
    public ReservationResponse updateReservation(UpdateReservationRequest updateReservationRequest) {
        return getReservationResponse(mapper.toReservation(updateReservationRequest));
    }

    @Override
    public ReservationResponse deleteReservation(String id) {
        try {
            reservationsRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error(e.getMessage());
            return new ReservationResponse("0", "NO_RECORD_FOUND");
        }

        return new ReservationResponse("0", "UNRESERVED");
    }

    private ReservationResponse getReservationResponse(Reservation reservation) {
        try {
            reservationsRepo.save(reservation);
        } catch (ConstraintViolationException e) {
            logger.error(e.getMessage());
            return new ReservationResponse("0", "UNAVAILABLE");
        }

        return new ReservationResponse(reservation.getId(), "BOOKED");
    }
}
