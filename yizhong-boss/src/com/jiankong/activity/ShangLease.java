package com.jiankong.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jiankong.httpservice.LeaseSell;
import com.jiankong.local.SaveImage;
import com.jiankong.local.SharedPrefer;
import com.jiankong.utils.ByteBitmap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ShangLease extends Activity {

	private EditText et_brand, et_model, et_machine, et_vin, et_price,
			et_phone, et_phone1;
	private String brand, model, machine, vin, price, phone, phone1, path;
	private ImageButton ib;
	private byte[] imagedata;

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				String info = (String) msg.obj;
				Toast.makeText(getApplicationContext(), "发布商品成功", 0).show();
				break;
			case 0:
				Toast.makeText(getApplicationContext(), "连接网络失败...", 0).show();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shang_lease);

		et_brand = (EditText) findViewById(R.id.et_lease_brand);
		et_model = (EditText) findViewById(R.id.et_lease_model);
		et_machine = (EditText) findViewById(R.id.et_lease_machine);
		et_vin = (EditText) findViewById(R.id.et_lease_vin);
		et_price = (EditText) findViewById(R.id.et_lease_price);
		et_phone = (EditText) findViewById(R.id.et_lease_phone);
		et_phone1 = (EditText) findViewById(R.id.et_lease_phone1);

		ib = (ImageButton) findViewById(R.id.ib_sell);

		
		showImage();

	}

	public void shoose(View view) {
		savedata();
		startActivity(new Intent(this, PupWindow.class));
	}

	private void showImage() {
		
		readdata();
		Intent intent = getIntent();
		path = intent.getStringExtra("imagepath");
		System.out.println("图片地址" + path);
		if (path != null) {
			Bitmap bm = BitmapFactory.decodeFile(path);
			ib.setImageBitmap(bm);
		}
	}
	
	public void savedata(){
		brand = et_brand.getText().toString().trim();
		model = et_model.getText().toString().trim();
		machine = et_machine.getText().toString().trim();
		vin = et_vin.getText().toString().trim();
		price = et_price.getText().toString().trim();
		phone = et_phone.getText().toString().trim();
		phone1 = et_phone1.getText().toString().trim();
		
		try {
			SharedPrefer.SaveBrand(getApplicationContext(), brand);
			SharedPrefer.SaveModel(getApplicationContext(), model);
			SharedPrefer.SaveMachine(getApplicationContext(), machine);
			SharedPrefer.SaveVin(getApplicationContext(), vin);
			SharedPrefer.SavePrice(getApplicationContext(), price);
			SharedPrefer.SavePhone(getApplicationContext(), phone);
			SharedPrefer.SavePhone1(getApplicationContext(), phone1);
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	 }
	
	public void readdata(){
		brand=SharedPrefer.ReadBrand(getApplicationContext());
		model=SharedPrefer.ReadModel(getApplicationContext());
		machine=SharedPrefer.ReadMachine(getApplicationContext());
		vin=SharedPrefer.ReadVin(getApplicationContext());
		price=SharedPrefer.ReadPrice(getApplicationContext());
		phone=SharedPrefer.ReadPhone(getApplicationContext());
		phone=SharedPrefer.ReadPhone1(getApplicationContext());
		
		et_brand.setText(brand);
		et_model.setText(model);
		et_machine.setText(machine);
		et_vin.setText(vin);
		et_price.setText(price);
		et_phone.setText(phone);
		et_phone1.setText(phone1);
	}

	public boolean getimage() {
		savedata();
		System.out.println("数据"+brand+model+machine+vin+price+phone+phone1);
		if (path==null) {
			Toast.makeText(this, "请选择一张照片", 1).show();
			return false;
		} else {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(path);
				Bitmap bitmap = BitmapFactory.decodeStream(fis);
				ByteBitmap byt = new ByteBitmap();
				imagedata = byt.Bitmap2Bytes(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		  if (brand.length()==0 || model.length()==0 || machine.length()==0
				|| vin.length()==0 || price.length()==0 || phone.length()==0
				|| phone1.length()==0 || imagedata == null) {
			Toast.makeText(this, "请将信息填写完整", 1).show();
			return false;
		}  else if (phone.length() != 11 || phone1.length() != 11) {
			Toast.makeText(this, "请输入正确的手机号码", 1).show();
			return false;
		} else {
			return true;
		}
	}

	public void Sell(View view) {
		boolean image = getimage();
		if (image) {
			new Thread(new Runnable() {
				public void run() {
					try {

						String result = LeaseSell.sell(brand, model, machine,
								vin, price, phone, phone1, imagedata);
						Message msg = new Message();
						msg.what = 1;
						msg.obj = result;
						handler.sendMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
						Message msg = new Message();
						msg.what = 0;
						handler.sendMessage(msg);
					}
				}
			}).start();
		}
	}

	public void Lease(View view) {
		boolean image = getimage();
		if (image) {
			new Thread(new Runnable() {
				public void run() {
					try {
						String result = LeaseSell.lease(brand, model, machine,
								vin, price, phone, phone1, imagedata);
						Message msg = new Message();
						msg.what = 1;
						msg.obj = result;
						handler.sendMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
						Message msg = new Message();
						msg.what = 0;
						handler.sendMessage(msg);
					}
				}
			}).start();
		}
	}
	public void Toreturn(View view){
		finish();
	}
}
