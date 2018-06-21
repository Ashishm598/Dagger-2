package com.liveinpride.android.app.builder;

import com.liveinpride.android.utility.Utils;

import dagger.Component;

@AppScope
@Component(modules = UtilsModule.class)
public interface AppComponent {
    Utils utils();
}
