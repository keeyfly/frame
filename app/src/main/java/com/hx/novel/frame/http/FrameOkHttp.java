package com.hx.novel.frame.http;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/30.
 */
public class FrameOkHttp {
/*
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static FrameHttp client = null;
    private Context mContext;

    public void setClient(Context context) {
        this.mContext = context;
        synchronized (FrameOkHttp.class) {
            if (client == null) {
                client = new FrameHttp(context);
            }
        }
    }

    *//**
     * get请求
     *
     * @param url
     * @throws IOException
     *//*

    public void getRequest(String url, final RequestHttpListener requestListener) throws IOException {
        Request request = new Request.Builder().url(url).build();
        if (!Constant.isNetworkConnected) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        if (client == null || requestListener == null) {
            return;
        }
        Call newCall = client.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = "";
                if (response.isSuccessful()) {
                    string = response.body().string();
                    requestListener.requestResult(string);
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

    *//**
     * post 键值对
     *
     * @param url
     * @param map
     * @return
     * @throws IOException
     *//*
    public void postRequest(String url, Map<String, String> map, final RequestHttpListener requestListener) throws IOException {
        FormBody.Builder params = new FormBody.Builder();
        for (String key : map.keySet()) {
            params.add(key, map.get(key));
        }
        Request request = new Request.Builder().url(url).post(params.build()).build();
        if (!Constant.isNetworkConnected) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        if (client == null || requestListener == null) {
            return;
        }
        Call newCall = client.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = "";
                if (response.isSuccessful()) {
                    string = response.body().string();
                    requestListener.requestResult(string);
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

    *//**
     * post Json 请求
     *
     * @param url
     * @param json
     * @throws IOException
     *//*

    public void postJsonRequest(String url, String json, final RequestHttpListener requestListener) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        if (!Constant.isNetworkConnected) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        if (client == null || requestListener == null) {
            return;
        }
        Call newCall = client.newCall(request);
        newCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = "";
                if (response.isSuccessful()) {
                    string = response.body().string();
                    requestListener.requestResult(string);
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

    *//**
     * 上传文件
     *
     * @param url
     * @param file
     * @throws Exception
     *//*
    public void uploadFile(String url, File file, final RequestHttpListener requestListener) throws Exception {


        Request request = new Request.Builder().url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        if (!Constant.isNetworkConnected) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        if (client == null || requestListener == null) {
            return;
        }
        Response response = client.newCall(request).execute();
        String string = "";
        if (response.isSuccessful()) {
            string = response.body().string();
            requestListener.requestResult(string);
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public interface RequestHttpListener {
        void requestResult(String string);

    }*/
}
