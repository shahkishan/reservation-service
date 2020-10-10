package com.restaurant.tablebookingservice.repo;

import com.restaurant.tablebookingservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsRepo extends JpaRepository<Reservation, String> {
    List<Reservation> findAllByReservationDate(LocalDate date);
}
