package com.tutuit.a1.ui.activity;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.LocalMusicBean;
import com.tutuit.a1.data.network.Constant;
import com.tutuit.a1.databinding.ActivityMainBinding;
import com.tutuit.a1.ui.Adapter.LocalMusicAdapter;
import com.tutuit.a1.ui.Adapter.MainFragmentPagerAdapter;
import com.tutuit.a1.ui.Fragment.LocalmusicFragment;
import com.tutuit.a1.ui.Receiver.BottomSheetClickReceiver;
import com.tutuit.a1.ui.Receiver.SongListReceiver;
import com.tutuit.a1.ui.Receiver.SongPlayReceiver;
import com.tutuit.a1.ui.Service.SongPlayService;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends BaseActivity{
    // 地步导航栏的选项
    private MenuItem menuItem;
    private static ActivityMainBinding binding;
    private SongPlayReceiver receiver;
    private SongListReceiver songListReceiver;
    private LocalBroadcastManager  manager;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog dialog;


    public static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            binding.bottomSheetProgress.setProgress(msg.arg1);
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setLifecycleOwner(this);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);

/*        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        }*/
        initViews();
        initBottomListener(); // 底部导航栏点击事件和滑动绑定
    }

    /**
     * 设置广播接收器，接收如下载音乐，播放音乐等消息
     */
    private void initGetReceiver() {
        // 监听歌曲播放
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("song_play");
        receiver = new SongPlayReceiver();
        registerReceiver(receiver, intentFilter);
        // 监听底部导航栏被唤出
        IntentFilter intentFilter1 = new IntentFilter();
        BottomSheetClickReceiver receiver = new BottomSheetClickReceiver();
        intentFilter1.addAction("bottom_sheet_click");
        registerReceiver(receiver,intentFilter1);
    }
    @Override
    protected void onResume() {
        super.onResume();
        initGetReceiver();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (receiver!=null){
            unregisterReceiver(receiver);
        }
    }

    /**
     * 设置小底部窗口显示播放音乐，后面或许可以用服务来完成
     * 即悬浮播放栏
     */
    public void initBottomVisibility(String name,String singer) {
        // 使得播放栏存在
        binding.mainBottom.setVisibility(View.VISIBLE);
        binding.bottomSheetName.setText(name);
        binding.bottomSheetSinger.setText(singer);
        // 设置旋转
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.image);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnimation.setInterpolator(lin);
        binding.bottomSheetImageView.startAnimation(rotateAnimation);
        // 播放按钮点击
        binding.bottomSheetStopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SongPlayService.class);
                intent.putExtra("button","stop");
                intent.putExtra("type",String.valueOf(Constant.TYPE_LOCAL));
                startService(intent);
            }
        });
        // 下一首点击
        binding.bottomSheetNextPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SongPlayService.class);
                intent.putExtra("button","next");
                intent.putExtra("position","1");
                intent.putExtra("type",String.valueOf(Constant.TYPE_LOCAL));
                startService(intent);

            }
        });
        // 播放列表点击
        binding.bottomSheetPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    /*            Intent intent = new Intent("TO Get SongList");
                // 发送本地广播
                manager.sendBroadcast(intent);*/

            }
        });
    }
    /**
     * 底部导航栏的点击切换碎片
     */
    private void initBottomListener() {
        binding.mainButtomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.me:
                        binding.fragmentVp.setCurrentItem(0);
                        Toast.makeText(MainActivity.this,"我的",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.find:
                        binding.fragmentVp.setCurrentItem(1);
                        Toast.makeText(MainActivity.this,"发现",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.podcast:
                        binding.fragmentVp.setCurrentItem(2);
                        Toast.makeText(MainActivity.this,"播客",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.attention:
                        binding.fragmentVp.setCurrentItem(3);
                        Toast.makeText(MainActivity.this,"关注",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.CloudVillage:
                        binding.fragmentVp.setCurrentItem(4);
                        Toast.makeText(MainActivity.this,"云村",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        binding.fragmentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(menuItem !=null){
                    menuItem.setChecked(false);
                }else {
                    binding.mainButtomNav.getMenu().getItem(0).setChecked(false);
                }
                menuItem = binding.mainButtomNav.getMenu().getItem(position);
                menuItem.setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    public static void setMediaMaxProgress(int max){
        binding.bottomSheetProgress.setMax(max);
    }

    /**
     * 设置bottomSheet数据
     * @param name
     * @param singer
     */
    public void setBottomSheetState(String name,String singer){
        // 设置折叠
     //   bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_bottomsheet,null);
        TextView bottomSheet1Name = (TextView) view.findViewById(R.id.bottom_sheet1_name);
        TextView bottomSheet1Singer = (TextView) view.findViewById(R.id.bottom_sheet1_singer);
        dialog.setContentView(view);
        dialog.show();
        bottomSheet1Name.setText("歌手："+name);
        bottomSheet1Singer.setText(singer);
    }

    /**
     * 获取歌曲集合
     * @param songList
     */
    public void setBottomSheetSongList(List<LocalMusicBean> songList){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_bs_songlist,null);
        RecyclerView recyclerView =(RecyclerView) view.findViewById(R.id.bs_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LocalMusicAdapter(songList));
        dialog.setContentView(view);
     //   new Gson().get
        dialog.show();


    }
    /**
     * 为viewPager设置适配器
     * 同时为ViewPager设置懒加载
     */
    private void initViews() {
        MainFragmentPagerAdapter adapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), 0);
        binding.fragmentVp.setAdapter(adapter);
        binding.fragmentVp.setOffscreenPageLimit(4);
        // 注册本地广播管理器
        manager = LocalBroadcastManager.getInstance(this);
        // 注册筛选器
        IntentFilter intentFilter = new IntentFilter();
        // 筛选动作
        intentFilter.addAction("TO Get SongList");
        // new广播对象
        songListReceiver = new SongListReceiver();
        // 注册本地广播
        manager.registerReceiver(songListReceiver,intentFilter);

/*       // 获取bottomSheet
        bottomSheetBehavior= BottomSheetBehavior.from(binding.bottomSheet);
        // 设置初始状态为隐藏
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                break;
            case R.id.search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        manager.unregisterReceiver(songListReceiver);
        super.onDestroy();
    }
}