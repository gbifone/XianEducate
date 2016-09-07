package com.kema.xian.xianeducate.model.utils;


import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.kema.xian.xianeducate.R;

public class ImageLoaderUtil {
	
	@SuppressLint("DefaultLocale") 
	public static void display(String uri,ImageView imageAware,ImageLoadListener listener){
	if(!StringUtils.isEmpty(uri)){
		if(!uri.toUpperCase().contains("HTTP")){
			uri = "file://" + uri;
		}
		if (listener != null) {
			com.nostra13.universalimageloader.core.ImageLoader.getInstance().
			displayImage(uri, imageAware, listener);
		}else {
			com.nostra13.universalimageloader.core.ImageLoader.getInstance().
			displayImage(uri, imageAware);
		}
	}else{
		imageAware.setImageResource(R.drawable.ic_launcher);
	}
	}
}