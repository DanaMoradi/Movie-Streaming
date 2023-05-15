package com.example.bbc.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.bbc.R;
import com.example.bbc.adapter.IntroAdapter;
import com.example.bbc.databinding.ActivitySplashScreenBinding;
import com.example.bbc.db.sharedPrefrences.IntroPref;
import com.example.bbc.model.IntroModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("CustomSplashScreen")
public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySplashScreenBinding binding;
    IntroPref pref;


    private ViewPager viewPager;
    List<IntroModel> list = new ArrayList<>();
    private TabLayout tabs;
    ExtendedFloatingActionButton gotoApp;
    IntroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        init();

        if (pref.isOpen()) {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        addToList();

        adapter = new IntroAdapter(list);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager, true);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == list.size() - 1) {
                    gotoApp.setAlpha(1);
                    alphaAnimation(gotoApp, 0, 1);
                    tabs.setVisibility(View.INVISIBLE);
                    gotoApp.setVisibility(View.VISIBLE);
                } else {
                    gotoApp.setAlpha(0);
                    tabs.setVisibility(View.VISIBLE);
                    gotoApp.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        gotoApp.setOnClickListener(this);

    }

    private void addToList() {
        IntroModel item = new IntroModel();
        item.setDescription("با اپلیکیشن مووی استریمینگ می\u200Cتوانید با کیفیتی مناسب با سرعت اینترنتی که دارید، فیلم و سریال\u200Cها را ببینید و نگرانی بابت قطع و وصلی یا وقفه افتادن در فیلم نداشته باشید");
        item.setImg(R.raw.splash_num_one);
        list.add(item);
        item = new IntroModel();
        item.setDescription("با اپلیکیشن مووی استریمینگ می\u200Cتوانید فیلم و سریال\u200Cهای ایرانی و خارجی، کارتون و انیمیشن را در هر زمان و مکانی به راحتی تماشا کنید");
        item.setImg(R.raw.splash_num_two);
        list.add(item);
        item = new IntroModel();
        item.setDescription("با خرید اشتراک مووی استریمینگ  به تعداد زیادی فیلم و سریال و انیمیشن دسترسی داشته باشید و چیزی را از قلم نیندازید");
        item.setImg(R.raw.splash_num_three);
        list.add(item);
        item = new IntroModel();
        item.setDescription("مووی استرمینگ با تلاش شبانه روزی خود سعی بر این دارد تمام نیازهای یک دوستدار سینما را به راحت ترین و متنوع ترین شکل ممکن برطرف سازد. لذا از شما همراهان عزیز تقاضا داریم در راستای ادامه کار، ما را حمایت کرده تا با تولید محتوای بهتر بتوانیم با کمک هم در دنیای وسیع سینما و دوبلاژ به آثاری دست نیافتنی برسیم.");
        item.setImg(R.raw.splash_num_four);
        list.add(item);

    }

    private void init() {

        pref = new IntroPref(this);

        viewPager = binding.vpIntro;
        tabs = binding.tabsIntro;
        gotoApp = binding.fabGotoApp;
    }

    private void alphaAnimation(View view, float from, float to) {
        AlphaAnimation animation = new AlphaAnimation(from, to);
        animation.setDuration(500);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        pref.saveIntro();
    }
}