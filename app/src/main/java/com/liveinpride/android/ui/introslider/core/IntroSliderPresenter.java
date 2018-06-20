package com.liveinpride.android.ui.introslider.core;

import com.liveinpride.android.ui.Presenter;
import com.liveinpride.android.utility.PreferenceManager;

public class IntroSliderPresenter implements Presenter {

    private IntroSliderView view;
    private IntroSliderModel model;

    public IntroSliderPresenter(IntroSliderView view, PreferenceManager preferenceManager) {
        this.view = view;
        this.model = new IntroSliderModel(this, preferenceManager);
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

    public void launchHomeActivityIfNotFirstTime() {
        if (!model.isFirstTimeLaunch()) {
            model.setFirstTimeLaunch(false);
            view.navigateToHomeScreen();
        }
    }

    public int[] getIntroSliderLayouts() {
        return model.getIntroSliderLayouts();
    }

    public void nextBtnClicked() {
        model.setFirstTimeLaunch(false);
        view.navigateToHomeScreen();
    }

    public void skipBtnClicked() {
        model.setFirstTimeLaunch(false);
        view.navigateToHomeScreen();
    }
}
