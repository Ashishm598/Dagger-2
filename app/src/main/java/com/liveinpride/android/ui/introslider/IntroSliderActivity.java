package com.liveinpride.android.ui.introslider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liveinpride.android.R;
import com.liveinpride.android.ui.home.HomeActivity;
import com.liveinpride.android.ui.introslider.core.IntroSliderPresenter;
import com.liveinpride.android.ui.introslider.core.IntroSliderView;
import com.liveinpride.android.utility.PreferenceManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by user on 01-08-2017.
 */

public class IntroSliderActivity extends AppCompatActivity implements IntroSliderView {


    // Use Dagger
    IntroSliderPresenter presenter;
    private PreferenceManager preferenceManager;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tv_swipe_to_begin)
    TextView tvSwipeToBegin;

    @BindView(R.id.dotsLayout)
    LinearLayout dotsLayout;

    @BindView(R.id.btn_next)
    Button btnNext;

    @BindView(R.id.btn_skip)
    Button btnSkip;

    Unbinder unbinder;

    private IntroViewPagerAdapter introViewPagerAdapter;
    private TextView[] dots;
    private int[] layouts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use Dagger
        preferenceManager = new PreferenceManager(this);
        presenter = new IntroSliderPresenter(this, preferenceManager);

        // Checking for first time launch - before calling setContentView() ?if yes goto HomeScreen.
        presenter.launchHomeActivityIfNotFirstTime();


        // Making notification bar transparent
        changeStatusBarColor();


        super.setContentView(R.layout.activity_welcome);
        unbinder = ButterKnife.bind(this);

        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = presenter.getIntroSliderLayouts();

        // adding bottom dots
        addBottomDots(0);


        introViewPagerAdapter = new IntroViewPagerAdapter(layouts);
        viewPager.setAdapter(introViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


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
        unbinder.unbind();
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int colorsActive = getResources().getColor(R.color.dot_active);
        int colorInactive = getResources().getColor(R.color.dot_inactive);

        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);


        switch (currentPage) {
            case 0:
                tvSwipeToBegin.setTextColor(getResources().getColor(R.color.white));
                btnSkip.setTextColor(getResources().getColor(R.color.white));
                break;

            case 1:
                btnSkip.setTextColor(getResources().getColor(R.color.primary_text_color));
                tvSwipeToBegin.setTextColor(getResources().getColor(R.color.primary_text_color));
                break;

            case 2:
                tvSwipeToBegin.setTextColor(getResources().getColor(R.color.primary_text_color));
                break;

        }


    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setVisibility(View.INVISIBLE);
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };


    @Override
    public void initView() {

    }


    @Override
    public void navigateToHomeScreen() {
        startActivity(new Intent(IntroSliderActivity.this, HomeActivity.class));
        finish();
    }


    /**
     * Making notification bar transparent
     */
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


    @OnClick(R.id.btn_next)
    public void onBtnNextClicked() {
        // checking for last page
        // if last page home screen will be launched
        int current = getItem(+1);
        if (current < layouts.length) {
            // move to next screen
            viewPager.setCurrentItem(current);
        } else {
            presenter.nextBtnClicked();
        }
    }

    @OnClick(R.id.btn_skip)
    public void onBtnSkipClicked() {
        presenter.skipBtnClicked();
    }
}