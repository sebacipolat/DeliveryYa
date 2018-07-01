package com.cipolat.deliveryya.base.Model.Firebase;

import com.cipolat.deliveryya.base.Model.OrderDetails;
import com.google.firebase.database.DatabaseError;

/**
 * Created by sebastian on 01/10/17.
 */

public interface OrdersCallback {
    void onOrdersDataReady(OrderDetails store);
    void onError(DatabaseError error);
    void onNoConection();

}
