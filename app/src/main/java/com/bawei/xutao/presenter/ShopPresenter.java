package com.bawei.xutao.presenter;

import com.bawei.xutao.base.BasePresenter;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.core.IRequest;

import io.reactivex.Observable;

/**
 * author: 徐涛
 * data: 2019/10/17 10:10:55
 * function:
 */
public class ShopPresenter extends BasePresenter {


    public ShopPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.shoplist();
    }
}
