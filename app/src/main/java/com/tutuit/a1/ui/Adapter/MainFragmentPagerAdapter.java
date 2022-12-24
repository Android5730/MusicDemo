package com.tutuit.a1.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tutuit.a1.ui.Fragment.AttentionFragment;
import com.tutuit.a1.ui.Fragment.CloudVillageFragment;
import com.tutuit.a1.ui.Fragment.FindFragment;
import com.tutuit.a1.ui.Fragment.MeFragment;
import com.tutuit.a1.ui.Fragment.PodcastFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] pager = new String[]{"我的","发现","博客","关注","云村"};

    public MainFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new MeFragment();
        }else if(position == 1){
            return new FindFragment();
        }else if(position == 2){
            return new PodcastFragment();
        }else if(position == 3){
            return new AttentionFragment();
        }else if(position == 4){
            return new CloudVillageFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return pager.length;
    }
    public CharSequence getPageTitle(int position) {
        return pager[position];
    }
}
