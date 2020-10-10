package com.restaurant.tablebookingservice.service;

import com.restaurant.tablebookingservice.entity.Table;
import com.restaurant.tablebookingservice.repo.TablesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TablesCache {
    @Autowired
    private TablesRepo tablesRepo;

    private Map<String, Table> tablesByName;
//    private Map<Integer, Table> tablesById;

    @PostConstruct
    @Scheduled(cron = "${tables.cache.reload.cron}")
    public void refresh() {
        List<Table> slots = tablesRepo.findAll();
        if(tablesByName == null) {
            tablesByName = new HashMap<>();
        }
//
//        if(tablesById == null) {
//            tablesByName = new HashMap<>();
//        }
        tablesByName.clear();
        tablesByName.putAll(slots.stream().collect(Collectors.toMap(Table::getTableName, t -> t)));
//        tablesById.clear();
//        tablesById.putAll(slots.stream().collect(Collectors.toMap(Table::getId, t -> t)));
    }

    public Table get(String name) {
        if(tablesByName.isEmpty()) {
            refresh();
        }
        return tablesByName.get(name);
    }

    public List<Table> getTables() {
        if(tablesByName.isEmpty()) {
            refresh();
        }

        return new ArrayList<>(tablesByName.values());
    }
}
