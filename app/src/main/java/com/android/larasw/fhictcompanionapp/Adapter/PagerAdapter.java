package com.android.larasw.fhictcompanionapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.larasw.fhictcompanionapp.GradeFragment;
import com.android.larasw.fhictcompanionapp.HomeFragment;
import com.android.larasw.fhictcompanionapp.MenuActivity;
import com.android.larasw.fhictcompanionapp.NewsFragment;
import com.android.larasw.fhictcompanionapp.ScheduleFragment;
import com.android.larasw.fhictcompanionapp.SettingsFragment;

/**
 * Created by Res Non Verba on 10/09/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                GradeFragment gradeFragment = new GradeFragment();
                return gradeFragment;
            case 2:
                ScheduleFragment scheduleFragment = new ScheduleFragment();
                return scheduleFragment;
            case 3:
                NewsFragment newsFragment = new NewsFragment();
                return newsFragment;
            case 4:
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
