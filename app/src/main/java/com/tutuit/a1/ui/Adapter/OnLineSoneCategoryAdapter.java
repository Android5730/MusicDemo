package com.tutuit.a1.ui.Adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.tutuit.a1.R;
import com.tutuit.a1.ui.Utility.MyApplication;
import com.tutuit.a1.data.bean.SongCategoryBean;

import java.util.List;

public class OnLineSoneCategoryAdapter extends BaseQuickAdapter<SongCategoryBean.PlaylistsBean, BaseViewHolder> {
    public OnLineSoneCategoryAdapter() {
        super(R.layout.item_online);
    }

    public OnLineSoneCategoryAdapter(List<SongCategoryBean.PlaylistsBean> playlists) {
        super(R.layout.item_online,playlists);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, SongCategoryBean.PlaylistsBean playlists) {
        baseViewHolder.setText(R.id.online_rv_content,playlists.getName());
        Glide.with(MyApplication.getContext()).load(playlists.getCoverImgUrl()).into((ImageView)baseViewHolder.getView(R.id.online_rv_imageView));

    }
}
