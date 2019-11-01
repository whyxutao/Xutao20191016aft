package com.bawei.xutao20191018aft.base;

import com.bawei.xutao20191018aft.bean.Data;
import com.bawei.xutao20191018aft.bean.Weather;
import com.bawei.xutao20191018aft.core.DataCall;
import com.bawei.xutao20191018aft.model.IRequest;
import com.bawei.xutao20191018aft.until.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: 徐涛
 * data: 2019/10/18 15:15:06
 * function:
 */
public abstract class BasePresenter {

    private DataCall dataCall;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    public void reqeustData(Object...args){
//OkHttp: {"resultcode":"101","reason":"错误的请求KEY","result":null,"error_code":10001}
        IRequest iRequest = RetrofitUtil.getInstance().create(IRequest.class);
        getModel(iRequest,args).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data o) throws Exception {

                        if (dataCall==null)
                            return;

                        if (o.resultcode.equals("200")){
                            dataCall.onSuccess(o.result);
                        }else {
                            dataCall.onError(o);
                        }


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }

    protected abstract Observable getModel(IRequest iRequest, Object...args);

}
