package com.waji.dao;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.waji.local.SaveQRcode;
import com.waji.local.SharedPrefer;
import com.waji.utils.ByteBitmap;

public class CreatQRcode {

	public Bitmap creatQRcode(Context context, String info) {

		String dir = Environment.getExternalStorageDirectory() + "/QRcode";
		String fileName1 = "/qrcode.jpg";
		Bitmap bm = BitmapFactory.decodeFile(dir + fileName1);
		if (bm != null) {
			return bm;
		} else {
			Bitmap bitmap = generateBitmap(info, 400, 400);

			ByteBitmap byt = new ByteBitmap();
			byte[] byt1 = byt.Bitmap2Bytes(bitmap);
			SaveQRcode save = new SaveQRcode();
			save.saveQRcode(context, byt1);
			return bitmap;
		}
	}

	private Bitmap generateBitmap(String info, int width, int height) {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
//		Map<EncodeHintType, String> hints = new HashMap<>();修改前
		
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix encode = qrCodeWriter.encode(info, BarcodeFormat.QR_CODE,
					width, height, hints);
			int[] pixels = new int[width * height];
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (encode.get(j, i)) {
						pixels[i * width + j] = 0x00000000;
					} else {
						pixels[i * width + j] = 0xffffffff;
					}
				}
			}
			return Bitmap.createBitmap(pixels, 0, width, width, height,
					Bitmap.Config.RGB_565);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}
}
