package com.cipolat.deliveryya.order.CustomView.StatusLine;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.cipolat.deliveryya.base.Model.OrderStatus;
import com.cipolat.deliveryya.base.Model.TimeLineModel;
import com.cipolat.deliveryya.order.R;
import com.github.vipulasri.timelineview.TimelineView;

/**
 * Created by sebastian on 23/09/17.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {

    private TextView mDate, mMessage;
    private TimelineView mTimelineView;
    private Context mContext;

    public TimeLineViewHolder(View itemView, int viewType, Context ctx) {
        super(itemView);
        mDate = itemView.findViewById(R.id.text_timeline_date);
        mMessage = itemView.findViewById(R.id.text_timeline_title);
        mTimelineView = itemView.findViewById(R.id.time_marker);

        mTimelineView.initLine(viewType);
        mContext = ctx;
    }

    public void bindPlaceObj(TimeLineModel timeLineModel) {
        if (timeLineModel.getmStatus() == OrderStatus.ACTIVE) {
            mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker));
        } else if (timeLineModel.getmStatus() == OrderStatus.CURRENT) {
            mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_marker_current));
        }
        if (!timeLineModel.getHour().isEmpty()) {
            mDate.setVisibility(View.VISIBLE);
            mDate.setText(timeLineModel.getHour());
        } else
            mDate.setVisibility(View.GONE);

        mMessage.setText(timeLineModel.getDetail());
    }

}