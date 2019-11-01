package com.bawei.xutao.frag;

import android.graphics.Color;
import android.os.Bundle;

import com.bawei.xutao.R;
import com.bawei.xutao.base.BaseFragment;
import com.bawei.xutao.bean.User;
import com.bawei.xutao.db.UserDao;
import com.bawei.xutao.frag.adapter.MyRecAdapter;
import com.bawei.xutao.frag.bean.DataCallBack;
import com.bawei.xutao.frag.bean.Dshop;
import com.bawei.xutao.frag.bean.Tao;
import com.bawei.xutao.presenter.OrderPresenter;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * author: 徐涛
 * data: 2019/10/16 20:20:16
 * function:
 */
public class FragFour extends BaseFragment implements DataCallBack<List<Dshop>> {
    @BindView(R.id.pager)
    RecyclerView pager;
    @BindView(R.id.cardView)
    CardView cardView;
    private OrderPresenter orderPresenter;
    private MyRecAdapter myAdapter;
    private User user;

    @Override
    protected int getLayoutID() {
        return R.layout.fragfour;
    }

    @Override
    protected void initView(Bundle view) {

        orderPresenter = new OrderPresenter(this);
        user = userDao.queryBuilder().where(UserDao.Properties.Check.eq(true)).unique();
        orderPresenter.getReqeustData(String.valueOf(user.userId), user.sessionId, String.valueOf(0), String.valueOf(1), String.valueOf(5));

        myAdapter = new MyRecAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        pager.setLayoutManager(gridLayoutManager);

        cardView.setRadius(40);

    }


    @Override
    public void onSuccess(List<Dshop> data) {

        myAdapter.addAll(data);
        pager.setAdapter(myAdapter);

    }

    @Override
    public void onError(Tao tao) {

    }
}
