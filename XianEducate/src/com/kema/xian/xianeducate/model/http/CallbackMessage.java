package com.kema.xian.xianeducate.model.http;


/**
 * ��Ӧ�ص���Ϣ��
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