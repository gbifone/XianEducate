package com.kema.xian.xianeducate.activity.base;

import android.app.Application;
import android.content.Context;
/**
 * 
 * @author hl.ding
 *
 */
public class ApplicationContext extends Application {
	public static Context mContext;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mContext = getApplicationContext();
	}
}
