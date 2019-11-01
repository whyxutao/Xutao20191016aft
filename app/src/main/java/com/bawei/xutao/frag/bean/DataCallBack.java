package com.bawei.xutao.frag.bean;

/**
 * author: 徐涛
 * data: 2019/10/24 13:13:56
 * function:
 */
public interface DataCallBack<T> {

    void onSuccess(T data);
    void onError(Tao tao);

}
