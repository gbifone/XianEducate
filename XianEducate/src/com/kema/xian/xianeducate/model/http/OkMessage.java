package com.kema.xian.xianeducate.model.http;


import android.os.Message;

import java.io.Serializable;

/**
 * Handler信息体基类
 * @author zhousf
 */
public class OkMessage implements Serializable {

    public int what;

    public Message build(){
        Message msg = new Message();
        msg.what = this.what;
        msg.obj = this;
        return msg;
    }

}
