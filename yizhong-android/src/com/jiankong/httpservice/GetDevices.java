package com.jiankong.httpservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.utils.StreamUtils;

public class GetDevices {

	public static Context context;
	static String id;

	public static String get(Context context1) throws Exception {
		context = context1;

		String token = SharedPrefer.ReadToken(context);
		// System.out.println("获取列表信息提交的token" + token);
		id = SharedPrefer.Readid(context);
		HttpClient client = new DefaultHttpClient();
		HttpGet httpRequest = new HttpGet(PublicString.GetDeviceUrl + id);

		client.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 5000);
		httpRequest.setHeader("token", token);

		HttpResponse response = client.execute(httpRequest);

		int code = response.getStatusLine().getStatusCode();
		//System.out.println("获取设备列表返回码:" + code);

		InputStream is = response.getEntity().getContent();
		String result = StreamUtils.readStream(is);
		//System.out.println("获取设备列表结果:" + result);

		JSONObject jsonObj = new JSONObject(result);
		String message = jsonObj.getString("message");
		String data = jsonObj.getString("data");

		if (code == 200) {
			String pj = ParseJson(data);
			return pj;
		} else {
			return message;
		}
	}

	public static String ParseJson(String data) throws Exception {

		JSONObject dataObj = new JSONObject(data);
		String records = dataObj.getString("records");
		JSONArray array = new JSONArray(records);
		String message = null;
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			String deviceName = object.getString("deviceName");
			String deviceId = object.getString("mac");
			// System.out.println("解析json" + deviceName + deviceId);
			DeviceDao det = new DeviceDao(context);
			det.add(id, deviceId, deviceName, null, null);

			String gd = GetDetail.get(context, deviceId);
			String gs = GetState.get(context, deviceId);
			String gl = GetLocation.get(context, deviceId);
			System.out.println("输出gs和gl" + gs + gl + gd);

			if (gs.equals("false") || gl.equals("false")) {
				System.out.println("返回空号");
				message = null;

			}else if (gs.equals(gl) && gl.equals(gd)) {
				 message = gl;
			} else {
				message = null;
			}
		}
		return message;

	}
}
