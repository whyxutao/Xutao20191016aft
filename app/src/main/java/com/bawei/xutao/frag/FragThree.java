package com.bawei.xutao.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bawei.xutao.R;
import com.bawei.xutao.adapter.MyAdapter;
import com.bawei.xutao.base.BaseFragment;
import com.bawei.xutao.bean.Shop;
import com.bawei.xutao.bean.User;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.db.UserDao;
import com.bawei.xutao.presenter.CarPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * author: 徐涛
 * data: 2019/10/16 20:20:16
 * function:
 */
public class FragThree extends BaseFragment implements DataCall<List<Shop>>, MyAdapter.PriceLisenter {

    @BindView(R.id.shop_listView)
    ExpandableListView shopListView;
    @BindView(R.id.shop_checkAll)
    CheckBox shopCheckAll;
    @BindView(R.id.shop_text_num)
    TextView shopTextNum;
    @BindView(R.id.shop_text_price)
    TextView shopTextPrice;
    @BindView(R.id.shop_btn)
    Button shopBtn;
    Unbinder unbinder;
    private User user;
    private MyAdapter myAdapter;
    private CarPresenter carPresenter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragthree;
    }

    @Override
    protected void initView(Bundle view) {

        myAdapter = new MyAdapter(getContext());
        myAdapter.setPriceLisenter(this);
        shopListView.setAdapter(myAdapter);
        shopListView.setGroupIndicator(null);
        carPresenter = new CarPresenter(this);
        user = userDao.queryBuilder().where(UserDao.Properties.Check.eq(true)).unique();
        carPresenter.requestData(String.valueOf(user.userId),user.sessionId);

    }

    @Override
    public void onSuccess(List<Shop> result) {

           myAdapter.addAll(result);
           int groupCount = result.size();
        for (int i = 0; i < groupCount; i++) {
            shopListView.expandGroup(i);
        }

        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onError(String data) {

    }

    @Override
    public void price(double price, boolean ac) {

        shopCheckAll.setChecked(ac);
        shopTextPrice.setText("￥"+String.valueOf(price));

    }

    @OnClick({R.id.shop_checkAll, R.id.shop_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shop_checkAll:
                myAdapter.allCheck(shopCheckAll.isChecked());
                break;
            case R.id.shop_btn:
                break;
        }
    }
}
