package com.bawei.xutao.base;

import com.bawei.xutao.bean.Data;
import com.bawei.xutao.bean.User;
import com.bawei.xutao.core.DataCall;
import com.bawei.xutao.core.IRequest;
import com.bawei.xutao.utils.NetUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: 徐涛
 * data: 2019/10/16 13:13:53
 * function:
 */
public abstract class BasePresenter {

    private DataCall dataCall;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }

    public void requestData(Object...args){

        IRequest iRequest = NetUtil.getInstance().create(IRequest.class);

        getModel(iRequest,args).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data o) throws Exception {

                        if (dataCall==null){
                            return;
                        }

                        if (o.status.equals("0000")){
                            dataCall.onSuccess(o.result);
                        }else{
                            dataCall.onError(o.status+o.message);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (dataCall==null)
                            return;
                        dataCall.onError(throwable.toString());

                    }
                });




    }








    protected abstract Observable getModel(IRequest iRequest,Object...args);

    public void destory(){
        dataCall = null;
    }

}
