package com.waji.http;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.content.Context;
import com.waji.daomain.PublicString;
import com.waji.local.SharedPrefer;
import com.waji.utils.StreamUtils;

public class SendLocation {

	public  String saveLocation(final Context context) throws Exception {
		String id = SharedPrefer.ReadUsername(context);
		double longitude = SharedPrefer.ReadLongitude(context);
		double latitude = SharedPrefer.ReadLatitude(context);
		 
		String lng = String.valueOf(longitude);
		String lat = String.valueOf(latitude);

		String path = PublicString.SendLocationUrl;
		 HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(path);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("deviceMac", id));
		//parameters.add(new BasicNameValuePair("address", address));
		parameters.add(new BasicNameValuePair("lng", lng));
		parameters.add(new BasicNameValuePair("lat", lat));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"UTF-8");
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		int code = response.getStatusLine().getStatusCode();
		System.out.println("发送地址信息得到的返回吗" + code+lng+lat);
		
	 
		if (code == 200) {
			InputStream is = response.getEntity().getContent();
            String result = StreamUtils.readStream(is);
			System.out.println("发送地址信息得到的结果" + result);
			return result;
		} else {
			return null;
		}
	}

}
