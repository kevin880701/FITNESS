package com.hr.fitness.Model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentArrayList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private  int mChildCount = 0;
    Fragment mCurrentPrimaryItem;
    int mBehavior;

    public ViewPagerAdapter(@NonNull FragmentManager manager, int behavior, ArrayList<Fragment> fragmentArrayList) {
        super(manager, behavior);
        mCurrentPrimaryItem = manager.getPrimaryNavigationFragment();
        mBehavior = behavior;
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title) {
//        fragmentArrayList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}
