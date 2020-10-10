package com.restaurant.tablebookingservice.repo;


import com.restaurant.tablebookingservice.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotsRepo extends JpaRepository<Slot, Integer> {
}
