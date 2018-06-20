package com.liveinpride.android.ui.home.core;

import com.liveinpride.android.ui.Presenter;
import com.liveinpride.android.utility.Utils;

public class HomePresenter implements Presenter {

    private HomeView view;
    private HomeModel model;
    private Utils utils;

    public HomePresenter(HomeView view, Utils utils) {
        this.view = view;
        this.utils = utils;
        this.model = new HomeModel();
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

    private boolean isNetworkAvailable() {
        return utils.isConnectingToInternet();
    }


    public void loadWebView() {
        if (isNetworkAvailable()) {
            view.loadWebView(getURL());
        } else {
            view.showToastNetworkNotAvailable();
        }
    }
}
