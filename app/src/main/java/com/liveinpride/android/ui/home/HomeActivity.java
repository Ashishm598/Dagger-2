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
import com.liveinpride.android.ui.home.core.HomeModel;
import com.liveinpride.android.ui.home.core.HomePresenter;
import com.liveinpride.android.ui.home.core.HomeView;
import com.liveinpride.android.utility.MyCustomWebViewClient;
import com.liveinpride.android.utility.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeActivity extends AppCompatActivity implements HomeView {


    // Use Dagger
    private HomePresenter presenter;
    private Utils utils;

    @BindView(R.id.webView)
    WebView webView;

    Unbinder unbinder;

    WebSettings webSettings;
    MyCustomWebViewClient webViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        // Use Dagger
        utils = new Utils(this);
        presenter = new HomePresenter(this, utils);

        // WebView Settings Setup
        setUpWebViewSettings();

        // Set WebView Client
        setWebViewClient();

        // Load WebView
        presenter.loadWebView();

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
        webViewClient = new MyCustomWebViewClient(this, utils);
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
