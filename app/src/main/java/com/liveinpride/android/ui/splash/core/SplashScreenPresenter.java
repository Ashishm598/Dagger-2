package com.liveinpride.android.ui.splash.core;

import com.liveinpride.android.ui.Presenter;

public class SplashScreenPresenter implements Presenter {

    private SplashScreenView view;
    private SplashScreenModel model;

    public SplashScreenPresenter(SplashScreenView view) {
        this.view = view;
        this.model = new SplashScreenModel(this);
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

    public void gotoIntroSliderScreen() {
        view.navigateToIntroSliderScreen();
    }
}
