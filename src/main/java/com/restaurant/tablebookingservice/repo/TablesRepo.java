package com.restaurant.tablebookingservice.repo;

import com.restaurant.tablebookingservice.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablesRepo extends JpaRepository<Table, Integer> {
}
