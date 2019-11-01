package com.bawei.xutao20191018aft.model;

import com.bawei.xutao20191018aft.bean.Data;
import com.bawei.xutao20191018aft.bean.Weather;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author: 徐涛
 * data: 2019/10/18 14:14:49
 * function:
 */
public interface IRequest {

    @GET("weather/index")
    Observable<Data<Weather>> weather(@Query("cityname")String cityname);

}
