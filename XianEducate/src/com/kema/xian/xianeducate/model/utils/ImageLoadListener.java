
package com.kema.xian.xianeducate.model.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.kema.xian.xianeducate.R;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoadListener implements ImageLoadingListener
{
	/**
	 * ȡ������
	 */
	@Override
	public void onLoadingCancelled(String url, View view)
	{

		// TODO Auto-generated method stub
		if (view != null)
		{
			((ImageView) view).setScaleType(ScaleType.FIT_XY);
			((ImageView) view)
					.setImageResource(R.drawable.ic_launcher);
		}

	}

	/**
	 * �������
	 */
	@Override
	public void onLoadingComplete(String url, View view, Bitmap bitmap)
	{
 
		// TODO Auto-generated method stub
		if (view != null)
		{
			((ImageView) view).setScaleType(ScaleType.FIT_XY);
			((ImageView) view).setImageBitmap(bitmap);
		}
	}

	/**
	 * ����ʧ��
	 */
	@Override
	public void onLoadingFailed(String url, View view, FailReason failReason)
	{
		// TODO Auto-generated method stub
		if (view != null)
		{
			((ImageView) view).setScaleType(ScaleType.FIT_XY);
			((ImageView) view)
					.setImageResource(R.drawable.ic_launcher);
		}

	}

	/**
	 * ���ؿ�ʼ
	 */
	@Override
	public void onLoadingStarted(String url, View view)
	{

		// TODO Auto-generated method stub
		if (view != null)
		{
			((ImageView) view).setScaleType(ScaleType.CENTER_CROP);
			((ImageView) view).setImageResource(R.drawable.ic_launcher);
		}

	}

}
