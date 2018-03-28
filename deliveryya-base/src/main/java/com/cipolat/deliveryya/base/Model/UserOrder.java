package com.cipolat.deliveryya.base.Model;


import java.io.Serializable;

/**
 * Created by sebastian on 24/09/17.
 */

public class UserOrder implements Serializable{

    private int id;
    private long order_number;
    private int estimated_dipatch_time;
    private int orders_count;
    private int total;
    private String last_status;
    private Store store;

    public UserOrder() {

    }

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }


    public int getEstimated_dipatch_time() {
        return estimated_dipatch_time;
    }

    public void setEstimated_dipatch_time(int estimated_dipatch_time) {
        this.estimated_dipatch_time = estimated_dipatch_time;
    }

    public int getOrders_count() {
        return orders_count;
    }

    public void setOrders_count(int orders_count) {
        this.orders_count = orders_count;
    }

    public String getLast_status() {
        return last_status;
    }

    public void setLast_status(String last_status) {
        this.last_status = last_status;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

