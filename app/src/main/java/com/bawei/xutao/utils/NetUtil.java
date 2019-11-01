package com.bawei.xutao.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: 徐涛
 * data: 2019/10/16 13:13:40
 * function:
 */
public class NetUtil {

    private static NetUtil netUtil;

    private Retrofit retrofit;

    private NetUtil(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/small/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static NetUtil getInstance(){
        if (netUtil==null){
            netUtil = new NetUtil();
        }
        return netUtil;
    }

    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }

}
