package com.hx.novel.frame.api;

import com.hx.novel.frame.bean.FrameJson;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/9/13.
 */
public interface RequestApiClient {

    /**
     *
     * GET
     * 动态获取URL地址：@Path 用于替换url中某个字段 @GET("group/{id}/users") @Path("id")
     * 动态指定条件获取信息：@Query   用于在url后拼接上参数
     * 动态指定条件组获取信息：@QueryMap(encoded = true) 对参数进行URLEncode
     * <p>
     *
     * POST
     * 携带数据类型为对象时：@Body 传入一个实体
     * 携带数据类型为表单键值对时：@Field  开头必须多加上@FormUrlEncoded这句注释，不然会报错。
     * 单文件上传时：@Part
     * 多文件上传时：@PartMap
     * 用于添加请求头部 @Header  或者 @Headers("Cache-Control: max-age=640000")类似这样的
     * 也可以设置多个 @Headers({
                                 "Accept: application",
                                 "User-Agent: Retrofit-Sample-App"
                             })
     */

    @GET("login.do")
    Observable<FrameJson> getLoginResult();
}
