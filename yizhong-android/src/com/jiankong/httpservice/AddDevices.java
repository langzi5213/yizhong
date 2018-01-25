package com.jiankong.httpservice;

import java.io.InputStream;
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
import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.utils.StreamUtils;

public class AddDevices {

	public static String add(Context context,String mac, String deviceName, String username,String phone)
			throws Exception {
		String token=SharedPrefer.ReadToken(context);
		System.out.println(token);
		String owner=SharedPrefer.Readid(context);
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(PublicString.AddDeviceUrl);
		httpPost.setHeader("token", token);  
		  
		
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
 
		parameters.add(new BasicNameValuePair("owner", owner)); 
		parameters.add(new BasicNameValuePair("mac", mac)); 
		//parameters.add(new BasicNameValuePair("username", username)); 
		parameters.add(new BasicNameValuePair("deviceName", deviceName));
	 

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"UTF-8");
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		int code = response.getStatusLine().getStatusCode();
		System.out.println("添加设备返回码"+code);
		 if (code == 200) {
			InputStream is = response.getEntity().getContent();
             String result = StreamUtils.readStream(is);
             System.out.println("添加设备返回结果"+result);
			 return "成功";
		} else {
			return null;
		}
	}
}
