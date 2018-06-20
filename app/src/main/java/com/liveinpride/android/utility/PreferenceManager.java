package com.liveinpride.android.utility;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by user on 01-08-2017.
 */

public class PreferenceManager {
    private SharedPreferences intro_slider_sf,fullscreen_mode_sf;
    private SharedPreferences.Editor intro_slider_editor, fullscreen_mode_editor;
    private Context _context;

    // shared pref mode
    private int PRIVATE_MODE = 0;


    // Shared preferences intro slider file name
    private static final String INTRO_SLIDER = "intro_slider-welcome";         // File name
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";    // Key name

    // Shared preferences fullscreen file name
    private static final String FULLSCREEN_MODE = "full_screen_mode";                      // File name
    private static final String IS_FULLSCREEN_MODE_ENABLE = "IsFullscreenModeEnable";     // Key name


    public PreferenceManager(Context context) {
        this._context = context;

        intro_slider_sf = _context.getSharedPreferences(INTRO_SLIDER, PRIVATE_MODE);
        fullscreen_mode_sf = _context.getSharedPreferences(FULLSCREEN_MODE, PRIVATE_MODE);

        intro_slider_editor = intro_slider_sf.edit();
        fullscreen_mode_editor = fullscreen_mode_sf.edit();

    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        intro_slider_editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        intro_slider_editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return intro_slider_sf.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFullScreenMode(boolean switchStatus){
        fullscreen_mode_editor.putBoolean(IS_FULLSCREEN_MODE_ENABLE, switchStatus);
        fullscreen_mode_editor.commit();

    }


    public boolean isFullScreenModeEnable(){
      //  return fullscreen_mode_sf.getBoolean(IS_FULLSCREEN_MODE_ENABLE, WebViewAppConfig.fullscreenMode);
        return false;
    }
}
