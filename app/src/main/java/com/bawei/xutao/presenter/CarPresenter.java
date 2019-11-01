package com.bawei.xutao.presenter;

import com.bawei.xutao.base.BasePresenter;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.core.IRequest;

import io.reactivex.Observable;

/**
 * author: 徐涛
 * data: 2019/10/18 14:14:16
 * function:
 */
public class CarPresenter extends BasePresenter {


    public CarPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.findShoppingCar((String)args[0],(String)args[1]);
    }
}
