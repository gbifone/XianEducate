package com.kema.xian.xianeducate.model.http;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;

import android.os.Message;
import android.util.Log;

import android.content.Context;

public class OkHttpUtil {
	private boolean showHttpLog = true;// �Ƿ���ʾHttp������־ Ĭ����ʾ
	private static final byte[] LOCKER = new byte[0];

	private static OkHttpClient okHttpClient;
	private static OkHttpUtil mInstance;
	private static final int POST = 1;
	private static final int GET = 2;
	private final String TAG = getClass().getSimpleName();
	private long timeStamp = System.currentTimeMillis();// ����ʱ���������ÿ�������ʶ

	/**
	 * ����
	 * 
	 * @return
	 */
	public static OkHttpUtil getInstance() {

		synchronized (LOCKER) {
			if (mInstance == null) {
				mInstance = new OkHttpUtil();
			}
		}
		return mInstance;

	}

	/**
	 * ����okHttpClient
	 */
	private OkHttpUtil() {
		okHttpClient = new OkHttpClient().newBuilder()
				.readTimeout(60, TimeUnit.SECONDS)
				.connectTimeout(60, TimeUnit.SECONDS)
				.writeTimeout(60, TimeUnit.MILLISECONDS)
				.addInterceptor(LOG_INTERCEPTOR).build();

	}

	/**
	 * �첽get����
	 * 
	 * @param info
	 *            ������Ϣ��
	 * @param callback
	 *            �ص��ӿ�
	 */
	public void getAsync(HttpInfo info, CallbackOk callbackOk) {
		doRequestAsync(info, GET, callbackOk, null);
	}

	/**
	 * �첽Post����
	 * 
	 * @param info
	 *            ������Ϣ��
	 * @param callback
	 *            �ص��ӿ�
	 */
	public void PostAsync(HttpInfo info, CallbackOk callbackOk) {
		doRequestAsync(info, POST, callbackOk, null);
	}

	/**
	 * �첽����
	 * 
	 * @param info
	 *            ������Ϣ��
	 * @param method
	 *            ���󷽷�
	 * @param callback
	 *            �ص��ӿ�
	 */
	private void doRequestAsync(final HttpInfo info, int method,
			final CallbackOk callbackOk, Request request) {

		if (callbackOk == null)
			throw new NullPointerException(
					"CallbackOk is null that not allowed");

		Call call = okHttpClient.newCall(request == null ? fetchRequest(info,
				method) : request);

		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				showLog(e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				// ���̻߳ص�

				Message msg = new CallbackMessage(
						OkMainHandler.RESPONSE_CALLBACK, callbackOk,
						dealResponse(info, response, call, null)).build();
				OkMainHandler.getInstance().sendMessage(msg);
			}

		});

	}

	protected HttpInfo dealResponse(HttpInfo info, Response response,
			Call call, Object object) {
		// TODO Auto-generated method stub
		try {
			if (null != response) {
				if (response.isSuccessful() && null != response.body()) {
					if (null == object) {
						return retInfo(info, info.SUCCESS, response.body()
								.string());
					}
					// else{ //�����ļ�
					// return dealDownloadFile(info,object,response,call);
					// }
				} else {
					showLog("HttpStatus: " + response.code());
					if (response.code() == 404)// ����ҳ��·������
						return retInfo(info, info.CheckURL);
					if (response.code() == 416)// ������������Χ����
						return retInfo(info, info.Message, "����Http��������Χ����\n"
								+ response.body().string());
					if (response.code() == 500)// �������ڲ�����
						return retInfo(info, info.NoResult);
					if (response.code() == 502)// ��������
						return retInfo(info, info.CheckNet);
					if (response.code() == 504)// ���س�ʱ
						return retInfo(info, info.CheckNet);
				}
			}
			return retInfo(info, info.CheckURL);
		} catch (Exception e) {
			e.printStackTrace();
			return retInfo(info, info.NoResult);
		} finally {
			if (null != response)
				response.close();
		}

	}

	private HttpInfo retInfo(HttpInfo info, int code) {
		retInfo(info, code, null);
		return info;
	}

	private HttpInfo retInfo(HttpInfo info, int code, String resDetail) {
		info.packInfo(code, resDetail);
		showLog(">>>>>>>��������>>>>>>>>" + "Response: " + info.getRetDetail());
		return info;
	}

	/**
	 * ��װ��������
	 * 
	 * @param info
	 * @param method
	 * @return
	 */
	private Request fetchRequest(HttpInfo info, int method) {
		Request request;
		if (method == POST) {
			FormBody.Builder builder = new FormBody.Builder();
			if (null != info.getParams() && !info.getParams().isEmpty()) {
				StringBuilder log = new StringBuilder("PostParams:");
				String logInfo;
				for (String key : info.getParams().keySet()) {
					builder.add(key, info.getParams().get(key));
					logInfo = key + "=" + info.getParams().get(key) + ",";
					log.append(logInfo);
				}
				showLog(log.toString());
			}
			request = new Request.Builder().url(info.getUrl())
					.post(builder.build()).build();
		} else {
			StringBuilder params = new StringBuilder();
			params.append(info.getUrl());
			if (null != info.getParams() && !info.getParams().isEmpty()) {
				String logInfo;
				for (String name : info.getParams().keySet()) {
					logInfo = "&" + name + "=" + info.getParams().get(name);
					params.append(logInfo);
				}
			}
			request = new Request.Builder().url(params.toString()).get()
					.build();
		}
		return request;
	}

	/**
	 * ��ӡ��־
	 * 
	 * @param msg
	 *            ��־��Ϣ
	 */
	private void showLog(String msg) {
		if (this.showHttpLog)
			Log.i(TAG + "[" + timeStamp + "]", msg);
	}

	/**
	 * ��־������
	 */
	private Interceptor LOG_INTERCEPTOR = new Interceptor() {
		@Override
		public Response intercept(Chain chain) throws IOException {
			long startTime = System.currentTimeMillis();
			showLog(String.format(">>>>>��������>>>>>>" + "%s-URL: %s %n", chain
					.request().method(), chain.request().url()));
			Response res = chain.proceed(chain.request());
			long endTime = System.currentTimeMillis();
			showLog(String.format("CostTime: %.1fs",
					(endTime - startTime) / 1000.0));
			return res;
		}
	};

}
