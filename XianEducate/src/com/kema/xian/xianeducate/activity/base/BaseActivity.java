package com.kema.xian.xianeducate.activity.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
/**
 * 基础activity
 * @author hl.ding
 *
 */
public abstract class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 状态栏透明
			// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// //导航栏透明（部分手机home键和返回键在屏幕内，那就是导航栏）

		}
		setContentView(setLayout());
		initUI();
		initValue();
		initListener();
	}

	/**
	 * 初始化布局
	 * 
	 * @return
	 */
	protected abstract int setLayout();

	/**
	 * 初始化控件
	 */
	protected abstract void initUI();

	/**
	 * 控件赋值
	 */
	protected abstract void initValue();

	/**
	 * 初始化监听
	 */
	protected abstract void initListener();
}
