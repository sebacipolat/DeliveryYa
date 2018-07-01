package com.cipolat.deliveryya.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cipolat.deliveryya.base.Model.Pedido;

import java.util.ArrayList;

import com.cipolat.deliveryya.base.R;

/**
 * Created by sebastian on 21/09/17.
 */
public class FoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private ArrayList<Pedido> mListNews;
    private View.OnClickListener mListener;

    public FoodsAdapter(Context context, ArrayList<Pedido> list) {
        this.mContext = context;
        this.mListNews = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pedido_item, parent, false);
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

    public Pedido getItemByPos(int pos) {
        return mListNews.get(pos);
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }


    @Override
    public void onClick(View view) {
        if (mListener != null)
            mListener.onClick(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenFood;
        TextView title;
        TextView cantLbl;
        TextView priceLbl;
        Context innerContext;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            this.innerContext = context;

            imagenFood = itemView.findViewById(R.id.imagenFood);
            title = itemView.findViewById(R.id.title);
            cantLbl = itemView.findViewById(R.id.cantLbl);
            priceLbl = itemView.findViewById(R.id.priceLbl);
        }

        public void bindPlaceObj(Pedido item) {
            title.setText(item.getTitle());
            priceLbl.setText(innerContext.getString(com.cipolat.deliveryya.base.R.string.order_money_value).replace("@", String.valueOf(item.getTotal())));
            RequestOptions options = new RequestOptions();
            options.fitCenter();
            Glide.with(innerContext).load(item.getThumb_url()).apply(options).into(imagenFood);
            cantLbl.setText(innerContext.getString(com.cipolat.deliveryya.base.R.string.pedido_cant).replace("@", String.valueOf(item.getQuantity())));
        }
    }

}







