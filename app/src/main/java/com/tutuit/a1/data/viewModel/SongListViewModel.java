package com.tutuit.a1.data.viewModel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.tutuit.a1.data.bean.SongListBean;

import java.util.List;

import retrofit2.Call;

public class SongListViewModel<T> extends BaseViewModel<T>{
    private MutableLiveData<SongListBean> songList;
    private long id;// 歌单id
    public SongListViewModel(Application application) {
        super(application);
    }

    @Override
    public void getBean(Call<T> call, MutableLiveData<T> bean) {
        super.getBean(call, bean);
    }

    public MutableLiveData<SongListBean> getSongList() {
        if (songList==null){
            songList = new MutableLiveData<>();
            getBean((Call<T>) super.apiService.getSongList(String.valueOf(id),"40"), (MutableLiveData<T>) songList);
        }
        return songList;
    }


    public void setId(long id) {
        this.id = id;
    }
}
