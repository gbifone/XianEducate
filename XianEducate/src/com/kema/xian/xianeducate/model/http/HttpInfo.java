package com.kema.xian.xianeducate.model.http;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.TextUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kema.xian.xianeducate.model.bean.DownloadFileInfo;
import com.kema.xian.xianeducate.model.bean.UploadFileInfo;


/**
 * Http����ʵ����
 * @author zhousf
 */
@SuppressLint("NewApi")
public class HttpInfo {

    //**�����������**/
    private String url;//�����ַ
    private Map<String,String> params;//�������
    private List<UploadFileInfo> uploadFiles;//�ϴ��ļ�����
    private List<DownloadFileInfo> downloadFiles;//�����ļ�����

    //**��Ӧ���ز�������**/
    private int retCode;//������
    private String retDetail;//���ؽ��

    private Class<?> tag;

    public HttpInfo(Builder builder) {
        this.url = builder.url;
        this.params = builder.params;
        this.tag = builder.tag;
        this.uploadFiles = builder.uploadFiles;
        this.downloadFiles = builder.downloadFiles;
    }

    public static Builder Builder() {
        return new Builder();
    }


    public static final class Builder {

        private String url;
        private Map<String,String> params;
        private List<UploadFileInfo> uploadFiles;
        private List<DownloadFileInfo> downloadFiles;
        private Class<?> tag;


        public Builder() {
        }

        public HttpInfo build(Object object){
            setTag(object);
            return new HttpInfo(this);
        }

        public HttpInfo build(){
            return new HttpInfo(this);
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder addParams(Map<String, String> params) {
            if(null == params)
                return this;
            if(null == this.params){
                this.params = params;
            }else{
                this.params.putAll(params);
            }
            return this;
        }

        public Builder addParam(String key, String value){
            if(null == this.params)
                this.params = new HashMap<String,String>();
            if(!TextUtils.isEmpty(key))
                this.params.put(key,value);
            return this;
        }

        /**
         * �����ϴ��ļ�
         * @param interfaceParamName �ӿڲ�������
         * @param filePathWithName �ϴ����ļ�·���������ļ���
         */
        public Builder addUploadFile(String interfaceParamName, String filePathWithName) {
            addUploadFile(filePathWithName,interfaceParamName,null);
            return this;
        }

        /**
         * �����ϴ��ļ�
         * @param interfaceParamName �ӿڲ�������
         * @param filePathWithName �ϴ����ļ�·���������ļ���
         * @param progressCallback �ϴ����Ȼص��ӿ�
         */
        public Builder addUploadFile(String interfaceParamName, String filePathWithName, ProgressCallback progressCallback) {
            if(null == this.uploadFiles){
                this.uploadFiles = new ArrayList<UploadFileInfo>();
            }
            if(!TextUtils.isEmpty(filePathWithName)){
                this.uploadFiles.add(new UploadFileInfo(filePathWithName,interfaceParamName,progressCallback));
            }
            return this;
        }

        /**
         * �����ϴ��ļ�
         * @param url �ϴ��ļ��ӿڵ�ַ
         * @param interfaceParamName �ӿڲ�������
         * @param filePathWithName �ϴ����ļ�·���������ļ���
         * @param progressCallback �ϴ����Ȼص��ӿ�
         */
        public Builder addUploadFile(String url, String interfaceParamName, String filePathWithName, ProgressCallback progressCallback) {
            if(null == this.uploadFiles){
                this.uploadFiles = new ArrayList<UploadFileInfo>();
            }
            if(!TextUtils.isEmpty(filePathWithName)){
                this.uploadFiles.add(new UploadFileInfo(url,filePathWithName,interfaceParamName,progressCallback));
            }
            return this;
        }

        public Builder addUploadFiles(List<UploadFileInfo> uploadFiles){
            if(null == uploadFiles)
                return this;
            if(null == this.uploadFiles){
                this.uploadFiles = uploadFiles;
            }else{
                this.uploadFiles.addAll(uploadFiles);
            }
            return this;
        }

        /**
         * ���������ļ�
         * @param url �����ļ��������ַ
         * @param saveFileName �ļ��������ƣ���������չ��
         */
        public Builder addDownloadFile(String url,String saveFileName){
            addDownloadFile(url,null,saveFileName,null);
            return this;
        }

        /**
         * ���������ļ�
         * @param url �����ļ��������ַ
         * @param saveFileName �ļ��������ƣ���������չ��
         * @param progressCallback ���ؽ��Ȼص��ӿ�
         */
        public Builder addDownloadFile(String url,String saveFileName,ProgressCallback progressCallback){
            addDownloadFile(url,null,saveFileName,progressCallback);
            return this;
        }

        /**
         * ���������ļ�
         * @param url �����ļ��������ַ
         * @param saveFileDir �ļ�����Ŀ¼·��������������
         * @param saveFileName �ļ��������ƣ���������չ��
         * @param progressCallback ���ؽ��Ȼص��ӿ�
         */
        public Builder addDownloadFile(String url,String saveFileDir,String saveFileName,ProgressCallback progressCallback){
            if(null == this.downloadFiles){
                this.downloadFiles = new ArrayList<DownloadFileInfo>();
            }
            if(!TextUtils.isEmpty(url)){
                this.downloadFiles.add(new DownloadFileInfo(url,saveFileDir,saveFileName,progressCallback));
            }
            return this;
        }

        public Builder addDownloadFile(DownloadFileInfo downloadFile){
            if(null == downloadFile)
                return this;
            if(null == this.downloadFiles){
                this.downloadFiles = new ArrayList<DownloadFileInfo>();
            }
            this.downloadFiles.add(downloadFile);
            return this;
        }

        public Builder addDownloadFiles(List<DownloadFileInfo> downloadFiles){
            if(null == downloadFiles)
                return this;
            if(null == this.downloadFiles){
                this.downloadFiles = downloadFiles;
            }else {
                this.downloadFiles.addAll(downloadFiles);
            }
            return this;
        }

        public Builder setTag(Object object) {
            if(object instanceof Activity){
                Activity activity = (Activity) object;
                this.tag = activity.getClass();
            }
            if(object instanceof android.support.v4.app.Fragment){
                android.support.v4.app.Fragment fragment = (android.support.v4.app.Fragment) object;
                this.tag = fragment.getActivity().getClass();
            }
            if(object instanceof android.app.Fragment){
                android.app.Fragment fragment = (android.app.Fragment) object;
                this.tag = fragment.getActivity().getClass();
            }
            return this;
        }

    }


    //**���󷵻س�������**/
    final int NonNetwork = 1;
    @SuppressLint("NewApi")
	final String NonNetwork_Detail = "�����ж�";
    final int SUCCESS = 2;
    final String SUCCESS_Detail = "��������ɹ�";
    final int ProtocolException = 3;
    final String ProtocolException_Detail = "����Э�������Ƿ���ȷ";
    final int NoResult = 4;
    final String NoResult_Detail = "�޷���ȡ������Ϣ(�������ڲ�����)";
    final int CheckURL = 5;
    final String CheckURL_Detail = "���������ַ�Ƿ���ȷ";
    final int CheckNet = 6;
    final String CheckNet_Detail = "�������������Ƿ�����";
    final int ConnectionTimeOut = 7;
    final String ConnectionTimeOut_Detail = "���ӳ�ʱ";
    final int WriteAndReadTimeOut = 8;
    final String WriteAndReadTimeOut_Detail = "��д��ʱ";
    final int ConnectionInterruption = 9;
    final String ConnectionInterruption_Detail = "�����ж�";
    final int NetworkOnMainThreadException = 10;
    final String NetworkOnMainThreadException_Detail = "��������UI�߳��н����������";
    final int Message = 11;
    final String Message_Detail = "";

    public HttpInfo packInfo(int retCode, String retDetail){
        this.retCode = retCode;
        switch (retCode){
            case NonNetwork:
                this.retDetail = NonNetwork_Detail;
                break;
            case SUCCESS:
                this.retDetail = SUCCESS_Detail;
                break;
            case ProtocolException:
                this.retDetail = ProtocolException_Detail;
                break;
            case NoResult:
                this.retDetail = NoResult_Detail;
                break;
            case CheckURL:
                this.retDetail = CheckURL_Detail;
                break;
            case CheckNet:
                this.retDetail = CheckNet_Detail;
                break;
            case ConnectionTimeOut:
                this.retDetail = ConnectionTimeOut_Detail;
                break;
            case WriteAndReadTimeOut:
                this.retDetail = WriteAndReadTimeOut_Detail;
                break;
            case ConnectionInterruption:
                this.retDetail = ConnectionInterruption_Detail;
                break;
            case NetworkOnMainThreadException:
                this.retDetail = NetworkOnMainThreadException_Detail;
                break;
            case Message:
                this.retDetail = Message_Detail;
                break;
        }
        if(!TextUtils.isEmpty(retDetail)){
            this.retDetail = retDetail;
        }
        return this;
    }

    public boolean isSuccessful(){
        return this.retCode == SUCCESS;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRetDetail() {
        return retDetail;
    }

    public void setRetDetail(String retDetail) {
        this.retDetail = retDetail;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Class<?> getTag() {
        return tag;
    }

    public List<UploadFileInfo> getUploadFiles() {
        return uploadFiles;
    }

    public List<DownloadFileInfo> getDownloadFiles() {
        return downloadFiles;
    }

}