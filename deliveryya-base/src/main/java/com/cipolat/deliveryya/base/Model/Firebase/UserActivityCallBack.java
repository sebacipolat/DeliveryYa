package com.cipolat.deliveryya.base.Model.Firebase;

import com.cipolat.deliveryya.base.Model.UserOrder;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

/**
 * Created by sebastian on 28/09/17.
 */

public interface UserActivityCallBack {
    void onOrderListReady(ArrayList<UserOrder> listOrders);
    void onError(DatabaseError error);
    void onNoConection();
}
