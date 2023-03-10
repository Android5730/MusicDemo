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
         * ????????????????????????
         */
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent1 = new Intent(getActivity(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getContext(),0,intent1,0);
                // ????????????
                NotificationManager manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(notificationChannel);
                    // ??????:???????????????;????????????????????????????????????NotificationChannel???
                    Notification notification = new NotificationCompat.Builder(getContext(),"????????????")
                            .setContentTitle(viewModel.getMusicBeans().get(position).getName())
                            .setContentText(viewModel.getMusicBeans().get(position).getSinger())
                            .setWhen(System.currentTimeMillis()) //????????????????????? ???currentTimeMillis()?????????nanoTime()?????????
                            // ????????????????????????????????????????????????alpha???????????????????????????
                            .setSmallIcon(R.drawable.music)
                            .setContentIntent(pi)
                            // ????????????????????????????????????
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
         * bottomSheet????????????
         */
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (view.getId()==R.id.songList_like){
                    Toast.makeText(getActivity(),"????????????",Toast.LENGTH_SHORT).show();
                }else if(view.getId() == R.id.songList_moveInformation){
                    Intent intent = new Intent("bottom_sheet_click");
                    intent.putExtra("singer",viewModel.getMusicBeans().get(position).getSinger());
                    intent.putExtra("name",viewModel.getMusicBeans().get(position).getName());
                    getActivity().sendBroadcast(intent);
                }
            }
        });
        /**
         * ?????????????????????
         */


    }

    /**
     * ????????????????????????SD???
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
     * ????????????????????????
     */
    @SuppressLint("Range")
    private void initGetMusic() {
        viewModel.getMusic();
        List<LocalMusicBean> musicBeans = viewModel.getMusicBeans();
        if (musicBeans==null){
            Toast.makeText(getActivity(),"??????????????????",Toast.LENGTH_SHORT).show();
        }
        initView(musicBeans);
    }

    /**
     * ??????UI
     */
    private void initView(List<LocalMusicBean> musicBeans) {
        binding.localRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LocalMusicAdapter(musicBeans);
        // ???????????????????????????????????????
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
