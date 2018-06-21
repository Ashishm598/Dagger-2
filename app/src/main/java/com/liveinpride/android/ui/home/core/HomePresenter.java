package com.liveinpride.android.ui.home.core;

import com.liveinpride.android.ui.Presenter;

public class HomePresenter implements Presenter {

    private HomeView view;
    private HomeModel model;

    public HomePresenter(HomeView view, HomeModel model) {
        this.view = view;
        this.model = model;
    }

    private String getURL() {
        return model.getWebURL();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }


    public void loadWebView() {
        view.loadWebView(getURL());
    }
}
