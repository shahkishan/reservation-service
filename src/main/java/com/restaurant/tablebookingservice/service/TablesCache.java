package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.entity.Slot;
import com.restaurant.tablebookingservice.entity.Table;
import com.restaurant.tablebookingservice.repo.SlotsRepo;
import com.restaurant.tablebookingservice.repo.TablesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TablesCache {
    @Autowired
    private TablesRepo tablesRepo;

    private Map<String, Table> tablesMap;

    @PostConstruct
    @Scheduled(cron = "${tables.cache.reload.cron}")
    public void refresh() {
        List<Table> slots = tablesRepo.findAll();
        if(tablesMap==null) {
            tablesMap = new HashMap<>();
        }

        tablesMap.putAll(slots.stream().collect(Collectors.toMap(Table::getTableName, t -> t)));
    }

    public Table get(String name) {
        if(tablesMap.isEmpty()) {
            refresh();
        }
        return tablesMap.get(name);
    }
}
