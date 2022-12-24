package com.tutuit.a1.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.LocalMusicBean;

import java.util.List;

public class LocalMusicAdapter extends BaseQuickAdapter<LocalMusicBean, BaseViewHolder>{
    public LocalMusicAdapter(@Nullable List<LocalMusicBean> data) {
        super(R.layout.item_songlist, data);
    }



    @Override
    protected void convert(@NonNull BaseViewHolder helper, LocalMusicBean item) {
         helper.setText(R.id.songList_name,item.getName())
                .setText(R.id.songList_singer,item.getSinger());
//       Glide.with(MyApplication.getContext()).load(item.getAlbum()).into((ImageView)helper.getView(R.id.songList_imageView));

    }
}


