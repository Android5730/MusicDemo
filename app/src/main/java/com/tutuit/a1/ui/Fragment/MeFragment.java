package com.tutuit.a1.ui.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tutuit.a1.R;
import com.tutuit.a1.ui.Adapter.MeFragmentPagerAdapter;
import com.tutuit.a1.databinding.FragmentMeBinding;


public class MeFragment extends Fragment {
    private FragmentMeBinding binding;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  HotBean viewModel = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(HotBean.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_me,container,false);
      //  binding.setMe(viewModel);
        binding.setLifecycleOwner(getActivity());
        initView();
        initLayout();
        return binding.getRoot();
    }

    private void initLayout() {
    }

    private void initView() {
        // 为viewPager设置适配器
        binding.meViewPager.setAdapter(new MeFragmentPagerAdapter(getActivity().getSupportFragmentManager(), 0));
        // 为Tab设置ViewPager
        binding.meTabLayout.setupWithViewPager(binding.meViewPager,false);
    }
}
