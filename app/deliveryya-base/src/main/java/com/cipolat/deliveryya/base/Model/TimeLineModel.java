package com.cipolat.deliveryya.base.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sebastian on 23/09/17.
 */

public class TimeLineModel implements Parcelable {

    private String detail;
    private String hour;
    private OrderStatus mStatus = OrderStatus.ACTIVE;

    public TimeLineModel() {
    }

    public TimeLineModel(String mMessage, String mDate, OrderStatus mStatus) {
        this.detail = mMessage;
        this.hour = mDate;
        this.mStatus = mStatus;
    }

    void setStatus(OrderStatus mStatus) {
        this.mStatus = mStatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.detail);
        dest.writeString(this.hour);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
    }

    protected TimeLineModel(Parcel in) {
        this.detail = in.readString();
        this.hour = in.readString();
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
    }

    public static final Creator<TimeLineModel> CREATOR = new Creator<TimeLineModel>() {
        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public OrderStatus getmStatus() {
        return mStatus;
    }

    public void setmStatus(OrderStatus mStatus) {
        this.mStatus = mStatus;
    }
}