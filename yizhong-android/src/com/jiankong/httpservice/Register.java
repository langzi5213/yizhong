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
 

import com.jiankong.interfac.PublicString;
import com.jiankong.utils.StreamUtils; 
 

public class Register {
	public static String register(String username,String password,String name) throws Exception {
		
		 HttpClient client = new DefaultHttpClient();
	     HttpPost httpPost = new  HttpPost(PublicString.RedisterUrl);
	     List<NameValuePair> parameters=new ArrayList<NameValuePair>();
	     parameters.add(new BasicNameValuePair("username", username));
	     parameters.add(new BasicNameValuePair("password", password));
	      parameters.add(new BasicNameValuePair("name",name ));
	     
	     UrlEncodedFormEntity entity =	new UrlEncodedFormEntity(parameters,"UTF-8");
	     httpPost.setEntity(entity);
	     HttpResponse response = client.execute(httpPost);
	     int code = response.getStatusLine().getStatusCode();
	     System.out.println("注册返回码"+code);
	     
	     if (code == 200) {
		      InputStream is = response.getEntity().getContent();
	          String result = StreamUtils.readStream(is);
	          System.out.println("注册返回数据"+code);
	          return result;
    	 }else{
		  return null;
	   }
}
}
