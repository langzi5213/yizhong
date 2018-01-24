package com.waji.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import org.json.JSONObject;
import android.content.Context;
import com.waji.daomain.PublicString;
import com.waji.local.SharedPrefer;

public class SendImage {

	public static String serviceImagePath;
	public static String serviceVideoPath;

	Context context;
	private final OkHttpClient client = new OkHttpClient();

	public String send(Context context1, String info) throws Exception {
		String message = null;
		context = context1;
		int number1 = SharedPrefer.ReadNumber(context);
		String number = null;

		if (info.equals("1")) {
			number = number1 + "";
		} else {
			number = info;
		}

		String address = SharedPrefer.ReadAddress(context);

		int battery = SharedPrefer.ReadBattery(context);
		int batterystate = SharedPrefer.ReadBatteryState(context);
		String id = SharedPrefer.ReadUsername(context);

		File imagefile = new File(serviceImagePath);
		// File videofile = new File(serviceVideoPath);
		System.out.println("图片路径" + serviceImagePath);
		System.out.println("视频路径" + serviceVideoPath);
		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM);
		System.out.println("发送图片参数" + battery + address + batterystate + id);
		builder.addFormDataPart("jpg", number + ".jpg",
				RequestBody.create(MediaType.parse("image/png"), imagefile));

		// builder.addFormDataPart("video", number + ".jpg",
		// RequestBody.create(MediaType.parse("image/png"), videofile));
		builder.addFormDataPart("mac", id);
		builder.addFormDataPart("loadNumber", number);
		builder.addFormDataPart("battery", battery + "");

		builder.addFormDataPart("isCharge", batterystate + "");
		builder.addFormDataPart("address", address);
		MultipartBody requestBody = builder.build();
		Request request = new Request.Builder().url(PublicString.SendImgaeUrl)
				.post(requestBody).build();

		Response response = client.newCall(request).execute();
		String result = response.body().string();
		System.out.println("图片结果" + result);
		JSONObject jsonObj = new JSONObject(result);
		message = jsonObj.getString("message");

		return message;
	}

}
