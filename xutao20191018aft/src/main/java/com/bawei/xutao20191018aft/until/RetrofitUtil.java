package com.bawei.xutao20191018aft.until;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: 徐涛
 * data: 2019/10/18 14:14:45
 * function:
 */
public class RetrofitUtil {

    private static RetrofitUtil retrofitUtil;

    private Retrofit retrofit;

    public static RetrofitUtil getInstance(){
        if (retrofitUtil==null) {
            retrofitUtil = new RetrofitUtil();
        }
        return retrofitUtil;
    }

    private RetrofitUtil(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://v.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public <T> T create(final Class<T> service){
        return retrofit.create(service);
    }

}
