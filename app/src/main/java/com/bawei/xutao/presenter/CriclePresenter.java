package com.bawei.xutao.presenter;

import com.bawei.xutao.base.BasePresenter;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.core.IRequest;

import io.reactivex.Observable;

/**
 * author: 徐涛
 * data: 2019/10/24 20:20:43
 * function:
 */
public class CriclePresenter extends BasePresenter {
    public CriclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable getModel(IRequest iRequest, Object... args) {
        return iRequest.Cricle((String)args[0],(String) args[1],(String)args[2],(String)args[3]);
    }
}
