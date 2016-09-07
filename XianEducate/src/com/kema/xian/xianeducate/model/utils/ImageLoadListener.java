
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
	 * 取消加载
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
	 * 加载完成
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
	 * 加载失败
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
	 * 加载开始
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
