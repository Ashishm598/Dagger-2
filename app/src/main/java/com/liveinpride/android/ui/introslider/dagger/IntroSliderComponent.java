package com.liveinpride.android.ui.introslider.dagger;

import com.liveinpride.android.ui.introslider.IntroSliderActivity;

import dagger.Component;

@IntroSliderScope
@Component(modules = IntroSliderModule.class)
public interface IntroSliderComponent {
    void inject(IntroSliderActivity introSliderActivity);
}
