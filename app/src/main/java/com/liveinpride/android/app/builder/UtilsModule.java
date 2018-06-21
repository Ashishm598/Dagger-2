package com.liveinpride.android.app.builder;

import com.liveinpride.android.utility.Utils;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @AppScope
    @Provides
    Utils provideUtils(){
      return new Utils();
    }

}
