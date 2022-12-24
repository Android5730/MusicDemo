package com.tutuit.a1.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.tutuit.a1.ui.Fragment.LocalmusicFragment;
import com.tutuit.a1.ui.Fragment.OnLinewMusic.OnlineMusicFragment;

public class MeFragmentPagerAdapter extends FragmentPagerAdapter {
    private String[] pager = new String[]{"本地音乐","网络音乐"};
    public MeFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new LocalmusicFragment();
        }else if(position == 1){
            return new OnlineMusicFragment();
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
