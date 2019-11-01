package com.bawei.xutao.frag.bean;

import com.bawei.xutao.core.IRequest;
import com.bawei.xutao.utils.NetUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: 徐涛
 * data: 2019/10/24 13:13:55
 * function:
 */
public abstract class DBasePresenter {

    private DataCallBack dataCallBack;

    public DBasePresenter(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    public void getReqeustData(Object...args){

        IRequest iRequest = NetUtil.getInstance().create(IRequest.class);

        getDDate(iRequest,args).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Tao>() {
                    @Override
                    public void accept(Tao o) throws Exception {

                        if (o.status.equals("0000")){
                            dataCallBack.onSuccess(o.orderList);
                        }else {
                            dataCallBack.onError(o);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }

    protected abstract Observable getDDate(IRequest iRequest,Object...args);

}
