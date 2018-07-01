package com.cipolat.deliveryya.order;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cipolat.deliveryya.base.Model.OrderDetails;
import com.cipolat.deliveryya.base.Model.Pedido;
import com.cipolat.deliveryya.base.Model.Store;
import com.cipolat.deliveryya.base.Model.TimeLineModel;
import com.cipolat.deliveryya.base.Model.UserOrder;
import com.cipolat.deliveryya.base.UI.CustomView.CustomSuperStateView;
import com.cipolat.deliveryya.base.UI.CustomView.Loader.AraryImageLoader;
import com.cipolat.deliveryya.order.CustomView.StatusLine.TimeLineAdapter;
import com.google.android.instantapps.InstantApps;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PedidoActivity extends AppCompatActivity implements OrderView {

    Toolbar toolbar;
    RecyclerView listFood;
    RecyclerView statusLine;
    ImageView backgr;

    View orderLay;
    View orderIndicator;
    NestedScrollView nestScroll;
    AraryImageLoader loaderAnim;
    TextView pedidoLbl;
    TextView pedidoStatusLbl;
    CustomSuperStateView stateView;
    ViewStub installStub;
    LinearLayout footer;

    CircleImageView logo;
    TextView title;
    TextView statusLbl;
    RatingBar ratingBar;

    TextView orderNumValueLbl;
    TextView timeLbl;
    RelativeLayout lay;

    private FoodsAdapter mAdapter;
    private TimeLineAdapter mTimeLineAdapter;
    private FoodPresenter mFoodPresenter;

    public static String ORDER_OBJ = "ORDER_OBJ";
    public static String URL_ID_PARAMS = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        toolbar = findViewById(R.id.toolbar);
        listFood = findViewById(R.id.orderList);
        statusLine = findViewById(R.id.statusLine);
        orderLay = findViewById(R.id.orderHeader);
        orderIndicator = findViewById(R.id.orderIndicator);
        backgr = findViewById(R.id.backgr);
        loaderAnim = findViewById(R.id.loaderAnim);
        pedidoLbl = findViewById(R.id.pedidoLbl);
        pedidoStatusLbl = findViewById(R.id.pedidoStatusLbl);
        installStub = findViewById(R.id.installStub);
        footer = findViewById(R.id.footer);
        logo = orderLay.findViewById(R.id.logo);
        title = orderLay.findViewById(R.id.title);
        statusLbl = orderLay.findViewById(R.id.statusLbl);
        ratingBar = orderLay.findViewById(R.id.ratingBar);

        orderNumValueLbl = orderIndicator.findViewById(R.id.orderNumValue);
        timeLbl = orderIndicator.findViewById(R.id.time);
        lay = orderIndicator.findViewById(R.id.lay);

        //No mostrar toolbar en instantapps
        if (!InstantApps.isInstantApp(this)) {
            toolbar.setTitle("");
            toolbar.setNavigationIcon(ContextCompat.getDrawable(this, com.cipolat.deliveryya.base.R.drawable.ic_arrow_back_black_24dp));
            setSupportActionBar(toolbar);
        }
        mFoodPresenter = new FoodPresenter(this);
        mFoodPresenter.setView(this);
        Intent fromMainActInt = getIntent();
        if (fromMainActInt != null)
            loadDataFromIntent(fromMainActInt);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null)
            loadDataFromIntent(intent);
    }

    private void loadDataFromIntent(Intent intent) {
        Uri uri = getIntent().getData();
        if (uri != null && uri.getQueryParameter(URL_ID_PARAMS) != null) {
            //Proveniente de un deep link ->INSTANT APP
            String orderNum = uri.getQueryParameter(URL_ID_PARAMS);
            searchOrder(Long.valueOf(orderNum));
        } else {
            //Viene de un activity
            UserOrder order = (UserOrder) intent.getSerializableExtra(ORDER_OBJ);
            if (order != null) {
                searchOrder(order.getOrder_number());
            } else
                unknownError();
        }
    }

    private void searchOrder(long orderNumber) {
        mFoodPresenter.getOrdersData(orderNumber);
        enableLoading(true);
    }

    private void enableLoading(boolean status) {
        if (status) {
            loaderAnim.setVisibility(View.VISIBLE);
            loaderAnim.startAnim();
        } else {
            loaderAnim.stopAnim();
            loaderAnim.setVisibility(View.GONE);
        }
    }

    private void showInstantsInstall() {
        if (InstantApps.isInstantApp(this)) {
            installStub.inflate();
            Button installBtn = findViewById(R.id.installBtn);
            installBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postInstall = new Intent();
                    InstantApps.showInstallPrompt(PedidoActivity.this, postInstall, 100, "");
                }
            });
        }
    }

    private void fillListPedidos(ArrayList<Pedido> lista) {
        mAdapter = new FoodsAdapter(this, lista);
        listFood.setVisibility(View.VISIBLE);
        listFood.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        listFood.setAdapter(mAdapter);
        listFood.setHasFixedSize(true);
        pedidoLbl.setVisibility(View.VISIBLE);
    }

    private void fillHistory(ArrayList<TimeLineModel> statusHistoryList) {
        mTimeLineAdapter = new TimeLineAdapter(statusHistoryList, this);
        statusLine.setVisibility(View.VISIBLE);
        statusLine.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        statusLine.setHasFixedSize(true);
        statusLine.setAdapter(mTimeLineAdapter);
        pedidoStatusLbl.setVisibility(View.VISIBLE);
    }

    //Carga contenido del header asociado con el store
    private void fillHeaderData(Store store) {
        title.setText(store.getTitle());
        ratingBar.setVisibility(View.VISIBLE);
        ratingBar.setRating(store.getScore());
        RequestOptions options = new RequestOptions();
        options.fitCenter();
        Glide.with(this).load(store.getLogo_url()).apply(options).into(logo);
        Glide.with(this).load(store.getBackgr()).apply(options).into(backgr);
    }

    //Completa el contenido del header del pedido
    private void fillIndicatorData(OrderDetails details) {
        lay.setVisibility(View.VISIBLE);
        orderNumValueLbl.setText(getString(com.cipolat.deliveryya.base.R.string.pedido_order_number).replace("@", (String.valueOf(details.getOrder_number()))));
        timeLbl.setText(getString(com.cipolat.deliveryya.base.R.string.order_time).replace("z", (String.valueOf(details.getEstimated_dipatch_time()))));
    }

    private void fillLastStatus(String status) {
        statusLbl.setVisibility(View.VISIBLE);
        statusLbl.setText(status);
    }


    private void connectionError() {
        enableLoading(false);
        stateView.connectionError();
    }

    private void unknownError() {
        enableLoading(false);
        stateView.unknownError();
    }

    private void emptyData() {
        enableLoading(false);
        stateView.emptyData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFoodPresenter.detachView();
    }

    @Override
    public void onStoreDataReady(Store storeDetails) {
        fillHeaderData(storeDetails);
    }

    @Override
    public void onOrderDetails(OrderDetails details) {
        enableLoading(false);
        fillIndicatorData(details);
        fillListPedidos(details.getPedidosList());
        fillHistory(details.getStatusHistory());
        fillLastStatus(details.getOrder_status());
        showInstantsInstall();
        footer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInvalidOrder() {
        emptyData();
    }

    @Override
    public void onNetworkError() {
        enableLoading(false);
        connectionError();
    }

    @Override
    public void onNetworkTimeOut() {

    }

    @Override
    public void onUnkownError(String error) {
        enableLoading(false);
        unknownError();
    }
}

