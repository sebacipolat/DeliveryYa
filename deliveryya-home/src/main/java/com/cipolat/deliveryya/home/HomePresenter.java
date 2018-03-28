package com.cipolat.deliveryya.home;

import android.content.Context;
import com.cipolat.deliveryya.base.Data.DataManager;
import com.cipolat.deliveryya.base.Model.Firebase.UserActivityCallBack;
import com.cipolat.deliveryya.base.Model.UserOrder;
import com.cipolat.deliveryya.base.Presenter;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;

/**
 * Created by sebastian on 23/07/17.
 */

public class HomePresenter implements Presenter<HomeView> {
    private Context mCtx;
    private HomeView mPView;
    private DataManager mDataManager;

    public HomePresenter(Context mCtx) {
        this.mCtx = mCtx;
        this.mDataManager = new DataManager(mCtx);
    }

    @Override
    public void setView(HomeView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");
        this.mPView = view;
    }


    public void getUserActivity() {
        this.mDataManager.getUserActivity(new UserActivityCallBack() {
            @Override
            public void onOrderListReady(ArrayList<UserOrder> listOrders) {
                mPView.onOrdersResponseReady(listOrders);
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
