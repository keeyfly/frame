package com.hx.novel.frame.http;

import android.content.Context;

import com.hx.novel.common.constant.Constant;
import com.hx.novel.frame.api.RequestApiClient;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FrameRetrofit {

    protected static FrameRetrofit retrofitManager = null;
    protected FrameHttp baseHttp;
    protected RequestApiClient apiService;

    public FrameRetrofit(Context context) {
        if (baseHttp == null) {
            synchronized (FrameRetrofit.class) {
                baseHttp = new FrameHttp(context);
            }
        }
        sendRequest();
    }

    public static FrameRetrofit Builder(Context context) {
        if (retrofitManager == null) {
            synchronized (FrameRetrofit.class) {
                retrofitManager = new FrameRetrofit(context);
            }
        }
        return retrofitManager;
    }

    private void sendRequest() {
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .client(baseHttp.getHttp())
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
                .addConverterFactory(GsonConverterFactory.create())// 使用G_SON作为数据转换器
                .build();
        apiService = retrofit.create(RequestApiClient.class);
    }

    public RequestApiClient getApiService() throws NullPointerException {
        return apiService;
    }
}
