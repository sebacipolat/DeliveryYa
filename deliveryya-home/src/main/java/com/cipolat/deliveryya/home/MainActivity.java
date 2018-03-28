package com.cipolat.deliveryya.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import com.cipolat.deliveryya.R;
import com.cipolat.deliveryya.base.Model.UserOrder;
import com.cipolat.deliveryya.base.UI.CustomView.CustomSuperStateView;
import com.cipolat.deliveryya.base.UI.CustomView.Loader.AraryImageLoader;
import com.cipolat.deliveryya.order.PedidoActivity;
import java.util.ArrayList;
import cn.refactor.lib.colordialog.PromptDialog;

public class MainActivity extends AppCompatActivity implements HomeView {

    Toolbar toolbar;
    AppBarLayout appBarLayout;
    RecyclerView listOrders;
    AraryImageLoader loaderAnim;
    CustomSuperStateView stateView;
    FloatingActionButton fab;

    private OrderAdapter mAdapter;
    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appBarLayout);
        listOrders = findViewById(R.id.listRecylr);
        loaderAnim = findViewById(R.id.loaderAnim);
        stateView = findViewById(R.id.stateView);
        fab = findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        showLoading();

        mHomePresenter = new HomePresenter(this);
        mHomePresenter.setView(this);
        mHomePresenter.getUserActivity();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PromptDialog(MainActivity.this)
                        .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                        .setAnimationEnable(true)
                        .setTitleText(getString(R.string.app_info_title))
                        .setContentText(getString(R.string.app_info_label))
                        .setPositiveListener(getString(R.string.app_info_label_ok), new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog dialog) {
                                dialog.dismiss();
                            }
                        }).show();
            }
        });


    }

    private void fillList(ArrayList<UserOrder> lista) {
        listOrders.setVisibility(View.VISIBLE);
        mAdapter = new OrderAdapter(this, lista);

        listOrders.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserOrder orderSelect = mAdapter.getItemByPos(listOrders.getChildAdapterPosition(view));
                if (orderSelect != null) {
                    Intent inten = new Intent(MainActivity.this, PedidoActivity.class);
                    inten.putExtra(PedidoActivity.ORDER_OBJ, orderSelect);
                    startActivity(inten);
                }
            }
        });
        listOrders.setAdapter(mAdapter);
        listOrders.setHasFixedSize(true);
    }

    private void showLoading() {
        loaderAnim.setVisibility(View.VISIBLE);
        loaderAnim.startAnim();
    }

    private void hideLoading() {
        loaderAnim.setVisibility(View.GONE);
        loaderAnim.stopAnim();
        listOrders.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    private void connectionError() {
        hideLoading();
        stateView.connectionError();
    }

    private void unknownError() {
        hideLoading();
        stateView.unknownError();
    }

    private void emptyOrders() {
        hideLoading();
        stateView.emptyOrder();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHomePresenter.detachView();

    }

    @Override
    public void onOrdersResponseReady(ArrayList<UserOrder> responseData) {
        hideLoading();
        if (responseData != null && !responseData.isEmpty()) {
            fillList(responseData);
        } else
            emptyOrders();
    }

    @Override
    public void onOrdersResponseFail() {
        unknownError();
    }

    @Override
    public void onNetworkError() {
        connectionError();
    }

    @Override
    public void onNetworkTimeOut() {
        connectionError();
    }

    @Override
    public void onUnkownError(String error) {
        unknownError();
    }
}
