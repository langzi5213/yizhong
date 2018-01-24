package com.waji.http;


import com.android.internal.util.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.content.Context;
import android.os.Environment;
import com.waji.local.SharedPrefer;


public class SendVideo {

	public static String serviceImagePath;
	private String imageFileName;
	Context context;
	private final OkHttpClient client = new OkHttpClient();

	public void send(Context context1, final int who, String info) {
		context = context1;
		String dir = Environment.getExternalStorageDirectory()
				+ "/Camera/"
				+ new SimpleDateFormat("yyyy—MM—dd").format(new Date())
						.toString() + "/";

		int number1 = SharedPrefer.ReadNumber(context);
		String number = null;
		if (who == 1) {
			number1 = number1 + 1;
			imageFileName = dir + number1 + ".jpg";
		}
		if (who == 2) {
			imageFileName =   serviceImagePath;

		}
		if (info.equals("1")) {
			number = number1 + "";
		} else {
			number = info;
		}

		 
		String address = SharedPrefer.ReadAddress(context);

		int battery = SharedPrefer.ReadBattery(context);
		int batterystate = SharedPrefer.ReadBatteryState(context);
		String id = SharedPrefer.ReadUsername(context);
		
		File file = new File(imageFileName);
		System.out.println("图片路径" + imageFileName);
		MultipartBody.Builder builder = new MultipartBody.Builder()
				.setType(MultipartBody.FORM);
		System.out.println("发送图片参数" + battery+address+batterystate+id);
		builder.addFormDataPart("jpg", number + ".jpg",
				RequestBody.create(MediaType.parse("image/png"), file));
		builder.addFormDataPart("mac", id);
		builder.addFormDataPart("loadNumber", number);
		builder.addFormDataPart("battery", battery+"");
	 
		//builder.addFormDataPart("isCharge", batterystate+"");
		 builder.addFormDataPart("address", address);
		MultipartBody requestBody = builder.build();
		Request request = new Request.Builder()
				.url("http://119.23.111.186:8080/deviceJpg/UploadFile")
				.post(requestBody).build();

		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("上传失败:" + e.getLocalizedMessage());

			}

			@Override
			public void onResponse(Call call, Response response)
					throws IOException {
				System.out.println("上传照片返回结果：" + response.body().string());

			}
		});
	}

}
