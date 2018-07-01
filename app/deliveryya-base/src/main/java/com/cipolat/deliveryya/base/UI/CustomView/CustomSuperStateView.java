package com.cipolat.deliveryya.base.UI.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.cipolat.deliveryya.base.R;
import com.cipolat.superstateview.SuperStateView;

/**
 * Created by sebastian on 04/02/18.
 */

public class CustomSuperStateView extends SuperStateView {
    private Context mCtx;

    public CustomSuperStateView(Context context) {
        super(context);
        this.mCtx = context;
    }

    public CustomSuperStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCtx = context;
    }

    public CustomSuperStateView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mCtx = context;
    }

    public void connectionError(){
        setVisibility(View.VISIBLE);
        setImageState(com.cipolat.deliveryya.base.R.drawable.cloud_sad);
        setTitleText(mCtx.getString(R.string.internet_connection_error_lbl));
        setSubTitleText(mCtx.getString(R.string.internet_connection_error_sub_lbl));
        setTitleStyle(R.style.network_error_placeholder_title);
        setSubTitleStyle(R.style.placeholder_error_sub_title);
    }

    public void unknownError(){
        setVisibility(View.VISIBLE);
        setImageState(R.drawable.orange_sad);
        setTitleText(mCtx.getString(R.string.internet_connection_error_lbl));
        setSubTitleText(mCtx.getString(R.string.response_error_lbl));
        setTitleStyle(R.style.network_error_placeholder_title);
        setSubTitleStyle(R.style.placeholder_error_sub_title);
    }
    public void emptyData(){
        setVisibility(View.VISIBLE);
        setImageState(R.drawable.dishes_empty);
        setTitleStyle(R.style.empty_state_placeholder_title);
        setSubTitleStyle(R.style.placeholder_error_sub_title);
        setTitleText(mCtx.getString(R.string.order_state_lbl));
        setSubTitleText(mCtx.getString(R.string.order_state_sub_lbl));
    }
    public void emptyOrder(){
        setVisibility(View.VISIBLE);
        setVisibility(View.VISIBLE);
        setImageState(R.drawable.dishes_empty);
        setTitleText(mCtx.getString(R.string.empty_state_lbl));
        setSubTitleText(mCtx.getString(R.string.empty_state_sub_lbl));
        setTitleStyle(R.style.empty_state_placeholder_title);
        setSubTitleStyle(R.style.placeholder_error_sub_title);
    }
}
