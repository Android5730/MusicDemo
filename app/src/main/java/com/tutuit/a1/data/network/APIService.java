package com.tutuit.a1.data.network;

import com.tutuit.a1.data.bean.ChoicenessBean;
import com.tutuit.a1.data.bean.SongCategoryBean;
import com.tutuit.a1.data.bean.SongListBean;
import com.tutuit.a1.data.bean.bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("/playlist/highquality/tags")
    Call<ChoicenessBean> choicenessSongList(); // 精选歌单

    @GET("/top/playlist/highquality")
    Call<SongCategoryBean> getSongCategory(@Query("cat") String category, @Query("limit") int limit); // 根据歌单名获取歌单

    @GET("/playlist/track/all")
    Call<SongListBean> getSongList(@Query("id")String id, @Query("limit")String limit);// 获取歌单内歌曲
    @GET("/playlist/detail")
    Call<bean> getSongList(@Query("id")String id);// 获取歌单内歌曲
    @GET("/song/url/v1")
    Call<bean> getOnlineMusic(@Query("id")String id,@Query("level")String level);// 获取网络歌曲url
}
