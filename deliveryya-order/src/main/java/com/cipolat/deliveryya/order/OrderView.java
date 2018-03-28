package com.cipolat.deliveryya.order;

import com.cipolat.deliveryya.base.Model.OrderDetails;
import com.cipolat.deliveryya.base.Model.Pedido;
import com.cipolat.deliveryya.base.Model.Store;
import com.cipolat.deliveryya.base.Model.TimeLineModel;

import java.util.ArrayList;

/**
 * Created by sebastian on 23/07/17.
 */
public interface OrderView {
    void onStoreDataReady(Store storeDetails);

    void onOrderDetails(OrderDetails details);

    void onInvalidOrder();

    void onNetworkError();

    void onNetworkTimeOut();

    void onUnkownError(String error);
}
