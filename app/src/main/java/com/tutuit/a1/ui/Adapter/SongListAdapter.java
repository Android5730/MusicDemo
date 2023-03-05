package com.tutuit.a1.ui.Adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.SongListBean;
import com.tutuit.a1.data.bean.bean;
import com.tutuit.a1.ui.Utility.MyApplication;

import java.util.List;

public class SongListAdapter extends BaseQuickAdapter<SongListBean.SongsBean, BaseViewHolder> {
   /* public SongListAdapter(@Nullable List<bean.PlaylistBean.TracksBean> data) {
        super(R.layout.item_songlist, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, bean.PlaylistBean.TracksBean tracksBean) {
        baseViewHolder.setText(R.id.songList_name,tracksBean.getAl().getName())
                        .setText(R.id.songList_singer,tracksBean.getAr().get(0).getName());
        Glide.with(MyApplication.getContext()).load(tracksBean.getAl().getPicUrl()).into((ImageView)baseViewHolder.getView(R.id.song_imageView));
    }*/

    public SongListAdapter(@Nullable List<SongListBean.SongsBean> data) {
        super(R.layout.item_songlist, data);
    }




    @Override
    protected void convert(@NonNull BaseViewHolder helper, SongListBean.SongsBean item) {
        helper.setText(R.id.songList_name,item.getName())
                .setText(R.id.songList_singer,item.getAr().get(0).getName());
       Glide.with(MyApplication.getContext()).load(item.getAl().getPic()).into((ImageView)helper.getView(R.id.song_imageView));

    }

}
