package com.tutuit.a1.ui.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tutuit.a1.data.network.Constant;
import com.tutuit.a1.data.viewModel.LocalMusicViewModel;
import com.tutuit.a1.R;
import com.tutuit.a1.ui.Adapter.LocalMusicAdapter;
import com.tutuit.a1.ui.Service.SongPlayService;
import com.tutuit.a1.data.bean.LocalMusicBean;
import com.tutuit.a1.databinding.FragmentLocalBinding;

import java.io.Serializable;
import java.util.List;

public class LocalmusicFragment extends Fragment implements View.OnClickListener{
    private FragmentLocalBinding binding;
    private LocalMusicViewModel viewModel;
    private LocalMusicAdapter adapter;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_local,container,false);
        viewModel = new ViewModelProvider(this).get(LocalMusicViewModel.class);
        binding.setLifecycleOwner(getActivity());
        binding.setLocal(viewModel);
        initdynamicAuthorization();
        initGetMusic();
        return binding.getRoot();
    }

    private void initTouch() {
        /**
         * 预计在此开启服务
         */
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(getActivity(), SongPlayService.class);
                intent.putExtra("list", (Serializable) viewModel.getMusicBeans());
                intent.putExtra("position",""+position);
                intent.putExtra("button","start");
                intent.putExtra("type",Constant.TYPE_LOCAL);
                intent.putExtra("time",String.valueOf(viewModel.getMusicBeans().get(position).getDuration()));
                getActivity().startService(intent);
                Toast.makeText(getActivity(),""+viewModel.getMusicBeans().get(position).getUrl(),Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * bottomSheet点击按钮
         */
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (view.getId()==R.id.songList_like){
                    Toast.makeText(getActivity(),"点击收藏",Toast.LENGTH_SHORT).show();
                }else if(view.getId() == R.id.songList_moveInformation){
                    Intent intent = new Intent("bottom_sheet_click");
                    intent.putExtra("singer",viewModel.getMusicBeans().get(position).getSinger());
                    intent.putExtra("name",viewModel.getMusicBeans().get(position).getName());
                    getActivity().sendBroadcast(intent);
                }
            }
        });
        /**
         * 播放集合的点击
         */


    }

    /**
     * 动态获取用户授权SD卡
     */
    private void initdynamicAuthorization() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else {
            initGetMusic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    initGetMusic();
                }else {
                    Toast.makeText(getActivity(),"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    /**
     * 读取本地音乐数据
     */
    @SuppressLint("Range")
    private void initGetMusic() {
        viewModel.getMusic();
        List<LocalMusicBean> musicBeans = viewModel.getMusicBeans();
        if (musicBeans==null){
            Toast.makeText(getActivity(),"本地音乐为空",Toast.LENGTH_SHORT).show();
        }
        initView(musicBeans);
    }

    /**
     * 设置UI
     */
    private void initView(List<LocalMusicBean> musicBeans) {
        binding.localRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LocalMusicAdapter(musicBeans);
        // 添加我的喜欢、歌曲详情按钮
        adapter.addChildClickViewIds(R.id.songList_like, R.id.songList_moveInformation);
        binding.localRV.setAdapter(adapter);
        initTouch();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }
}
