package com.bawei.xutao20191018aft.presenter;

import com.bawei.xutao20191018aft.base.BasePresenter;
import com.bawei.xutao20191018aft.core.DataCall;
import com.bawei.xutao20191018aft.model.IRequest;

import io.reactivex.Observable;

/**
 * author: 徐涛
 * data: 2019/10/18 15:15:23
 * function:
 */
public class WeaPresenter extends BasePresenter {
    public WeaPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.weather((String)args[0]);
    }
}
