package com.liveinpride.android.ui.introslider.dagger;

import com.liveinpride.android.ui.introslider.IntroSliderActivity;
import com.liveinpride.android.ui.introslider.core.IntroSliderModel;
import com.liveinpride.android.ui.introslider.core.IntroSliderPresenter;
import com.liveinpride.android.utility.PreferenceManager;

import dagger.Module;
import dagger.Provides;

@Module
public class IntroSliderModule {

    IntroSliderActivity introSliderActivity;


    public IntroSliderModule(IntroSliderActivity introSliderActivity) {
        this.introSliderActivity = introSliderActivity;
    }

    @IntroSliderScope
    @Provides
    IntroSliderActivity provideContext() {
        return this.introSliderActivity;
    }

    @IntroSliderScope
    @Provides
    IntroSliderPresenter providePresenter(IntroSliderModel introSliderModel) {
        return new IntroSliderPresenter(introSliderActivity, introSliderModel);
    }

    @IntroSliderScope
    @Provides
    IntroSliderModel provideModel(PreferenceManager preferenceManager) {
        return new IntroSliderModel(preferenceManager);
    }

    @IntroSliderScope
    @Provides
    PreferenceManager providePreferenceManager() {
        return new PreferenceManager(introSliderActivity);
    }

}
