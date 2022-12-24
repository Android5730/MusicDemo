package com.tutuit.a1.data.viewModel;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.tutuit.a1.data.bean.SongCategoryBean;

import retrofit2.Call;

public class OnLineMusicViewModel<T> extends BaseViewModel<T> {
    // 歌单分类排序
    private MutableLiveData<Integer> number;
    // 歌单详细bean类
    private MutableLiveData<SongCategoryBean> bean;
    private T t;

    public OnLineMusicViewModel(Application application) {
        super(application);
        if (bean == null) {
            //      getBean(super.apiService.)
            bean = new MutableLiveData<>();
        }
        this.bean = bean;
    }


    public MutableLiveData<Integer> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
            number.setValue(0);
        } else {
            number.setValue(number.getValue() + 1);
        }
        return number;
    }

    public void setNumber(MutableLiveData<Integer> number) {
        this.number = number;
    }

}
