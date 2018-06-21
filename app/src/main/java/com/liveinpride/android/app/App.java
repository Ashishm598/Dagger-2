package com.liveinpride.android.app;

import android.app.Application;

import com.liveinpride.android.app.builder.AppComponent;
import com.liveinpride.android.app.builder.DaggerAppComponent;
import com.onesignal.OneSignal;

public class App extends Application {


    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().build();

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }


}
