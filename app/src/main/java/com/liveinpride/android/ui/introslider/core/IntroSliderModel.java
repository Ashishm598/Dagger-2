package com.liveinpride.android.ui.introslider.core;

import com.liveinpride.android.R;
import com.liveinpride.android.utility.PreferenceManager;

public class IntroSliderModel {

    private PreferenceManager preferenceManager;
    private IntroSliderPresenter presenter;

    private int[] introSliderScreens = new int[]{
            R.layout.slide_screen1,
            R.layout.slide_screen2,
            R.layout.slide_screen3
    };


    IntroSliderModel(IntroSliderPresenter presenter, PreferenceManager preferenceManager) {
        this.presenter = presenter;
        this.preferenceManager = preferenceManager;
    }

    public boolean isFirstTimeLaunch() {
        return preferenceManager.isFirstTimeLaunch();
    }


    public void setFirstTimeLaunch(boolean b) {
        preferenceManager.setFirstTimeLaunch(b);
    }

    public int[] getIntroSliderLayouts() {
        return introSliderScreens;
    }
}
