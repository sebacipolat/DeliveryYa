package com.cipolat.deliveryya.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cipolat.deliveryya.R;
import com.cipolat.deliveryya.base.Model.UserOrder;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by sebastian on 21/09/17.
 */
public class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayList<UserOrder> mListNews;
    private View.OnClickListener mListener;

    public OrderAdapter(Context context, ArrayList<UserOrder> list) {
        this.mContext = context;
        this.mListNews = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v, mContext);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vhNews = (ViewHolder) holder;
        vhNews.bindPlaceObj(mListNews.get(position));

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public UserOrder getItemByPos(int pos) {
        return mListNews.get(pos);
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }


    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public void setOnClickListener(View.OnClickListener listen) {
        this.mListener = listen;
    }

    @Override
    public void onClick(View view) {
        if (mListener != null)
            mListener.onClick(view);
    }

    //Para Articulos Comunes
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView backgr;
        CircleImageView logoImage;
        TextView timeLbl;
        TextView titleLbl;
        TextView orderCountLbl;
        TextView statusLbl;
        TextView priceLbl;


        Context innerContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.innerContext = context;
            backgr=itemView.findViewById(R.id.backgr);
            logoImage=itemView.findViewById(R.id.logoImage);
            timeLbl=itemView.findViewById(R.id.timeLbl);
            titleLbl=itemView.findViewById(R.id.titleLbl);
            orderCountLbl=itemView.findViewById(R.id.orderCountLbl);
            statusLbl=itemView.findViewById(R.id.statusLbl);
            priceLbl=itemView.findViewById(R.id.priceLbl);

        }

        public void bindPlaceObj(UserOrder item) {
            if (item.getStore() != null) {
                titleLbl.setText(item.getStore().getTitle());
                RequestOptions options = new RequestOptions();
                options.fitCenter();
                Glide.with(innerContext).load(item.getStore().getLogo_url()).apply(options).into(logoImage);
                Glide.with(innerContext).load(item.getStore().getBackgr()).apply(options).into(backgr);
            }
            timeLbl.setText(innerContext.getString(R.string.order_time).replace("z", String.valueOf(item.getEstimated_dipatch_time())));

            statusLbl.setText(item.getLast_status());
            priceLbl.setText(innerContext.getString(R.string.order_money_value).replace("@", String.valueOf(item.getTotal())));

            if (item.getOrders_count() > 1)
                orderCountLbl.setText(String.valueOf(item.getOrders_count()) + " " + innerContext.getString(R.string.order_count_one_lbl));
            else
                orderCountLbl.setText(String.valueOf(item.getOrders_count()) + " " + innerContext.getString(R.string.order_count_many_lbl));
        }
    }


}







