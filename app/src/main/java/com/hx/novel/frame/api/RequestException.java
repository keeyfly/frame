package com.hx.novel.frame.api;


/**
 * 常见错误码管理
 */
public class RequestException extends RuntimeException {

    public RequestException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public RequestException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 错误状态
     */
    private static String getApiExceptionMessage(int code) {
        String message = " ";
        switch (code) {
            case 404:
                message = "服务器没有找到任何匹配Request-URI的资源";
                break;
            case 400:
                message = "因为错误的语法导致服务器无法理解请求信息";
                break;
            case 405:
                message = "Request请求的方法不被允许通过指定的URI";
                break;
            case 500:
                message = "服务器遭遇异常阻止了当前请求的执行";
                break;
        }
        return message;
    }
}
