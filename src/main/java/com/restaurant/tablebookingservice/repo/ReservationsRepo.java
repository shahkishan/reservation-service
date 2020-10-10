package com.restaurant.tablebookingservice.repo;

import com.restaurant.tablebookingservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepo extends JpaRepository<Reservation, String> {
}
