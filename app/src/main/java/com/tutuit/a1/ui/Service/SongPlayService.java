package com.tutuit.a1.ui.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Message;

import androidx.core.app.NotificationCompat;

import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.LocalMusicBean;
import com.tutuit.a1.data.bean.SongListBean;
import com.tutuit.a1.data.bean.bean;
import com.tutuit.a1.data.network.APIService;
import com.tutuit.a1.data.network.Constant;
import com.tutuit.a1.ui.activity.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 音乐播放的服务
public class SongPlayService extends Service implements MediaPlayer.OnPreparedListener {
    private MediaPlayer mediaPlayer ;
    private List<LocalMusicBean> list;
    private List<SongListBean.SongsBean> songListBeans;
    private int number = 0;
    private File file;
    private int duration1;// 歌曲时长

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    class SongPlayBinder extends Binder{
        public void startPlay(){
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        if (mediaPlayer==null){
           mediaPlayer = new MediaPlayer();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getStringExtra("button")){
            // 开始播放音乐，点击item
            case "start":
                if (Integer.parseInt(intent.getStringExtra("type"))== Constant.TYPE_LOCAL){
                    list = (List<LocalMusicBean>) intent.getSerializableExtra("list");
                  //  int duration =  Integer.parseInt(intent.getStringExtra("time"));
                }else if (Integer.parseInt(intent.getStringExtra("type"))== Constant.TYPE_LINE){
                    songListBeans = (List<SongListBean.SongsBean>) intent.getSerializableExtra("list");
                }
                number = Integer.parseInt(intent.getStringExtra("position"));
                try {
                    if (songListBeans==null){
                        // 除第一次点击后的点击
                        if (mediaPlayer!=null){
                            mediaPlayer.reset();
                        }
                        file = new File(""+list.get(number).getUrl());
                        mediaPlayer.setDataSource(file.getAbsolutePath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();

                    }else {
                        sendOnlineMusic(songListBeans.get(number));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "next":
                // 下一首
                number += Integer.parseInt(intent.getStringExtra("position"));
                try {
                    if (mediaPlayer!=null){
                        mediaPlayer.reset();
                    }
                    File file = null;
                    if (songListBeans==null){
                        file = new File(""+list.get(number).getUrl());
                        mediaPlayer.setDataSource(file.getAbsolutePath());
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    }else {
                        sendOnlineMusic(songListBeans.get(++number));
                    }
                    changeNotification(number);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "stop":
                // 暂停
                if(mediaPlayer.isPlaying()){
                    // 暂停
                    mediaPlayer.pause();
                }else {
                    // 再度播放
                    mediaPlayer.start();
                }
                break;
        }
        // 进度监听
        progress();
        if (Integer.parseInt(intent.getStringExtra("type"))== Constant.TYPE_LOCAL){
            Intent intent1 = new Intent("song_play");
            intent1.putExtra("songName",list.get(number).getName());
            intent1.putExtra("songSinger",list.get(number).getSinger());
            sendBroadcast(intent1);
        }
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void changeNotification(int number){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        // 开启通知
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
            // 参数:上下文环境;构造的通知将被发布到这个NotificationChannel上
            Notification notification = new NotificationCompat.Builder(getApplicationContext(),"播放音乐")
                    .setContentTitle(list.get(number).getName()+"     正在播放")
                    .setContentText(list.get(number).getSinger())
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
    }
    // 进行进度监听
    private void progress(){
        // 获取当前音频时长
        duration1 = mediaPlayer.getDuration();
        // 通过静态方法设置时长给悬空按钮
        MainActivity.setMediaMaxProgress(Math.toIntExact(duration1));
        if (mediaPlayer.isPlaying()){
            // 计时器 50毫秒执行一次
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 实例化
                    Message message = Message.obtain();
                    // 获取歌曲播放的进度位置
                    message.arg1 = mediaPlayer.getCurrentPosition();
                    // 歌曲已经放完，自动执行下一首
                    MainActivity.handler.sendMessage(message);
                    if (mediaPlayer.getCurrentPosition()>=duration1){
                        number++;
                        changeNotification(number);
                        File file = new File(""+list.get(number).getUrl());
                        mediaPlayer.reset();
                        try {
                            // 重复代码，后续可以重构下
                            mediaPlayer.setDataSource(file.getAbsolutePath());
                            //     mediaPlayer.setDataSource(SongPlayService.this, Uri.parse("mediaPlayer.setDataSource(this,http://m7.music.126.net/20230204171454/122c53c1befc7b13cb7aca75fec5a331/ymusic/0fd6/4f65/43ed/a8772889f38dfcb91c04da915b301617.mp3"));
                            mediaPlayer.prepare();
                            mediaPlayer.start();

                            // 获取当前音频时长
                            duration1 = mediaPlayer.getDuration();
                            // 通过静态方法设置时长给悬空按钮
                            MainActivity.setMediaMaxProgress(Math.toIntExact(duration1));
                            Intent intent1 = new Intent("song_play");
                            intent1.putExtra("songName",list.get(number).getName());
                            intent1.putExtra("songSinger",list.get(number).getSinger());
                            sendBroadcast(intent1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },0,50);
        }

    }
    // 发送网络音乐请求
    public void sendOnlineMusic(SongListBean.SongsBean songListBean){
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.RetrofitBaseUrl).build();
        APIService apiService = retrofit.create(APIService.class);
        Call<bean> onlineMusic = apiService.getOnlineMusic(String.valueOf(songListBean.getId()), "exhigh");
        onlineMusic.enqueue(new Callback<bean>() {
            @Override
            public void onResponse(Call<bean> call, Response<bean> response) {
                bean bea = response.body();
                Uri parse = Uri.parse(bea.getData().get(0).getUrl());
                try {
                    mediaPlayer.setDataSource(SongPlayService.this,parse);
                    mediaPlayer.prepare();
                    mediaPlayer.start();


                    duration1 = mediaPlayer.getDuration();
                    // 通过静态方法设置时长给悬空按钮
                    MainActivity.setMediaMaxProgress(Math.toIntExact(duration1));
                    Intent intent1 = new Intent("song_play");
                    intent1.putExtra("songName",songListBean.getName());
                    intent1.putExtra("songSinger",songListBean.getAr().get(0).getName());
                    sendBroadcast(intent1);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            number++;
                            changeNotification(number);
                            bean bea = response.body();
                            Uri parse = Uri.parse(bea.getData().get(0).getUrl());
                            try {
                                mediaPlayer.setDataSource(SongPlayService.this,parse);
                                mediaPlayer.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mediaPlayer.start();
                            duration1 = mediaPlayer.getDuration();
                            // 通过静态方法设置时长给悬空按钮
                            MainActivity.setMediaMaxProgress(Math.toIntExact(duration1));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<bean> call, Throwable t) {
            }
        });
    }
}