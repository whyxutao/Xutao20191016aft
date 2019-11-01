package com.bawei.xutao.presenter;

import com.bawei.xutao.core.IRequest;
import com.bawei.xutao.frag.bean.DBasePresenter;
import com.bawei.xutao.frag.bean.DataCallBack;

import io.reactivex.Observable;

/**
 * author: 徐涛
 * data: 2019/10/24 14:14:10
 * function:
 */
public class OrderPresenter extends DBasePresenter {
    public OrderPresenter(DataCallBack dataCallBack) {
        super(dataCallBack);
    }

    @Override
    protected Observable getDDate(IRequest iRequest, Object... args) {
        return iRequest.ODER_LIST_BEANS((String)args[0],(String) args[1],(String)args[2],(String)args[3],(String)args[4]);
    }
}
