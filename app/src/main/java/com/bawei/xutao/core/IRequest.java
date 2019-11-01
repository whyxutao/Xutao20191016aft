package com.bawei.xutao.core;

import com.bawei.xutao.bean.Banners;
import com.bawei.xutao.bean.CommodityList;
import com.bawei.xutao.bean.Cricle;
import com.bawei.xutao.bean.Data;
import com.bawei.xutao.bean.Rxxp;
import com.bawei.xutao.bean.Shop;
import com.bawei.xutao.bean.User;
import com.bawei.xutao.frag.bean.Dshop;
import com.bawei.xutao.frag.bean.Tao;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * author: 徐涛
 * data: 2019/10/16 13:13:45
 * function:
 */
public interface IRequest {


    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<Data<User>> login(@Field("phone")String phone,@Field("pwd")String pwd);

    @GET("commodity/v1/commodityList")
    Observable<Data<Rxxp>> shoplist();

    @GET("commodity/v1/bannerShow")
    Observable<Data<List<Banners>>> bannerShow();

    @GET("order/verify/v1/findShoppingCart")
    Observable<Data<List<Shop>>> findShoppingCar(@Header("userId")String userId,
                                                 @Header("sessionId")String sessionId);

    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Tao<Dshop>> ODER_LIST_BEANS(@Header("userId")String userId, @Header("sessionId")String sessionId,
                                           @Query("status")String status,@Query("page")String page,@Query("count")String count);

    @GET("circle/v1/findCircleList")
    Observable<Data<List<Cricle>>> Cricle(@Header("userId")String userId, @Header("sessionId")String sessionId,
                                          @Query("page")String page,@Query("count")String count);
    
}
