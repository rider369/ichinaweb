package com.ichinaweb.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public static String post(String url, Map<String, String> map) {
		String result = "";

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(url);

		// 构建请求参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();

		// 添加参数
		if (map != null) {
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				String key = entry.getKey();
				String value = entry.getValue();
				list.add(new BasicNameValuePair(key, value));
			}

		}
		// 构建url加密实体，并以utf-8方式进行加密；
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, Consts.UTF_8);
		httppost.setEntity(entity);

		try {
			CloseableHttpResponse response = client.execute(httppost);

			if (response.getStatusLine().getStatusCode() == 200) {
				result = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
