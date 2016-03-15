package com.ichinaweb.util;

public class CreditConstant {
	public static class ErrorDef {
		public final static String ERR_EXCEPTION = "服务器异常";
		public final static String ERR_PARAM = "参数异常";
	}

	// 访问网址
	public static class URL {
		public final static String Host = "http://credit.talk007.com/SDKInterface/";
		public final static String URL_GET_CREDIT_AUTHID = Host + "getCreditAuthID.action";// 获取授权码
		public final static String URL_CREDIT_SIGNIN_FIRST = Host + "creditSignInFirst.action";
		public final static String URL_CREDIT_GET_IMG_URL = Host + "creditGetImgUrl.action";
		public final static String URL_CREDIT_SIGNIN_SECOND = Host + "creditSignInSecond.action";
		public final static String URL_CREDIT_GET_ACTIVATE_CODE = Host + "creditGetActivateCode.action";
		public final static String URL_CREDIT_SIGNIN_THIRD = Host + "creditSignInThird.action";
		public final static String URL_CREDIT_LOGIN = Host + "creditLogin.action";
		public final static String URL_CREDIT_THE_QUESTIONNAIRE = Host + "creditTheQuestionnaire.action";
		public final static String URL_CREDIT_SUBMIT_QUESTIONNAIRE = Host + "creditSubmitQuestionnaire.action";
		public final static String URL_CREDIT_GET_INFORMATION = Host + "creditGetInformation.action";
		public final static String URL_CREDIT_ANALYTICAL_REPORT = Host + "creditAnalyticalReport.action";
	}

	// 参数名称
	public static class PARAM {
		public final static String APPKEY = "appKey";
		public final static String APPNAME = "appName";
		public final static String AUTHID = "authId";
		public final static String SIGN = "sign";
		public final static String CODEVERSION = "codeVersion";
		public final static String DEVICEID = "deviceID";
		public final static String IDCARD = "idcard";
		public final static String METHOD = "method";
		public final static String NAME = "name";
		public final static String VERIFYCODE = "verifyCode";
		public final static String TOKEN = "token";
		public final static String MOBILETEL = "mobileTel";
		public final static String DATATIME = "dataTime";
		public final static String PASSWORD = "password";
		public final static String ANSWERRESULT = "answerResult";
		public final static String LOGINNAME = "loginName";
		public final static String LOGINTYPE = "loginType";
		public final static String ERRORCODE = "ErrorCode";
		public final static String ERRORMSG = "ErrorMsg";
		public final static String MD5KEY = "md5Key";
	}
}
