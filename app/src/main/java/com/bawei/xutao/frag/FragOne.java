package com.bawei.xutao.frag;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bawei.xutao.R;
import com.bawei.xutao.adapter.CenterAdapter;
import com.bawei.xutao.adapter.LastAdapter;
import com.bawei.xutao.adapter.TopAdapter;
import com.bawei.xutao.base.BaseFragment;
import com.bawei.xutao.bean.Banners;
import com.bawei.xutao.bean.CommodityList;
import com.bawei.xutao.bean.Rxxp;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.presenter.BannerPresenter;
import com.bawei.xutao.presenter.ShopPresenter;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * author: 徐涛
 * data: 2019/10/16 20:20:16
 * function:
 */
public class FragOne extends BaseFragment {


    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.mBanner)
    XBanner mBanner;
    @BindView(R.id.top_list)
    RecyclerView topList;
    @BindView(R.id.center_list)
    RecyclerView centerList;
    @BindView(R.id.last_list)
    RecyclerView lastList;

    TopAdapter topAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragone;
    }

    @Override
    protected void initView(Bundle view) {
        topList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        topAdapter = new TopAdapter();
        topList.setAdapter(topAdapter);

        BannerPresenter bannerPresenter = new BannerPresenter(new getBanners());
        bannerPresenter.requestData();
        ShopPresenter shopPresenter = new ShopPresenter(new GetList());
        shopPresenter.requestData();

    }

    class GetList implements DataCall<Rxxp> {

        @Override
        public void onSuccess(Rxxp result) {

            topAdapter.xutaotianjiashuju(result.rxxp.commodityList);
            topAdapter.notifyDataSetChanged();//刷新适配器,列表

            CommodityList mpzsh = (CommodityList) result.pzsh;
            centerList.setLayoutManager(new GridLayoutManager(getContext(), 3));
            centerList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
            centerList.setAdapter(new CenterAdapter(mpzsh.commodityList, getContext()));

            CommodityList mmlss = (CommodityList) result.mlss;
            lastList.setLayoutManager(new GridLayoutManager(getContext(), 3));
            lastList.addItemDecoration(new DividerItemDecoration(getContext(), 1));
            lastList.setAdapter(new LastAdapter(mmlss.commodityList, getContext()));

        }

        @Override
        public void onError(String data) {

        }
    }

    class getBanners implements DataCall<List<Banners>> {

        List<String> list;

        @Override
        public void onSuccess(List<Banners> result) {

            list = new ArrayList<>();

            for (int i = 0; i < result.size(); i++) {

                list.add(result.get(i).imageUrl);

            }

            mBanner.setData(list, null);
            mBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getContext()).load(list.get(position)).into((ImageView) view);
                }
            });
            mBanner.setPageTransformer(Transformer.Cube);
            mBanner.setPageChangeDuration(1000);
            mBanner.startAutoPlay();

        }

        @Override
        public void onError(String data) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.startAutoPlay();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBanner!=null) {
            mBanner.startAutoPlay();
        }
    }
}
