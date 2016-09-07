package com.kema.xian.xianeducate.activity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;
import com.kema.xian.xianeducate.R;
import com.kema.xian.xianeducate.R.layout;
import com.kema.xian.xianeducate.activity.base.AppConfig;
import com.kema.xian.xianeducate.activity.base.BaseActivity;
import com.kema.xian.xianeducate.model.http.CallbackOk;
import com.kema.xian.xianeducate.model.http.HttpInfo;
import com.kema.xian.xianeducate.model.http.HttpInfo.Builder;
import com.kema.xian.xianeducate.model.http.OkHttpUtil;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.Intents;
import android.widget.DatePicker;

/**
 * Ê×Ò³
 * 
 * @author hl.ding
 * 
 */
public class MainActivity extends BaseActivity {

	@Override
	protected int setLayout() {
		// TODO Auto-generated method stub
		return R.layout.activity_main;
	}

	@Override
	protected void initUI() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initValue() {
		// TODO Auto-generated method stub

		// OkHttpUtil
		// .getInstance()
		// .getAsync(
		// HttpInfo.Builder()
		// .setUrl("https://api.github.com/gists/c2a7c39532239ff261be")
		// .build(), new CallbackOk() {
		//
		// @Override
		// public void onResponse(HttpInfo info)
		// throws IOException {
		// // TODO Auto-generated method stub
		// Gson gson = new Gson();
		// Gist gist = gson.fromJson(info.getRetDetail(),
		// Gist.class);
		// for (Map.Entry<String, GistFile> entry : gist.files
		// .entrySet()) {
		// System.out.println("111 " + entry.getKey());
		// System.out.println(entry.getValue().content);
		// }
		// }
		// });

		Builder builder = HttpInfo.Builder();
		Map<String, String> map = new HashMap<String, String>();

		builder.setUrl(AppConfig.JL_BASE_URL + "login");
		map.put("appCode", "JXS_APP");
		map.put("username", "138138138");
		map.put("password", "111111");
		builder.addParams(map);
		HttpInfo info = new HttpInfo(builder);
		OkHttpUtil.getInstance().PostAsync(info, new CallbackOk() {

			@Override
			public void onResponse(HttpInfo info) throws IOException {
				// TODO Auto-generated method stub
				System.err.println(info.getRetDetail());
			}
		});

	}

	@Override
	protected void initListener() {
		// TODO Auto-generated method stub

	}

	static class Gist {
		Map<String, GistFile> files;
	}

	static class GistFile {
		String content;
	}

}
