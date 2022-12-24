package com.tutuit.a1.data.viewModel;

import static com.tutuit.a1.ui.Utility.MyApplication.getContext;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.provider.MediaStore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tutuit.a1.data.bean.LocalMusicBean;
import com.tutuit.a1.ui.Adapter.LocalMusicAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicViewModel extends ViewModel {
    private LocalMusicAdapter adapter;
    private List<MutableLiveData<LocalMusicBean>> musicList;


    private List<LocalMusicBean> musicBeans = new ArrayList<>();
    @SuppressLint("Range")
    public void getMusic(){
        Cursor cursor;
        cursor = getContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                String number = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                // 获取歌名
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                // 获取歌手
                String songer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                // 获取时长
          //      long duration = Long.parseLong(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                // 获取路径
                String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                // 获取专辑
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                //    cursor = getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
                //      String bitmap = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                //    String 串烧 = cursor.getString(cursor.getColumnIndex(String.valueOf(MediaStore.Images.Media.getContentUri(""+name))));
                musicBeans.add(new LocalMusicBean(name,songer,0,album,url));
            }
        }
    }

    public List<LocalMusicBean> getMusicBeans() {
        return musicBeans;
    }

    public void setMusicBeans(List<LocalMusicBean> musicBeans) {
        this.musicBeans = musicBeans;
    }

    public LocalMusicAdapter getAdapter() {
        if (adapter==null){
        }
        return adapter;
    }

}
