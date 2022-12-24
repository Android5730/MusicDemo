package com.tutuit.a1.ui.Fragment.OnLinewMusic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.tutuit.a1.R;
import com.tutuit.a1.data.bean.ChoicenessBean;
import com.tutuit.a1.data.bean.SongCategoryBean;
import com.tutuit.a1.data.network.APIService;
import com.tutuit.a1.data.network.Constant;
import com.tutuit.a1.data.viewModel.OnLineMusicViewModel;
import com.tutuit.a1.databinding.FragmentOnlineBinding;
import com.tutuit.a1.ui.Adapter.OnLineSoneCategoryAdapter;
import com.tutuit.a1.ui.activity.SongListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnlineMusicFragment extends Fragment {
    private FragmentOnlineBinding binding;
    private OnLineMusicViewModel viewModel;

    private APIService apiService;
    private ChoicenessBean choicenessBean;
    private SongCategoryBean songCategoryBean;
    // 标签集合
    private List<HashMap<String,?>> choicenessList;
    private Retrofit retrofit;
    private OnLineSoneCategoryAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_online,container,false);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(OnLineMusicViewModel.class);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.RetrofitBaseUrl).build();
        binding.setOnline(viewModel);
        apiService = retrofit.create(APIService.class);
        initGetLabel();
        return binding.getRoot();
    }
    // 点击事件
    private void iniTouch() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(getActivity(), SongListActivity.class);
                intent.putExtra("SongBean",songCategoryBean.getPlaylists().get(position));
                startActivity(intent);
            }
        });
    }

    /**
     * 功能：获取歌单分类标签
     */
    private void initGetLabel() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<ChoicenessBean> call = apiService.choicenessSongList();
                call.enqueue(new Callback<ChoicenessBean>() {
                    @Override
                    public void onResponse(@NonNull Call<ChoicenessBean> call, @NonNull Response<ChoicenessBean> response) {
                        choicenessBean = response.body();
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                    @Override
                    public void onFailure(@NonNull Call<ChoicenessBean> call, @NonNull Throwable t) {
                    }
                });
            }
        }).start();

    }
    // 异步线程返回更新UI
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    // 使用此集合装置分类          实例：1.古风
                    choicenessList = new ArrayList<>();
                    HashMap<String, Object> hashMap = new HashMap<>();
                    for(ChoicenessBean.Tags tags:choicenessBean.getTags()){
                        // 调用viewModel的分类排序
                        hashMap.put("排序",viewModel.getNumber().getValue()+".");
                        hashMap.put("分类",tags.getName());
                        choicenessList.add(hashMap);
                        hashMap = (HashMap<String, Object>) hashMap.clone();

                    }
                    binding.onlineSpinner.setAdapter(new SimpleAdapter(getActivity(),choicenessList,R.layout.spinner
                            ,new String[]{"排序","分类"},new int[]{R.id.spinner_number,R.id.spinner_name}));
                    binding.onlineProgress.setVisibility(View.INVISIBLE);
                    Message message =new Message();
                    message.what = 2;
                    handler.sendMessage(message);
                    break;
                case 2:
                    // 获取完标签再获取歌单
                    initSendSongList();
                    break;
                case 3:
                    adapter = new OnLineSoneCategoryAdapter(songCategoryBean.getPlaylists());
                    binding.onlineRV.setLayoutManager(new GridLayoutManager(getActivity(),3));
                    binding.onlineRV.setAdapter(adapter);
                    iniTouch();
                    break;
            }
            return false;
        }
    });
    private void initSendSongList() {
        final String[] category = {null};
        Map<String,String> selectedItem = (Map<String, String>) binding.onlineSpinner.getSelectedItem();
        String test = selectedItem.get("分类");
        initSend(test);
        binding.onlineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category[0] = (String) choicenessList.get(position).get("分类");
                Toast.makeText(getActivity(),category[0],Toast.LENGTH_SHORT).show();
                initSend(category[0]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void initSend(String category){
        /**
         * 功能：获取分类歌单内容
         */

        apiService = retrofit.create(APIService.class);
        Call<SongCategoryBean> songCategory = apiService.getSongCategory(category,50);
        songCategory.enqueue(new Callback<SongCategoryBean>() {
            @Override
            public void onResponse(Call<SongCategoryBean> call, Response<SongCategoryBean> response) {
                songCategoryBean = response.body();
                Message message = new Message();
                message.what = 3;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<SongCategoryBean> call, Throwable t) {

            }
        });

    }
}
