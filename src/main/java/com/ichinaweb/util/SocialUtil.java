package com.ichinaweb.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class SocialUtil {
	

	/**
	 * 获取授权ID
	 * @param appKey
	 * @param appName
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode":0,"ErrorMsg":"成功","data":{"authId":"c792f61d9e844a9da2857d9b18cf61f4"}}
	 * @throws Exception
	 */
	public static Map getSocialAuthID(String appKey, String appName, String codeVersion, String md5Key) 
			throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(SocialConstant.PARAM.APPKEY, appKey);
		param.put(SocialConstant.PARAM.APPNAME, appName);
		param.put(SocialConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(SocialConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(SocialConstant.URL.URL_GET_SOCIAL_AUTHID, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 获取全国城市社保列表
	 * @param appKey
	 * @param appName
	 * @param authId
	 * @param codeVersion
	 * @param md5Key
	 * @return {“ErrorCode”:0,“ErrorMsg”:””,“data”:{"province": [//省份集合{"name": "福建",//省份名称"citys": [//城市集合{"code": "xiamen",//城市编号"name": "厦门"//城市名称}]}]}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map socialCityList(String appKey, String appName, String authId, String codeVersion, String md5Key) 
			throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(SocialConstant.PARAM.APPKEY, appKey);
		param.put(SocialConstant.PARAM.APPNAME, appName);
		param.put(SocialConstant.PARAM.AUTHID, authId);
		param.put(SocialConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(SocialConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(SocialConstant.URL.URL_SOCIAL_CITY_LIST, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 获取指定城市的输入配置和用户标记
	 * @param appKey
	 * @param appName
	 * @param authId
	 * @param cityCode
	 * @param codeVersion
	 * @param md5Key
	 * @return {“ErrorCode”：0,“ErrorMsg”：””“data”：{“socialUrl”： “http://www.xxx.com”，//社保网站地址“socialToken”：“xawEWkdfa123dsoaed”//用户标记“forms”:[ (点击查看备注){"name" : "idcard","desc" : "身份证号"//解释},{"name":"vcode",//有该字段，就需要验证码"desc":"图片验证码"}]}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map socialCityInput(String appKey, String appName, String authId, String cityCode, 
			String codeVersion, String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(SocialConstant.PARAM.APPKEY, appKey);
		param.put(SocialConstant.PARAM.APPNAME, appName);
		param.put(SocialConstant.PARAM.AUTHID, authId);
		param.put(SocialConstant.PARAM.CITYCODE, cityCode);
		param.put(SocialConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(SocialConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(SocialConstant.URL.URL_SOCIAL_CITY_INPUT, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 刷新验证码接口
	 * @param appKey
	 * @param appName
	 * @param authId
	 * @param socialToken
	 * @param codeVersion
	 * @param md5Key
	 * @return {“ErrorCode”：0,“ErrorMsg”：””“data”：{“imageURL”:http://www.sss.jpg//验证码URL}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map socialIdentifyCode(String appKey, String appName, String authId, String socialToken, 
			String codeVersion, String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(SocialConstant.PARAM.APPKEY, appKey);
		param.put(SocialConstant.PARAM.APPNAME, appName);
		param.put(SocialConstant.PARAM.AUTHID, authId);
		param.put(SocialConstant.PARAM.SOCIALTOKEN, socialToken);
		param.put(SocialConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(SocialConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(SocialConstant.URL.URL_SOCIAL_IDENTIFY_CODE, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 获取社保详情
	 * @param appKey
	 * @param appName
	 * @param authId
	 * @param socialToken
	 * @param forms
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode": 0,"ErrorMsg": "","data": {"socialDetail": {"idCard": "3504261983*****X",//身份证号"name": "张三",//姓名"currentCity": "厦门",//当前社保所在城市"status": "有效",//当前参保状态"company": "厦门拓客网络有限公司",//当前单位名称"firstHandin": "2005-08-01",//第一次缴交时间"lastSeriesMonths": "32",//最近连续缴交月数"stopTimes": "2",//中途社保断开次数"stopMonths": "59",//中断的总月数"lastHandinBase": "2793",//最近一次缴交基数金额"lastPayTime": "2015-02-01",//最近一次缴费时间"payMonths": "58",//缴费总月数"lastHandleinMonth": "934.37",//最近一个月缴交金额"limit": "36284.027",//五险总额"medicalInsuranceLimit": "10544.999",//医疗保险总额"endowmentInsuranceLimit": "228.426",//养老保险总额"accidentInsuranceLimit": "396.67014",//工伤保险总额"unemploymentInsuranceLimit": "74.30",//失业保险总额"maternityInsuranceLimit": "746.62036",//生育保险总额"averageWage": 0, //平均工资"minimumWage": 0//最低工资}}}
	 * @throws UnsupportedEncodingException
	 */
	public static Map socialUserDetail(String appKey, String appName, String authId, String socialToken, 
			String forms, String codeVersion, String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(SocialConstant.PARAM.APPKEY, appKey);
		param.put(SocialConstant.PARAM.APPNAME, appName);
		param.put(SocialConstant.PARAM.AUTHID, authId);
		param.put(SocialConstant.PARAM.SOCIALTOKEN, socialToken);
		param.put(SocialConstant.PARAM.FORMS, forms);
		param.put(SocialConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(SocialConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(SocialConstant.URL.URL_SOCIAL_USER_DETAIL, param);
		return JSON.parseObject(result, Map.class);
	}
	
	/**
	 * 结束指定用户请求（当调用socialCityInput接口之后的任意一个接口后，不想继续操作时，调用该接口）
	 * @param appKey
	 * @param appName
	 * @param authId
	 * @param socialToken
	 * @param codeVersion
	 * @param md5Key
	 * @return {"ErrorCode"：0,"ErrorMsg"：""}
	 * @throws UnsupportedEncodingException
	 */
	public static Map socialFinish(String appKey, String appName, String authId, String socialToken, 
			String codeVersion, String md5Key) throws UnsupportedEncodingException {
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put(SocialConstant.PARAM.APPKEY, appKey);
		param.put(SocialConstant.PARAM.APPNAME, appName);
		param.put(SocialConstant.PARAM.AUTHID, authId);
		param.put(SocialConstant.PARAM.SOCIALTOKEN, socialToken);
		param.put(SocialConstant.PARAM.SIGN, MD5Util.getMD5Sign(param, md5Key));
		param.put(SocialConstant.PARAM.CODEVERSION, codeVersion);
		String result = HttpUtil.post(SocialConstant.URL.URL_SOCIAL_FINISH, param);
		return JSON.parseObject(result, Map.class);
	}
	
	
	public static void main(String[] args) throws Exception {
		String appKey = "b0b2544bf0164ca9afec718845a48584";
		String appName = "com.ichinaweb";
		String codeVersion = "1.2";
		String md5Key = "3e4b65f2fa0e4c91be3a891ebd939e98";
		String authId = "c792f61d9e844a9da2857d9b18cf61f4";
		String cityCode = "xiamen";
		String socialToken = "418125b9c79a29153b20e7671a31b7bdffacbb9817e96d3df0e16ef56620ffafd8bee126d3ce1e8b266ace9a1c30c155a84a114c77fd035d9d881493019961fe1ce57e018bca93f22d17698dfe5ab9e229fa9de8ab376fae5a7e2131bd8572d42bed8d14b9be0a84925b17f10bc4933313795fa898813c61bc57ed9f6399e31f0194cb2907e52eff";
		String forms = "{\"idcard\":\"xxx\",\"password\":\"xxx\",\"vcode\":\"0957\"}";
		Map<String, String> map = new HashMap<String, String>();
//		map = getSocialAuthID(appKey, appName, codeVersion, md5Key);
//		map = socialCityList(appKey, appName, authId, codeVersion, md5Key);
//		map = socialCityInput(appKey, appName, authId, cityCode, codeVersion, md5Key);
//		map = socialIdentifyCode(appKey, appName, authId, socialToken, codeVersion, md5Key);
//		map = socialUserDetail(appKey, appName, authId, socialToken, forms, codeVersion, md5Key);
		map = socialFinish(appKey, appName, authId, socialToken, codeVersion, md5Key);
		
		System.out.println(map);
	}
}
