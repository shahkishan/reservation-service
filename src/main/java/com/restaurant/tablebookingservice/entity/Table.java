package com.restaurant.tablebookingservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tables")
public class Table {
    @Id
    private int id;
    @Column(name = "tbl_name")
    private String tableName;

    public Table() {
    }

    public Table(int id, String tableName) {
        this.id = id;
        this.tableName = tableName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
