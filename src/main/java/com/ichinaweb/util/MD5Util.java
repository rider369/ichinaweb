package com.ichinaweb.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.ichinaweb.kit.StringKit;

public class MD5Util {

	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };

	public MD5Util() {
	}

	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToString(byte[] bByte) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bByte.length; i++) {
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}

	public static String GetMD5Code(String strObj) throws UnsupportedEncodingException {
		String resultString = null;
		try {
			resultString = new String(strObj.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			// md.digest() 
			resultString = byteToString(md.digest(strObj.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return resultString;
	}
	
	/**
	 * 获取MD5加密签名
	 * @param param
	 * @param md5Key
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getMD5Sign(Map<String, String> param, String md5Key) 
			throws UnsupportedEncodingException {
		String mapString = StringKit.mapToString(param);
		if (mapString.length() > 0) {
			mapString += "&" + SocialConstant.PARAM.MD5KEY + "=" + md5Key;
			return MD5Util.GetMD5Code(mapString);
		} 
		return "";
	}
}