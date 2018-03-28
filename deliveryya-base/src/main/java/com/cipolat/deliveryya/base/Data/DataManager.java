package com.cipolat.deliveryya.base.Data;

import android.content.Context;
import com.cipolat.deliveryya.base.Data.FireBase.FireManager;
import com.cipolat.deliveryya.base.Model.Firebase.OrdersCallback;
import com.cipolat.deliveryya.base.Model.Firebase.StoreDataCallback;
import com.cipolat.deliveryya.base.Model.Firebase.UserActivityCallBack;

/**
 * Created by sebastian on 28/09/17.
 */

public class DataManager {
    private FireManager fireManager;
    private Context mCtx;

    public DataManager(Context ctx) {
        this.mCtx = ctx;
        fireManager = new FireManager(mCtx);
    }

    public void getUserActivity(UserActivityCallBack cllb) {
        this.fireManager.getUseractivity(cllb);
    }

    public void getStoreData(String storeID, StoreDataCallback cllb) {
        this.fireManager.getStoreData(storeID, cllb);
    }

    public void getOrdersData(long orderNumber, OrdersCallback cllb) {
        this.fireManager.getOrdersData(orderNumber, cllb);
    }
}
