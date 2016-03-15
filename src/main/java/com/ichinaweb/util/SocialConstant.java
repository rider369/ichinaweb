package com.ichinaweb.util;

public class SocialConstant {
	public static class ErrorDef {
		public final static String ERR_EXCEPTION = "服务器异常";
		public final static String ERR_PARAM = "参数异常";
	}

	// 访问网址
	public static class URL {
		public final static String Host = "http://social.kuaicha.info:7077/SDKInterface/";
		public final static String URL_GET_SOCIAL_AUTHID = Host + "getSocialAuthID.action";// 获取授权码
		public final static String URL_SOCIAL_CITY_LIST = Host + "socialCityList.action";// 获取授权码
		public final static String URL_SOCIAL_CITY_INPUT = Host + "socialCityInput.action";// 获取授权码
		public final static String URL_SOCIAL_IDENTIFY_CODE = Host + "socialIdentifyCode.action";// 获取授权码
		public final static String URL_SOCIAL_USER_DETAIL = Host + "socialUserDetail.action";// 获取授权码
		public final static String URL_SOCIAL_FINISH = Host + "socialFinish.action";// 获取授权码
	}

	// 参数名称
	public static class PARAM {
		public final static String APPKEY = "appKey";
		public final static String APPNAME = "appName";
		public final static String AUTHID = "authId";
		public final static String SOCIALTOKEN = "socialToken";
		public final static String SIGN = "sign";
		public final static String CODEVERSION = "codeVersion";
		public final static String ERRORCODE = "ErrorCode";
		public final static String ERRORMSG = "ErrorMsg";
		public final static String CITYCODE = "cityCode";
		public final static String FORMS = "forms";
		public final static String AUTHIDTYPE = "authIDType";
		public final static String MD5KEY = "md5Key";
	}
}
