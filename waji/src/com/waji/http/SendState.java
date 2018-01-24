package com.waji.http;

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
import com.waji.daomain.PublicString;
import com.waji.local.SharedPrefer;
import com.waji.utils.StreamUtils;

public class SendState {

	public  String sendState(final Context context) throws Exception {
		String id = SharedPrefer.ReadUsername(context);
		String state = SharedPrefer.ReadState(context);

		String path = PublicString.SendStateUrl;
		 HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(path);
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("deviceMac", id));
		parameters.add(new BasicNameValuePair("state", state));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters,
				"UTF-8");
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		int code = response.getStatusLine().getStatusCode();
		System.out.println("发送状态信息得到的返回吗" + code);
		 
		if (code == 200) {
			InputStream is = response.getEntity().getContent();

			String result = StreamUtils.readStream(is);
			System.out.println("发送状态信息得到的结果" + result);
			return result;
		} else {
			return null;
		}
	
	}

}
