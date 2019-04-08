package com.example.androidassignment2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numberOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.numberOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0: return new FragmentClassic();
            case 1: return new FragmentPop();
            case 2: return new FragmentRock();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
