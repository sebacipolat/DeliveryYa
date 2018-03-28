package com.cipolat.deliveryya.base.Data.FireBase;

import android.content.Context;

import com.cipolat.deliveryya.base.Model.Firebase.Data;
import com.cipolat.deliveryya.base.Model.Firebase.OrdersCallback;
import com.cipolat.deliveryya.base.Model.Firebase.StoreDataCallback;
import com.cipolat.deliveryya.base.Model.Firebase.UserActivityCallBack;
import com.cipolat.deliveryya.base.Model.OrderDetails;
import com.cipolat.deliveryya.base.Model.Store;
import com.cipolat.deliveryya.base.Model.UserOrder;
import com.cipolat.deliveryya.base.Utils.NetworkUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/*
 * Created by sebastian on 28/09/17.
 */
public class FireManager {
    private Context mCtx;

    public FireManager(Context ctx) {
        this.mCtx = ctx;
    }

    //Retrieve UserActivity Orders
    public void getUseractivity(final UserActivityCallBack mListener) {
        if (NetworkUtils.isNetworkConnected(mCtx)) {
            DatabaseReference pedidosRef = FirebaseDatabase.getInstance().getReference(Data.USER_ACTIVITY_ROOT);
            pedidosRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArrayList<UserOrder> listActivityUser = new ArrayList<>();
                    for (DataSnapshot fireSnapshot : dataSnapshot.getChildren()) {
                        UserOrder order = fireSnapshot.getValue(UserOrder.class);
                        listActivityUser.add(order);
                    }
                    if (mListener != null)
                        mListener.onOrderListReady(listActivityUser);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    if (mListener != null)
                        mListener.onError(error);
                }
            });
        } else {
            if (mListener != null)
                mListener.onNoConection();
        }
    }

    //Get Store details
    public void getStoreData(String storeID, final StoreDataCallback strCllbk) {
        if (NetworkUtils.isNetworkConnected(mCtx)) {
            DatabaseReference pedidosRef = FirebaseDatabase.getInstance().getReference(Data.STORE_ROOT + "/" + storeID);
            pedidosRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Store storeData = dataSnapshot.getValue(Store.class);
                    if (strCllbk != null)
                        strCllbk.onStoreDataReady(storeData);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    if (strCllbk != null)
                        strCllbk.onError(error);
                }
            });
        } else {
            if (strCllbk != null)
                strCllbk.onNoConection();
        }
    }

    //Get order details
    public void getOrdersData(long orderNumber, final OrdersCallback strCllbk) {
        if (NetworkUtils.isNetworkConnected(mCtx)) {
            DatabaseReference pedidosRef = FirebaseDatabase.getInstance().getReference(Data.ORDERS_ROOT + "/" + orderNumber);
            pedidosRef.orderByChild(Data.ORDER_ITEM_ID);
            pedidosRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    OrderDetails orderData = dataSnapshot.getValue(OrderDetails.class);
                    if (strCllbk != null)
                        strCllbk.onOrdersDataReady(orderData);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    if (strCllbk != null)
                        strCllbk.onError(error);
                }
            });
        } else {
            if (strCllbk != null)
                strCllbk.onNoConection();
        }
    }
}
