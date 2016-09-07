package com.kema.xian.xianeducate.activity.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
/**
 * ����activity
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
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // ״̬��͸��
			// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// //������͸���������ֻ�home���ͷ��ؼ�����Ļ�ڣ��Ǿ��ǵ�������

		}
		setContentView(setLayout());
		initUI();
		initValue();
		initListener();
	}

	/**
	 * ��ʼ������
	 * 
	 * @return
	 */
	protected abstract int setLayout();

	/**
	 * ��ʼ���ؼ�
	 */
	protected abstract void initUI();

	/**
	 * �ؼ���ֵ
	 */
	protected abstract void initValue();

	/**
	 * ��ʼ������
	 */
	protected abstract void initListener();
}
