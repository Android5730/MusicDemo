package com.tutuit.a1.ui.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.Message;

import com.tutuit.a1.data.bean.LocalMusicBean;
import com.tutuit.a1.data.bean.SongBean;
import com.tutuit.a1.data.bean.SongListBean;
import com.tutuit.a1.data.network.Constant;
import com.tutuit.a1.ui.activity.MainActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// 音乐播放的服务
public class SongPlayService extends Service {
    private MediaPlayer mediaPlayer ;
    private List<LocalMusicBean> list;
    private List<SongListBean> songListBeans;
    private int number = 0;
    private int duration1;// 歌曲时长
    private int nowPosition;
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
                }else if (Integer.parseInt(intent.getStringExtra("type"))== Constant.TYPE_LINE){
                    songListBeans = (List<SongListBean>) intent.getSerializableExtra("list");
                }
                number = Integer.parseInt(intent.getStringExtra("position"));
                int duration =  Integer.parseInt(intent.getStringExtra("time"));
                try {
                    File file = new File(""+list.get(number).getUrl());
                    // 除第一次点击后的点击
                    if (mediaPlayer!=null){
                        mediaPlayer.reset();
                    }
                    mediaPlayer.setDataSource(file.getAbsolutePath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
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
                                if (mediaPlayer.getCurrentPosition()>=duration1){
                                    number++;
                                    File file = new File(""+list.get(number).getUrl());
                                    mediaPlayer.reset();
                                    try {
                                        // 重复代码，后续可以重构下
                                        mediaPlayer.setDataSource(file.getAbsolutePath());
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
                                }else {
                                    MainActivity.handler.sendMessage(message);
                                }
                            }
                        },0,50);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "next":
                // 下一首
                number += Integer.parseInt(intent.getStringExtra("position"));
                try {
                    File file = new File(""+list.get(number).getUrl());
                    if (mediaPlayer!=null){
                        mediaPlayer.reset();
                    }
                    mediaPlayer.setDataSource(file.getAbsolutePath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
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
            Intent intent1 = new Intent("song_play");
            intent1.putExtra("songName",list.get(number).getName());
            intent1.putExtra("songSinger",list.get(number).getSinger());
            sendBroadcast(intent1);
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}