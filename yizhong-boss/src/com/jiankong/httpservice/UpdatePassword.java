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
import org.json.JSONObject;

import android.content.Context;

import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.utils.StreamUtils;

public class UpdatePassword {

	public static String update(Context context, String username,
			String oldPassword, String newPassword) throws Exception {
		String token = SharedPrefer.ReadToken(context);

		 HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(PublicString.UpdatePasswordUrl);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("username", username));
		parameters.add(new BasicNameValuePair("oldPassword", oldPassword));
		parameters.add(new BasicNameValuePair("newPassword", newPassword));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"UTF-8");
		httpPost.setEntity(entity);
		httpPost.setHeader("token", token);
		HttpResponse response = client.execute(httpPost);
//		int code = response.getStatusLine().getStatusCode();
//		System.out.println("修改密码获取到的返回码" + code);
		InputStream is = response.getEntity().getContent();
		String result = StreamUtils.readStream(is);
		System.out.println("修改密码获取到的数据" + result);
      
		JSONObject jsonObj = new JSONObject(result);
		
		String data = jsonObj.getString("data");
		String message = jsonObj.getString("message");

			return message;
		 
	}
}
