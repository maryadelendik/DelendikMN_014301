package com.example.cp.production_orders;

import java.io.Serializable;

public class SearchProductionOrders  implements Serializable{
    private String search;
    private Boolean is_open;
    private String date;
    private String date_to;
    private String date_from;

    public SearchProductionOrders(String search, Boolean is_open, String date, String date_to, String date_from) {
        this.search = search;
        this.is_open = is_open;
        this.date = date;
        this.date_to = date_to;
        this.date_from = date_from;
    }

    public SearchProductionOrders() {

    }

    public String getDate_to() {
        return date_to;
    }

    public void setDate_to(String date_to) {
        this.date_to = date_to;
    }

    public String getDate_from() {
        return date_from;
    }

    public void setDate_from(String date_from) {
        this.date_from = date_from;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Boolean getIs_open() {
        return is_open;
    }

    public void setIs_open(Boolean is_open) {
        this.is_open = is_open;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
