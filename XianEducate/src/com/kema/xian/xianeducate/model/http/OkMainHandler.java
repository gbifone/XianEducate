package com.kema.xian.xianeducate.model.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * ���߳�Handler
 * @author zhousf
 */
public class OkMainHandler extends Handler {

    private static OkMainHandler singleton;

    public static OkMainHandler getInstance(){
        if(null == singleton){
            synchronized (OkMainHandler.class){
                if(null == singleton)
                    singleton = new OkMainHandler();
            }
        }
        return singleton;
    }

    public OkMainHandler() {
        super(Looper.getMainLooper());
    }

    /**
     * ��������ص���ʶ
     */
    public static final int RESPONSE_CALLBACK = 0x01;

    /**
     * ���Ȼص���ʶ
     */
    public static final int PROGRESS_CALLBACK = 0x02;

    /**
     * �ϴ�����ص���ʶ
     */
    public static final int RESPONSE_UPLOAD_CALLBACK = 0x03;

    /**
     * ���ؽ���ص���ʶ
     */
    public static final int RESPONSE_DOWNLOAD_CALLBACK = 0x04;


    @Override
    public void handleMessage(Message msg) {
        final int what = msg.what;
        try {
            switch (what){
                case RESPONSE_CALLBACK:
                    CallbackMessage callMsg = (CallbackMessage) msg.obj;
                    if(null != callMsg.callback)
                        callMsg.callback.onResponse(callMsg.info);
                    break;
                case PROGRESS_CALLBACK:
//                    ProgressMessage proMsg = (ProgressMessage) msg.obj;
//                    if(null != proMsg.progressCallback)
//                        proMsg.progressCallback.onProgressMain(proMsg.percent,proMsg.bytesWritten,proMsg.contentLength,proMsg.done);
                    break;
                case RESPONSE_UPLOAD_CALLBACK:
//                    UploadMessage uploadMsg = (UploadMessage) msg.obj;
//                    if(null != uploadMsg.progressCallback)
//                        uploadMsg.progressCallback.onResponseMain(uploadMsg.filePath,uploadMsg.info);
                    break;
                case RESPONSE_DOWNLOAD_CALLBACK:
//                    DownloadMessage downloadMsg = (DownloadMessage) msg.obj;
//                    if(null != downloadMsg)
//                        downloadMsg.progressCallback.onResponseMain(downloadMsg.filePath,downloadMsg.info);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }catch (Exception e){

        }
    }



}

