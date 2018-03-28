package com.cipolat.deliveryya.order;

import android.content.Context;

import com.cipolat.deliveryya.base.Data.DataManager;
import com.cipolat.deliveryya.base.Model.Firebase.OrdersCallback;
import com.cipolat.deliveryya.base.Model.Firebase.StoreDataCallback;
import com.cipolat.deliveryya.base.Model.OrderDetails;
import com.cipolat.deliveryya.base.Model.Store;
import com.cipolat.deliveryya.base.Presenter;
import com.google.firebase.database.DatabaseError;

/**
 * Created by sebastian on 23/07/17.
 */

public class FoodPresenter implements Presenter<OrderView> {
    private Context mCtx;
    private OrderView mPView;
    private DataManager mDataManager;

    public FoodPresenter(Context mCtx) {
        this.mCtx = mCtx;
        this.mDataManager = new DataManager(mCtx);
    }

    @Override
    public void setView(OrderView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");
        this.mPView = view;
    }


    public void getStoreData(String idstore) {
        this.mDataManager.getStoreData(idstore, new StoreDataCallback() {
            @Override
            public void onStoreDataReady(Store storeData) {
                if (storeData != null)
                    mPView.onStoreDataReady(storeData);
                else
                    mPView.onUnkownError(null);
            }

            @Override
            public void onError(DatabaseError error) {
                mPView.onUnkownError(null);

            }

            @Override
            public void onNoConection() {
                mPView.onNetworkError();
            }

        });
    }

    public void getOrdersData(long orderNumber) {
        this.mDataManager.getOrdersData(orderNumber, new OrdersCallback() {
            @Override
            public void onOrdersDataReady(OrderDetails details) {
                if (details != null) {
                    getStoreData(details.getStore_id());
                    mPView.onOrderDetails(details);
                } else
                    mPView.onInvalidOrder();
            }

            @Override
            public void onError(DatabaseError error) {
                mPView.onUnkownError(null);
            }

            @Override
            public void onNoConection() {
                mPView.onNetworkError();
            }
        });
    }

    @Override
    public void detachView() {
        mPView = null;
    }
}
