package com.hx.novel.frame.http;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 李贺翔 on 2016/8/31.
 * OK_HTTP相关配置
 */
public class FrameHttp {

    private OkHttpClient okHttpClient = null;
    private Map<String, String> queryParamsMap = new HashMap<>();
    private Map<String, String> headerParamsMap = new HashMap<>();
    private List<String> headerLinesList = new ArrayList<>();
    /**
     * 参数拦截器
     */
    public Interceptor addQueryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
           /* if (!queryParamsMap.containsKey("token")) {
                queryParamsMap.put("token", "");
            }*/
            return chain.proceed(injectParamsIntoUrl(chain.request()));
        }
    };
    private Context mContext;

    public FrameHttp(Context context) {
        this.mContext = context;
    }

    public OkHttpClient getHttp() {
        if (okHttpClient == null) {
            synchronized (FrameHttp.class) {
                okHttpClient = new OkHttpClient().newBuilder()
                        .addInterceptor(addQueryParameterInterceptor)
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();
            }
        }
        return okHttpClient;
    }

    /**
     * 添加公共参数 get or post
     */
    private Request injectParamsIntoUrl(Request original) {
        //Header添加参数
        Headers.Builder headerBuilder = original.headers().newBuilder();
        if (headerParamsMap.size() > 0) {
            Iterator iterator = headerParamsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (headerLinesList.size() > 0) {
            for (String line : headerLinesList) {
                headerBuilder.add(line);
            }
        }
        //Url添加参数
        HttpUrl.Builder httpUrlBuilder = original.url().newBuilder();
        if (queryParamsMap.size() > 0) {
            Iterator iterator = queryParamsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }
        original = original.newBuilder().url(httpUrlBuilder.build()).headers(headerBuilder.build()).build();
        return original;
    }
}
