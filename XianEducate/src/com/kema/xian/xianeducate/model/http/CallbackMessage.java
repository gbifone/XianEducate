package com.kema.xian.xianeducate.model.http;


/**
 * 响应回调信息体
 * @author zhousf
 */
public class CallbackMessage extends OkMessage{

    public CallbackOk callback;
    public HttpInfo info;

    public CallbackMessage(int what, CallbackOk callback, HttpInfo info) {
        this.what = what;
        this.callback = callback;
        this.info = info;
    }


}