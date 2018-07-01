package com.cipolat.deliveryya.order.CustomView.StatusLine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.cipolat.deliveryya.base.Model.TimeLineModel;
import com.cipolat.deliveryya.order.R;
import com.github.vipulasri.timelineview.TimelineView;
import java.util.List;

/**
 * Created by sebastian on 23/09/17.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimeLineModel> mFeedList;
    private Context mContext;

    public TimeLineAdapter(List<TimeLineModel> feedList, Context mctx) {
        mFeedList = feedList;
        mContext = mctx;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.status_item, null);
        return new TimeLineViewHolder(view, viewType, mContext);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        TimeLineModel timeLineModel = mFeedList.get(position);
        holder.bindPlaceObj(timeLineModel);

    }

    @Override
    public int getItemCount() {
        return (mFeedList != null ? mFeedList.size() : 0);
    }


}