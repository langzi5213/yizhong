package com.jiankong.httpservice;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.local.SQLite.LocationDao;
import com.jiankong.utils.FormatTime;
import com.jiankong.utils.StreamUtils;

public class GetLocation {
	public static String get(Context context, String mac)
			throws ClientProtocolException, IOException {
		String token = SharedPrefer.ReadToken(context);

		HttpClient client = new DefaultHttpClient();
		HttpGet httpRequest = new HttpGet(PublicString.GetLocationUrl + mac);
		httpRequest.setHeader("token", token);

		HttpResponse response = client.execute(httpRequest);
		int code = response.getStatusLine().getStatusCode();
		// System.out.println("获取设备地址返回码:" + code);
		String message;
		try {
			InputStream is = response.getEntity().getContent();
			String result = StreamUtils.readStream(is);
			//  System.out.println("获取设备地址结果:" + result);
			JSONObject jsonObj = new JSONObject(result);
			message = jsonObj.getString("message");
			String data = jsonObj.getString("data");
			if(data=="null"){
				return "false";
			}
			if (code == 200) {

				JSONObject dataObj = new JSONObject(data);
				String longitude = dataObj.getString("lng");
				String latitude = dataObj.getString("lat");
				
				long createTime = dataObj.getLong("createTime");
				String stringdate = FormatTime.formatTime(createTime);

			//	System.out.println("location数据" + mac + longitude + latitude
			//			+ stringdate );
				String id = SharedPrefer.Readid(context);
				DeviceDao user1 = new DeviceDao(context);
				String devicename = user1.findname(id, mac);
				LocationDao dd = new LocationDao(context);
				dd.add(id, mac, devicename, latitude, longitude,
						stringdate);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return message;

	}

	 
}
