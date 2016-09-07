
package com.kema.xian.xianeducate.model.utils;

import com.kema.xian.xianeducate.activity.base.ApplicationContext;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * To get package info.
 * @author hl.ding
 *
 */
public class PackageUtils {

	/**
	 * 获得APP名称
	 * @return
	 */
	public static String getApplicationName()
	{

		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = ApplicationContext.mContext.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(
					ApplicationContext.mContext.getPackageName(), 0);
		}
		catch (PackageManager.NameNotFoundException e) {
			applicationInfo = null;
		}
		String applicationName = (String) packageManager
				.getApplicationLabel(applicationInfo);
		return applicationName;
	}
	
	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	public static float getVersionCode()
	{

		float versionCode = 0f;
		try {
			// 获取软件版本号，对应AndroidManifest.xml下android:versionName
			versionCode = Float.parseFloat(ApplicationContext.mContext.getPackageManager()
					.getPackageInfo(ApplicationContext.mContext.getPackageName(), 0).versionName);
		}
		catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
}
