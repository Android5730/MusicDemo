package com.tutuit.a1.ui.Fragment;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
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
import com.tutuit.a1.ui.activity.MainActivity;

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
                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getContext(),0,intent1,0);
                // 开启通知
                NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(notificationChannel);
                    // 参数:上下文环境;构造的通知将被发布到这个NotificationChannel上
                    Notification notification = new NotificationCompat.Builder(getContext(),"播放音乐")
                            .setContentTitle(viewModel.getMusicBeans().get(position).getName())
                            .setContentText(viewModel.getMusicBeans().get(position).getSinger())
                            .setWhen(System.currentTimeMillis()) //事件发生的时间 （currentTimeMillis()毫秒；nanoTime()纳秒）
                            // 设置通知中显示的小图标。只能用纯alpha图层的图片进行设置
                            .setSmallIcon(R.drawable.music)
                            .setContentIntent(pi)
                            // 设置通知中显示的大图标。
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.music))
                            .setChannelId("AppTestNotificationId")
                            .build();
                    manager.notify(1,notification);
                }
                Intent intent = new Intent(getActivity(), SongPlayService.class);
                intent.putExtra("list", (Serializable) viewModel.getMusicBeans());
                intent.putExtra("position",""+position);
                intent.putExtra("button","start");
                intent.putExtra("type",String.valueOf(Constant.TYPE_LOCAL));
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
