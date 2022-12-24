package com.tutuit.a1.ui.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.SongListBean;

import java.util.List;

public class SongListAdapter extends BaseQuickAdapter<SongListBean.SongsBean, BaseViewHolder> {
    public SongListAdapter(@Nullable List<SongListBean.SongsBean> data) {
        super(R.layout.item_songlist, data);
    }




    @Override
    protected void convert(@NonNull BaseViewHolder helper, SongListBean.SongsBean item) {
        helper.setText(R.id.songList_name,item.getName())
                .setText(R.id.songList_singer,item.getAr().get(0).getName());
//       Glide.with(MyApplication.getContext()).load(item.getAlbum()).into((ImageView)helper.getView(R.id.songList_imageView));

    }

}
