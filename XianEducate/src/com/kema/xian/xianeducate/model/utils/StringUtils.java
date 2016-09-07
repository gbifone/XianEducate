package com.kema.xian.xianeducate.model.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This is used to deal with strings.
 */

public class StringUtils {
	// 邮箱表达式
	private final static String EMAIL_REGULAR_EXPRESSION = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	// 手机号码表达式
	private final static String PHONE_REGULAR_EXPRESSION = "[1]\\d{10}";
	// 密码
	private final static String PHONE_REGULAR_PWD = "^[0-9a-zA-Z_]$";
	private final static String EMPTY = "";

	/**
	 * To determine whether a string is empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(Object input) {

		return input == null || input.toString().trim().length() == 0
				|| "null".equals(input.toString().trim());
	}

	/**
	 * To determine whether a string is email.
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {

		if (isEmpty(email)) {
			return false;
		}
		Pattern emailer = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
		return emailer.matcher(email).matches();
	}

	/**
	 * 是否为密码
	 * 
	 * @param pwd
	 * @return
	 */
	public static boolean isPwd(String pwd) {

		if (isEmpty(pwd)) {
			return false;
		}
		Pattern emailer = Pattern.compile(PHONE_REGULAR_PWD);
		return emailer.matcher(pwd).matches();
	}

	/**
	 * 是否包涵汉字
	 * 
	 * @param str
	 * @return
	 */

	public static boolean isContainChinese(String str) {
		// [u4e00-u9fa5]
		Pattern p = Pattern.compile("[^\\x00-\\xff]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * To determine whether a string is number of phone.
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhoneNumber(String phone) {

		if (isEmpty(phone)) {
			return false;
		}
		Pattern emailer = Pattern.compile(PHONE_REGULAR_EXPRESSION);
		return emailer.matcher(phone).matches();
	}

	/**
	 * Use the clearer named.
	 * 
	 * @param str
	 * @return
	 */
	public static String clean(Object str) {

		return isEmpty(str) ? EMPTY : str.toString();
	}

	/**
	 * encoded in utf-8.
	 * 
	 * @param str
	 * @return
	 */
	public static String utf8Encode(String str) {

		if (!isEmpty(str) && str.getBytes().length != str.length()) {
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"UnsupportedEncodingException occurred. ", e);
			}
		}
		return str;
	}

	/**
	 * str compare to md5Str.
	 * 
	 * @param org
	 * @param md5Str
	 * @return
	 */
	public static boolean md5StrCompare(Object org, String md5Str) {

		String md5_org = MD5Utils.getMD5Str(org);
		return md5_org.equals(md5Str);
	}

}
