package com.cipolat.deliveryya.base.Model;

import com.cipolat.deliveryya.base.Utils.DateTimeUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by sebastian on 01/10/17.
 */

public class OrderDetails {
    private int estimated_dipatch_time;
    private long order_number;
    private String order_status;
    private String store_id;
    private HashMap<String, Pedido> order_items;
    private HashMap<String, TimeLineModel> track_status;

    public int getEstimated_dipatch_time() {
        return estimated_dipatch_time;
    }

    public void setEstimated_dipatch_time(int estimated_dipatch_time) {
        this.estimated_dipatch_time = estimated_dipatch_time;
    }

    public long getOrder_number() {
        return order_number;
    }

    public void setOrder_number(long order_number) {
        this.order_number = order_number;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public HashMap<String, Pedido> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(HashMap<String, Pedido> order_items) {
        this.order_items = order_items;
    }

    public HashMap<String, TimeLineModel> getTrack_status() {
        return track_status;
    }

    public void setTrack_status(HashMap<String, TimeLineModel> track_status) {
        this.track_status = track_status;
    }

    public ArrayList<Pedido> getPedidosList() {
        Collection<Pedido> values = order_items.values();
        //Creating an ArrayList of values
        ArrayList<Pedido> listOfValues = new ArrayList<Pedido>(values);
        return listOfValues;
    }

    public ArrayList<TimeLineModel> getStatusHistory() {
        Collection<TimeLineModel> values = track_status.values();
        //Creating an ArrayList of values
        ArrayList<TimeLineModel> listOfValues = new ArrayList<TimeLineModel>(values);
        sortData(listOfValues);
        return listOfValues;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    private void sortData(ArrayList<TimeLineModel> listOfValues) {
        Collections.sort(listOfValues, new Comparator<TimeLineModel>() {
            public int compare(TimeLineModel obj1, TimeLineModel obj2) {
                if (DateTimeUtils.timeIsBiggerThan(obj1.getHour(), obj2.getHour(), "hh:mm"))
                    return 0;
                else
                    return -1;
            }
        });
    }

}
