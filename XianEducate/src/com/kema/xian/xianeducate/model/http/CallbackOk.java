package com.kema.xian.xianeducate.model.http;



import java.io.IOException;

/**
 * �첽����ص��ӿ�
 * @author zhousf
 */
public interface CallbackOk {
    /**
     * �ûص��������л���UI�߳�
     */
    void onResponse(HttpInfo info) throws IOException;
}
