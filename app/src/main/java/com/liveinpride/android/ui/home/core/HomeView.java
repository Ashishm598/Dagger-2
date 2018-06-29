package com.liveinpride.android.ui.home.core;

public interface HomeView {

    void initView();
    void setUpWebViewSettings();
    void loadWebView(String url);
    void setWebViewClient();
    void displayNoNetworkLayout();
    void displayWebViewLayout();
    void showSwipeToRefreshLayout();
    void hideSwipeToRefreshLayout();
}
