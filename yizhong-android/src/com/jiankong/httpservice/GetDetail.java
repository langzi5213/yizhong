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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.jiankong.interfac.PublicString;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import com.jiankong.utils.FormatTime;
import com.jiankong.utils.StreamUtils;

public class GetDetail {

	public static String get(Context context, String mac) {
		String token = SharedPrefer.ReadToken(context);
		String message;
		try {
			HttpClient client = new DefaultHttpClient();
			// System.out.println("获取照片mac" + mac);
			HttpGet httpRequest = new HttpGet(PublicString.GetDetailUrl + mac);
			httpRequest.setHeader("token", token);

			HttpResponse response = client.execute(httpRequest);
			int code = response.getStatusLine().getStatusCode();
			// System.out.println("获取设备详情返回码:" + code);
			

			InputStream is = response.getEntity().getContent();
			String result = StreamUtils.readStream(is);
			  System.out.println("获取设备详情结果:" + result);
			JSONObject jsonObj = new JSONObject(result);
			message = jsonObj.getString("message");
			String data = jsonObj.getString("data");
			if(data=="null"){
				return "false";
			}
			if (code == 200) {
				JSONObject dataObj = new JSONObject(data);

				String records = dataObj.getString("records");
				JSONArray array = new JSONArray(records);

				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);

					String address = object.getString("address");
					String battery = object.getString("battery");
					String isCharge = object.getString("isCharge");
					String number = object.getString("loadNumber");
					String imageUrl = object.getString("mark");
					long createTime = object.getLong("createTime");
					String stringdate = FormatTime.formatTime(createTime);
					// System.out.println("我的状态     ..." + number);
					// System.out.println("detail数据" + mac + number + battery
					// + isCharge + stringdate + address);
					String id = SharedPrefer.Readid(context);

					if (isCharge.equals("1")) {
						battery = "充电中   电量" + battery + "%";
					} else {
						battery = "未充电   电量" + battery + "%";
					}
					if (number.equals("-1")) {
						number = "签到";
					}
					if (number.equals("-2")) {
						number = "签退";
					}

					DetailsDao dd = new DetailsDao(context);
					dd.add(id, mac, number, battery, address, imageUrl, null,
							stringdate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return message;

	}

}
