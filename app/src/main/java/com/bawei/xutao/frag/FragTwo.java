package com.bawei.xutao.frag;

import android.os.Bundle;

import com.bawei.xutao.R;
import com.bawei.xutao.adapter.MyCircleAdapter;
import com.bawei.xutao.base.BaseFragment;
import com.bawei.xutao.bean.Cricle;
import com.bawei.xutao.bean.Data;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.presenter.CriclePresenter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * author: 徐涛
 * data: 2019/10/16 20:20:16
 * function:
 */
public class FragTwo extends BaseFragment implements DataCall<List<Cricle>> {
    @BindView(R.id.circle_imageView)
    RecyclerView circleImageView;
    private CriclePresenter criclePresenter;
    private MyCircleAdapter myCircleAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragtwo;
    }

    @Override
    protected void initView(Bundle view) {

        criclePresenter = new CriclePresenter(this);
        myCircleAdapter = new MyCircleAdapter(getActivity());
        criclePresenter.requestData(String.valueOf(9723),"15719204070719723",String.valueOf(1),String.valueOf(5));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        circleImageView.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void onSuccess(List<Cricle> result) {

        myCircleAdapter.addAll(result);
        circleImageView.setAdapter(myCircleAdapter);

    }

    @Override
    public void onError(String data) {

    }
}
