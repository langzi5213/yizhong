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
import com.jiankong.local.SQLite.StateDao;
import com.jiankong.utils.FormatTime;
import com.jiankong.utils.StreamUtils;

public class GetState {

	public static String get(Context context, String mac)
			throws ClientProtocolException, IOException {
		String token = SharedPrefer.ReadToken(context);
		HttpClient client = new DefaultHttpClient();
		HttpGet httpRequest = new HttpGet(PublicString.GetStateUrl + mac);
		httpRequest.setHeader("token", token);

		HttpResponse response = client.execute(httpRequest);
		int code = response.getStatusLine().getStatusCode();
	 System.out.println("获取设备状态返回码:" + code);
		InputStream is = response.getEntity().getContent();
		String result = StreamUtils.readStream(is);
		  System.out.println("获取设备状态结果:" + result);
		String message;
		try {
			JSONObject jsonObj = new JSONObject(result);
			message = jsonObj.getString("message");
			String data = jsonObj.getString("data");
			if(data=="null"){
				return "false";
			}
			if (code == 200) {
				JSONObject dataObj = new JSONObject(data);
				int intstate = dataObj.getInt("state");
				String state = null;
				if (intstate == 1) {
					state = "暂停";

				} else if (intstate == 2) {
					state = "怠速";

				} else if (intstate == 3) {
					state = "工作";
				}

			 

				long createTime = dataObj.getLong("createTime");
				String stringdate = FormatTime.formatTime(createTime);

 			//	System.out.println("STATE数据" + mac + state + stringdate);

				StateDao dao = new StateDao(context);
				String id = SharedPrefer.Readid(context);
				dao.add(id, mac, state, stringdate);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return message;
	}

}
