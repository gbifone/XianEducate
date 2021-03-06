package com.kema.xian.xianeducate.model.http;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ����״̬
 * @author zhousf
 */
@StringDef({DownloadStatus.INIT,DownloadStatus.DOWNLOADING,DownloadStatus.PAUSE,DownloadStatus.COMPLETED})
@Retention(RetentionPolicy.SOURCE)
public @interface DownloadStatus {

    String INIT = "INIT";//��ʼ��״̬
    String DOWNLOADING = "DOWNLOADING";//��������״̬
    String PAUSE = "PAUSE";//��ͣ״̬
    String COMPLETED = "COMPLETED";//�������״̬

}