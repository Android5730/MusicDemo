package com.tutuit.a1.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallApi {
    public void sendGetRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.RetrofitBaseUrl).build();
        APIService apiService = retrofit.create(APIService.class);
    }
}
