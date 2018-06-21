package com.liveinpride.android.ui.home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
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

    Unbinder unbinder;

    WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        DaggerHomeComponent.builder()
                .appComponent(App.getAppComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);

        // WebView Settings Setup
        setUpWebViewSettings();

        // Set WebView Client
        setWebViewClient();


        if (utils.isConnectingToInternet(this)) {
            // Load WebView
            presenter.loadWebView();
        } else {
            showToastNetworkNotAvailable();
        }


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
        webView.loadUrl(url);
    }

    @Override
    public void showToastNetworkNotAvailable() {
        Toast.makeText(getApplicationContext(), "No Internet! Please connect to Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setWebViewClient() {
        webView.setWebViewClient(webViewClient);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.onDestroy();
    }
}
