package com.liveinpride.android.ui.home.dagger;

import com.liveinpride.android.app.builder.AppComponent;
import com.liveinpride.android.ui.home.HomeActivity;

import dagger.Component;

@HomeScope
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
