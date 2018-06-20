package com.liveinpride.android.ui.home.core;

import com.liveinpride.android.ui.Presenter;

public class HomePresenter implements Presenter {

    private HomeView view;
    private HomeModel model;

    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new HomeModel();
    }

    public String loadWebView() {
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
}
