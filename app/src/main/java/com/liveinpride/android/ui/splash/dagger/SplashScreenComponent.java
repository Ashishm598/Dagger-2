package com.liveinpride.android.ui.splash.dagger;


import com.liveinpride.android.ui.splash.SplashScreenActivity;

import dagger.Component;

@SplashScreenScope
@Component(modules = SplashScreenModule.class)
public interface SplashScreenComponent {
    void inject(SplashScreenActivity splashScreenActivity);
}
