package com.bawei.xutao;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.xutao.frag.FragFive;
import com.bawei.xutao.frag.FragFour;
import com.bawei.xutao.frag.FragOne;
import com.bawei.xutao.frag.FragThree;
import com.bawei.xutao.frag.FragTwo;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopActivity extends AppCompatActivity {

    

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.ten1)
    RadioButton ten1;
    @BindView(R.id.ten2)
    RadioButton ten2;
    @BindView(R.id.ten3)
    RadioButton ten3;
    @BindView(R.id.ten4)
    RadioButton ten4;
    @BindView(R.id.ten5)
    RadioButton ten5;
    @BindView(R.id.radio)
    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        List<Fragment> fragments = new ArrayList<>();

        fragments.add(new FragOne());
        fragments.add(new FragTwo());
        fragments.add(new FragThree());
        fragments.add(new FragFour());
        fragments.add(new FragFive());

        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                radio.check(radio.getChildAt(position).getId());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.ten1, R.id.ten2, R.id.ten3, R.id.ten4, R.id.ten5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ten1:
                viewpager.setCurrentItem(0);
                break;
            case R.id.ten2:
                viewpager.setCurrentItem(1);
                break;
            case R.id.ten3:
                viewpager.setCurrentItem(2);
                break;
            case R.id.ten4:
                viewpager.setCurrentItem(3);
                break;
            case R.id.ten5:
                viewpager.setCurrentItem(4);
                break;
        }
    }
}
