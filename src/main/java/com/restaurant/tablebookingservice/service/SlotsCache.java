package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.entity.Slot;
import com.restaurant.tablebookingservice.repo.SlotsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SlotsCache {
    @Autowired
    private SlotsRepo slotsRepo;

    private Map<String, Slot> slotsMap;

    @PostConstruct
    @Scheduled(cron = "${slots.cache.reload.cron}")
    public void refresh() {
        List<Slot> slots = slotsRepo.findAll();
        if(slotsMap==null) {
            slotsMap = new HashMap<>();
        }

        slotsMap.putAll(slots.stream().collect(Collectors.toMap(Slot::getSlotTime, s -> s)));
    }

    public Slot get(String name) {
        if(slotsMap.isEmpty()) {
            refresh();
        }
        return slotsMap.get(name);
    }
}
