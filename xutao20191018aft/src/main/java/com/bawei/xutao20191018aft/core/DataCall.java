package com.bawei.xutao20191018aft.core;

import com.bawei.xutao20191018aft.bean.Data;

/**
 * author: 徐涛
 * data: 2019/10/18 15:15:07
 * function:
 */
public interface DataCall<T> {

    void onSuccess(T result);

    void onError(Data data);

}
