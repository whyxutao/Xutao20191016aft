package com.bawei.xutao.presenter;

import com.bawei.xutao.base.BasePresenter;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.core.IRequest;

import io.reactivex.Observable;

/**
 * author: 徐涛
 * data: 2019/10/17 20:20:13
 * function:
 */
public class BannerPresenter extends BasePresenter {
    public BannerPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.bannerShow();
    }
}
