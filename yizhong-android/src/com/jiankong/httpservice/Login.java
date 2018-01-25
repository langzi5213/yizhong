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
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONObject;
import android.content.Context;

import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.utils.StreamUtils;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;

public class Login {

	public static String login(Context context, String username, String password)
			throws Exception {
		

		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(PublicString.LoginUrl);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("username", username));
		parameters.add(new BasicNameValuePair("password", password));
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"UTF-8");
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		int code = response.getStatusLine().getStatusCode();
		
		InputStream is = response.getEntity().getContent();

		String result = StreamUtils.readStream(is);
		JSONObject jsonObj = new JSONObject(result);
		
		String data = jsonObj.getString("data");
		String message = jsonObj.getString("message");
//		System.out.println("登录成功获取到的数据"+data);
//		System.out.println("登录成功获取到的数据"+result);
//		System.out.println("登录成功获取到的数据"+code);
		if (data!="null") {
			
			 
			JSONObject dataObj = new JSONObject(data);
			String token = dataObj.getString("token");
		//	System.out.println("得到的token     "+token);
			String loginUser = dataObj.getString("loginUser");
			JSONObject userObj = new JSONObject(loginUser);
			  String name = userObj.getString("name");
			
			SharedPrefer.SaveToken(context, "bearer "+token);
			 SharedPrefer.Savename(context, name);
//			System.out.println("登录成功获取到的数据"+result);
//			System.out.println("登录成功获取到的token数据"+token+name);
			
		} 
		return message;
	}
}
