package com.bawei.xutao.core;

import com.bawei.xutao.bean.Data;

/**
 * author: 徐涛
 * data: 2019/10/16 13:13:59
 * function:
 */
public interface DataCall<T> {

    void onSuccess(T result);

    void onError(String data);

}
