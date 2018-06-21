package com.liveinpride.android.ui.splash.dagger;


import com.liveinpride.android.ui.splash.SplashScreenActivity;
import com.liveinpride.android.ui.splash.core.SplashScreenModel;
import com.liveinpride.android.ui.splash.core.SplashScreenPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashScreenModule {

    SplashScreenActivity splashScreenActivity;

    public SplashScreenModule(SplashScreenActivity splashScreenActivity) {
        this.splashScreenActivity = splashScreenActivity;
    }

    @SplashScreenScope
    @Provides
    SplashScreenActivity provideContext() {
        return this.splashScreenActivity;
    }


    @SplashScreenScope
    @Provides
    SplashScreenPresenter providePresenter(SplashScreenModel model) {
        return new SplashScreenPresenter(splashScreenActivity, model);
    }

    @SplashScreenScope
    @Provides
    SplashScreenModel provideModel() {
        return new SplashScreenModel();
    }


}
