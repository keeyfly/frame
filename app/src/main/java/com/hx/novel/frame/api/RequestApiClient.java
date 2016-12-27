package com.hx.novel.frame.api;

import com.hx.novel.frame.bean.FrameJson;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/13.
 */
public interface RequestApiClient {

    /**
     * get
     * 动态获取URL地址：@Path
     * 动态指定条件获取信息：@Query
     * 动态指定条件组获取信息：@QueryMap
     * <p>
     * post
     * 携带数据类型为对象时：@Body
     * 携带数据类型为表单键值对时：@Field
     * 单文件上传时：@Part
     * 多文件上传时：@PartMap
     */

    @GET("login.do")
    Observable<FrameJson> getLoginResult();
}
