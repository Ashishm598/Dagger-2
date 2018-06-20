package com.liveinpride.android.ui.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.liveinpride.android.R;
import com.liveinpride.android.ui.introslider.IntroSliderActivity;
import com.liveinpride.android.ui.splash.core.SplashScreenPresenter;
import com.liveinpride.android.ui.splash.core.SplashScreenView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {


    SplashScreenPresenter presenter;

    long Delay = 3000;
    Intent myIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SplashScreenPresenter(this);

        // Making notification bar transparent
        changeStatusBarColor();

        setContentView(R.layout.activity_splash);

        // Create a Timer
        Timer RunSplash = new Timer();
        // Task to do when the timer ends
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                gotoIntroSliderScreen();
            }
        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView() {

    }

    @Override
    public void gotoIntroSliderScreen() {
        myIntent = new Intent(SplashScreenActivity.this, IntroSliderActivity.class);
        startActivity(myIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        SplashScreenActivity.this.finish();
    }

    @Override
    public void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            // FullScreen
            getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            // Status bar to Transparent
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
