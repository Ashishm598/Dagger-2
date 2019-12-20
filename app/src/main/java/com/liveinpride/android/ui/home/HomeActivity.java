package com.liveinpride.android.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.liveinpride.android.R;
import com.liveinpride.android.app.App;
import com.liveinpride.android.ui.home.core.HomePresenter;
import com.liveinpride.android.ui.home.core.HomeView;
import com.liveinpride.android.ui.home.dagger.DaggerHomeComponent;
import com.liveinpride.android.ui.home.dagger.HomeModule;
import com.liveinpride.android.utility.MyCustomWebViewClient;
import com.liveinpride.android.utility.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeActivity extends AppCompatActivity implements HomeView {


    @Inject
    HomePresenter presenter;

    @Inject
    MyCustomWebViewClient webViewClient;

    @Inject
    Utils utils;

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.btn_try_again)
    Button btnTryAgain;

    @BindView(R.id.no_network_layout)
    FrameLayout fl_no_network_layout;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout swipeToRefresh;

    String userAgent = "Mozilla/5.0 (Linux; Android 4.4.2;) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.99 Mobile Safari/537.36";

    Unbinder unbinder;

    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initView();

        DaggerHomeComponent.builder()
                .appComponent(App.getAppComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);

        // WebView Settings Setup
        setUpWebViewSettings();

        // Set WebView Client
        setWebViewClient();

        //Load WebView
        presenter.loadWebView();

        swipeToRefresh.setOnRefreshListener(mOnRefreshListener);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        new AlertDialog.Builder(this)
                                .setTitle("Exit")
                                .setMessage("Are you sure you want to exit?")
                                .setNegativeButton(android.R.string.no, null)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finishAffinity();
                                    }
                                }).create().show();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void initView() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void setUpWebViewSettings() {
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
    }

    @Override
    public void loadWebView(String url) {
        if (utils.isConnectedToInternet(this)) {
            // Load WebView
            webView.loadUrl(url);
            displayWebViewLayout();
        } else {
            displayNoNetworkLayout();
        }
    }


    @Override
    public void setWebViewClient() {
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setUserAgentString(userAgent);
    }

    @Override
    public void displayNoNetworkLayout() {
        fl_no_network_layout.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);
        Toast.makeText(this, "Please check your internet connection & Try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayWebViewLayout() {
        fl_no_network_layout.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSwipeToRefreshLayout() {

    }

    @Override
    public void hideSwipeToRefreshLayout() {
        if (swipeToRefresh.isRefreshing()) {
            swipeToRefresh.setRefreshing(false);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }

    @OnClick(R.id.btn_try_again)
    public void onViewClicked() {
        presenter.loadWebView();
    }


    SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            webView.reload();
        }
    };


}
