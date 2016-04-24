package com.ichinaweb.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.ichinaweb.kit.StringKit;

public class CalSign {
	
	public static String calcSign(Map items, String secret) throws UnsupportedEncodingException {
		SortedMap<String, String> sortedMap = new TreeMap<String, String>();
		sortedMap.putAll(items);
		String str = StringKit.sortedMapToString(sortedMap);
		str = secret + str + secret;
		System.out.println(str);
		return MD5Util.GetMD5Code(str);
	}
	
	public static void main(String[] args) {
		Map items = new HashMap<String, String>();
		items.put("p2", "v2");
		items.put("p3", "v3");
		items.put("p1", "v1");
		try {
			String sign = calcSign(items, "secret");
			System.out.println(sign);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
