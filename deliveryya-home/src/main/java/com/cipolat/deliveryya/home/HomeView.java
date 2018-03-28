package com.cipolat.deliveryya.home;

import com.cipolat.deliveryya.base.Model.UserOrder;
import java.util.ArrayList;
/**
 * Created by sebastian on 23/07/17.
 */
public interface HomeView {
    void onOrdersResponseReady(ArrayList<UserOrder> responseData);
    void onOrdersResponseFail();
    void onNetworkError();
    void onNetworkTimeOut();
    void onUnkownError(String error);
}
