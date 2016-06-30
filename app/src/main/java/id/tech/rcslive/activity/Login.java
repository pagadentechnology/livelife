package id.tech.rcslive.activity;

/**
 * Created by RebelCreative-A1 on 21/03/2016.
 */
import butterknife.OnClick;
import id.tech.rcslive.util.*;
import id.tech.rcslive.fragment.WalkthroughFragmentActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pixelcan.inkpageindicator.InkPageIndicator;

public class Login extends AppCompatActivity {
    boolean max=false;
    @Bind(R.id.viewpager)ViewPager viewPager;
    @Bind(R.id.btn_login)TextView btn_Login;
    @OnClick(R.id.btn_login) public void nextSlide(){
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        if(viewPager.getCurrentItem() == 2){
            btn_Login.setText("  Masuk  ");
            if(max && viewPager.getCurrentItem() == 2){
                startActivity(new Intent(getApplicationContext(), LoginForm.class));
                finish();
            }
            max =true;
        }
    }
    @Bind(R.id.indicator)
    InkPageIndicator inkPageIndicator;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        mPagerAdapter = new ScreenSlideAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        inkPageIndicator.setViewPager(viewPager);

        btn_Login.setTypeface(Font.setFontGaramond(getApplicationContext()));

    }

    private class ScreenSlideAdapter extends FragmentStatePagerAdapter{

        public ScreenSlideAdapter(FragmentManager fm){
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            if (position == 0){
                return WalkthroughFragmentActivity.create(position);
            }else if(position == 1){
                return WalkthroughFragmentActivity.create(position);
            }else{
                return WalkthroughFragmentActivity.create(position);
            }

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 3;
        }

    }

}
