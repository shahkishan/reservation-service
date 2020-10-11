package com.restaurant.tablebookingservice.service.impl;

import com.restaurant.tablebookingservice.dto.*;
import com.restaurant.tablebookingservice.dto.exceptions.ReservationException;
import com.restaurant.tablebookingservice.entity.Reservation;
import com.restaurant.tablebookingservice.repo.ReservationsRepo;
import com.restaurant.tablebookingservice.service.Mapper;
import com.restaurant.tablebookingservice.service.ReservationService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ReservationsRepo reservationsRepo;

    Logger logger = LoggerFactory.getLogger("ReservationServiceImpl");

    public List<AvailableSlotsResponse> getAvailableSlots(LocalDate date) {
        List<AvailableSlotsResponse> availableSlotsResponses = mapper
                .toAvailableSlots(reservationsRepo.findAllByReservationDate(date), date);

        if(availableSlotsResponses.isEmpty()) {
            throw new ReservationException("No available slots found for the given date", HttpStatus.OK);
        }

        return availableSlotsResponses;
    }

    @Override
    public List<ActiveReservationsResponse> getActiveBookings(LocalDate date) {
        List<Reservation> activeReservations = reservationsRepo.findAllByReservationDate(date);

        List<ActiveReservationsResponse> activeReservationResponses = activeReservations.stream()
                .map(mapper::toActiveReservation).collect(Collectors.toList());

        if(activeReservationResponses.isEmpty()) {
            throw new ReservationException("No active reservations found for the given date", HttpStatus.OK);
        }

        return activeReservationResponses;
    }

    @Override
    public ActiveReservationsResponse getActiveReservation(String id) {
        Optional<Reservation> reservation = reservationsRepo.findById(id);
        return mapper.toActiveReservation(reservation.orElseThrow(() ->
                new ReservationException("RESERVATION_DOESNT_EXIST", HttpStatus.NOT_FOUND)));
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
            synchronized (reservation.getTable().getTableName()+"_"+reservation.getSlot().getSlotTime()) {
                    reservationsRepo.save(reservation);
            }
        } catch (ConstraintViolationException e) {
            logger.error(e.getMessage());
            return new ReservationResponse("0", "UNAVAILABLE");
        }

        return new ReservationResponse(reservation.getId(), "BOOKED");
    }
}
