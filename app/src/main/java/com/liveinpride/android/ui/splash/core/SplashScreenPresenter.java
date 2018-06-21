package com.liveinpride.android.ui.splash.core;

import com.liveinpride.android.ui.Presenter;

public class SplashScreenPresenter implements Presenter {

    SplashScreenView view;
    SplashScreenModel model;

    public SplashScreenPresenter(SplashScreenView view, SplashScreenModel model) {
        this.view = view;
        this.model = model;
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
