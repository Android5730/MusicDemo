package com.tutuit.a1.ui.Fragment.CategorySongList;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.SongCategoryBean;
import com.tutuit.a1.data.bean.SongListBean;
import com.tutuit.a1.data.network.Constant;
import com.tutuit.a1.data.viewModel.SongListViewModel;
import com.tutuit.a1.databinding.ActivitySongListBinding;
import com.tutuit.a1.ui.Adapter.SongListAdapter;
import com.tutuit.a1.ui.Service.SongPlayService;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;


public class SongListFragment extends Fragment {


    private ActivitySongListBinding binding;
    private SongListViewModel<SongListBean> viewModel;
    private SongListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.setContentView(getActivity(),R.layout.activity_song_list);
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(SongListViewModel.class);
        initView();
        return binding.getRoot();
    }
    private void initTouch() {
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (view.getId()==R.id.songList_like){
                    Toast.makeText(getContext(),"点击收藏",Toast.LENGTH_LONG).show();
                }else if(view.getId() == R.id.songList_moveInformation){
                    Intent intent = new Intent("bottom_sheet_click");
                    intent.putExtra("singer",viewModel.getSongList().getValue().getSongs().get(position).getAr().get(0).getName());
                    intent.putExtra("name",viewModel.getSongList().getValue().getSongs().get(position).getName());
                    getActivity().sendBroadcast(intent);
                }
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(getActivity(), SongPlayService.class);
                Log.d("TAG", "onItemClick: 点击");
                intent.putExtra("list", (Serializable) viewModel.getSongList().getValue().getSongs());
                intent.putExtra("position",""+position);
                intent.putExtra("button","start");
                intent.putExtra("type", String.valueOf(Constant.TYPE_LINE));
                getActivity().startService(intent);
            }
        });
    }

    private void initView() {
        SongCategoryBean.PlaylistsBean songBean = (SongCategoryBean.PlaylistsBean) getActivity().getIntent().getSerializableExtra("SongBean");

        // 歌单
        viewModel.setId(songBean.getId());
        // 为MaterialButton--歌单作者头像 设置图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                Drawable creatorImage = loadImageFromNetwork(songBean.getCreator().getAvatarUrl());
                binding.creatorButton.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.creatorButton.setBackgroundDrawable(creatorImage);
                    }
                });
            }
        }).start();
        binding.categoryName.setText(songBean.getName());
        // 歌单作者名称
        binding.creatorButton.setText(songBean.getCreator().getNickname());
        // 歌单图片
        Glide.with(this)
                .load(songBean.getCoverImgUrl()).into(binding.categoryImage);
        // 设置歌单详情
        binding.categoryDescription.setText(songBean.getDescription());
        // 设置布局
        binding.listRv.setLayoutManager(new LinearLayoutManager(getContext()));
        // 监听歌曲
        viewModel.getSongList().observe(getViewLifecycleOwner(), new Observer<SongListBean>() {
            @Override
            public void onChanged(SongListBean songListBean) {
                if(songListBean!=null){
                    adapter = new SongListAdapter(songListBean.getSongs());
                    binding.listRv.setAdapter(adapter);
                    binding.progress.setVisibility(View.GONE);
                    initTouch();
                    Log.d("歌曲", "onChanged: "+songListBean.getSongs().get(0).getName());
                }
            }
        });
/*        viewModel.getSongList1().observe(this, new Observer<bean>() {
            @Override
            public void onChanged(bean bean) {
                if (bean!=null){
                    adapter = new SongListAdapter(bean.getPlaylist().getTracks());
                    binding.listRv.setAdapter(adapter);
                    binding.progress.setVisibility(View.GONE);
                    Log.d("歌曲", "onChanged: "+bean.getPlaylist().getTracks().get(0).getAl().getName());
                }
            }
        });*/
    }
    private Drawable loadImageFromNetwork(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }
        return drawable ;
    }
}