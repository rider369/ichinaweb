package com.ichinaweb.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ejb.CreateException;

import com.alibaba.fastjson.JSON;
import com.ichinaweb.util.CreditConstant.URL;

public class CreditUtil {
	
	/**
	 * 获取授权码
	 * @param appKey
	 * @param appName
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode": 0,"ErrorMsg": "","data": {"authId": "QN94bInBg1eFrPDZ1k6u6MBGPsA24Qsy"//授权ID“deviceID”:设备ID}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map getCreditAuthID(String appKey, String appName, String codeVersion, String md5Key) 
			throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(CreditConstant.PARAM.APPKEY, appKey);
		param.put(CreditConstant.PARAM.APPNAME, appName);
		param.put(CreditConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(CreditConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(CreditConstant.URL.URL_GET_CREDIT_AUTHID, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 注册第一步
	 * @param appKey
	 * @param appName
	 * @param deviceId
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode":0,"ErrorMsg"："","data"：{"method":"checkIdentity""token"： 605a5dfc1bc995ffbed28e4c3be83a9d}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map creditSignInFirst(String appKey, String appName, String deviceId, String authId, 
			String codeVersion, String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(CreditConstant.PARAM.APPKEY, appKey);
		param.put(CreditConstant.PARAM.APPNAME, appName);
		param.put(CreditConstant.PARAM.DEVICEID, deviceId);
		param.put(CreditConstant.PARAM.AUTHID, authId);
		param.put(CreditConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(CreditConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(CreditConstant.URL.URL_CREDIT_SIGNIN_FIRST, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 获取验证码图片路径
	 * @param appKey
	 * @param appName
	 * @param deviceId
	 * @param authId
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode":0,"ErrorMsg"："","data"：{"imgUrl":"http://XXX.jpg"//图片访问链接 直接放入img标签}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map creditGetImgUrl(String appKey, String appName, String deviceId, String authId,
			String codeVersion, String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(CreditConstant.PARAM.APPKEY, appKey);
		param.put(CreditConstant.PARAM.APPNAME, appName);
		param.put(CreditConstant.PARAM.DEVICEID, deviceId);
		param.put(CreditConstant.PARAM.AUTHID, authId);
		param.put(CreditConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(CreditConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(CreditConstant.URL.URL_CREDIT_GET_IMG_URL, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 注册第二步
	 * @param appKey
	 * @param appName
	 * @param deviceId
	 * @param authId
	 * @param idcard
	 * @param method
	 * @param name
	 * @param verifyCode
	 * @param token
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode":0,"ErrorMsg"："","data"：{"errorType": 2, (2验证码失败 0成功) 3姓名错误 4 已注册过"method": "checkIdentity","token": "27ccb482ecb95f48ad38fe6205984a95"}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map creditSignInSecond(String appKey, String appName, String deviceId, String authId,
			String idcard, String method, String name, String verifyCode, String token, String codeVersion, 
			String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(CreditConstant.PARAM.APPKEY, appKey);
		param.put(CreditConstant.PARAM.APPNAME, appName);
		param.put(CreditConstant.PARAM.DEVICEID, deviceId);
		param.put(CreditConstant.PARAM.AUTHID, authId);
		param.put(CreditConstant.PARAM.IDCARD, idcard);
		param.put(CreditConstant.PARAM.METHOD, method);
		param.put(CreditConstant.PARAM.NAME, name);
		param.put(CreditConstant.PARAM.VERIFYCODE, verifyCode);
		param.put(CreditConstant.PARAM.TOKEN, token);
		param.put(CreditConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(CreditConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(CreditConstant.URL.URL_CREDIT_SIGNIN_SECOND, param);
		return JSON.parseObject(result, Map.class);
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		String appKey = "b0b2544bf0164ca9afec718845a48584";
		String appName = "com.ichinaweb";
		String codeVersion = "1.0";
		String md5Key = "3e4b65f2fa0e4c91be3a891ebd939e98";
		String authId = "008768d96dbb4c119d58a77c5ada0d07";
		String deviceId = "88e5f2290774468491de48abfdb4273b";
		String token = "cbdea47c176c9f489b6aa999263ca3f5";
		String method = "checkIdentity";
		String idcard = "32038119870705413X";
		String name = URLEncoder.encode("程辉", "UTF-8");
		String verifyCode = "s76vf7";
		
		Map<String, String> map = new HashMap<String, String>();
//		map = getCreditAuthID(appKey, appName, codeVersion, md5Key);
//		map = creditSignInFirst(appKey, appName, deviceId, authId, codeVersion, md5Key);
//		map = creditGetImgUrl(appKey, appName, deviceId, authId, codeVersion, md5Key);
		map = creditSignInSecond(appKey, appName, deviceId, authId, idcard, method, name, verifyCode, token, codeVersion, md5Key);
		
		System.out.println(map);
	}

}
