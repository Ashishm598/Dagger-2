package com.liveinpride.android.ui.home.dagger;

import com.liveinpride.android.ui.home.HomeActivity;
import com.liveinpride.android.ui.home.core.HomeModel;
import com.liveinpride.android.ui.home.core.HomePresenter;
import com.liveinpride.android.utility.MyCustomWebViewClient;
import com.liveinpride.android.utility.Utils;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    HomeActivity homeActivity;


    public HomeModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @HomeScope
    @Provides
    HomeActivity provideContext() {
        return this.homeActivity;
    }

    @HomeScope
    @Provides
    HomePresenter providePresenter(HomeModel model) {
        return new HomePresenter(homeActivity, model);
    }

    @HomeScope
    @Provides
    HomeModel provideModel() {
        return new HomeModel();
    }

    @HomeScope
    @Provides
    MyCustomWebViewClient provideWebViewClient(Utils utils) {
        return new MyCustomWebViewClient(homeActivity, utils);
    }

}
