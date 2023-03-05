package com.tutuit.a1.data.viewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tutuit.a1.data.bean.ChoicenessBean;
import com.tutuit.a1.data.network.APIService;
import com.tutuit.a1.data.network.Constant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseViewModel<T> extends AndroidViewModel {
    protected APIService apiService;


    public BaseViewModel(Application application) {
        super(application);
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.RetrofitBaseUrl).build();
        apiService = retrofit.create(APIService.class);
    }
    public void getBean(Call<T> call, MutableLiveData<T> bean){
        if (call!=null&&bean!=null){
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                    bean.postValue(response.body());
                    Log.d("TAG", "onResponse: "+response.body().toString());
                }
                @Override
                public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                }
            });
        }
    }
}
