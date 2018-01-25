package com.jiankong.activity;


import com.jiankong.local.SaveImage;
import com.jiankong.utils.ByteBitmap;

import android.app.Activity; 
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PupWindow extends Activity implements OnClickListener{

	private Button btn_take_photo, btn_pick_photo, btn_cancel;
	private LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_dialog);
		btn_take_photo = (Button) this.findViewById(R.id.btn_take_photo);
		btn_pick_photo = (Button) this.findViewById(R.id.btn_pick_photo);
		btn_cancel = (Button) this.findViewById(R.id.btn_cancel);
		
		layout=(LinearLayout)findViewById(R.id.pop_layout);
		 layout.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				 Toast.makeText(getApplicationContext(), "aa", 
						Toast.LENGTH_SHORT).show();	
			}
		});
		 btn_cancel.setOnClickListener(this);
		btn_pick_photo.setOnClickListener(this);
		btn_take_photo.setOnClickListener(this);
	}
	
	 @Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_take_photo:
			getImageFromCamera();
			break;
		case R.id.btn_pick_photo:
			getImageFromAlbum();
			break;
		case R.id.btn_cancel:	
			finish();
			break;
		default:
			break;
		}
		 
	}
	protected void getImageFromCamera() {
		Intent intent = new Intent();
		intent.setAction("android.media.action.IMAGE_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
		startActivityForResult(intent, 0);
	}

	protected void getImageFromAlbum() {
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		startActivityForResult(intent, 1);
	}

	 

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			Uri selectedImage = data.getData();
			String[] filePathColumns = { MediaStore.Images.Media.DATA };
			Cursor c = getContentResolver().query(selectedImage,
					filePathColumns, null, null, null);
			c.moveToFirst();
			int columnIndex = c.getColumnIndex(filePathColumns[0]);
			String imagePath = c.getString(columnIndex);
			Intent intent=new Intent(this,ShangLease.class);
			intent.putExtra("imagepath", imagePath);
			startActivity(intent);
			c.close();
		} else if (requestCode == 0) {
			Toast.makeText(this, "照片拍摄完毕", 0).show();
			Bundle bundle = data.getExtras();
			Bitmap bitmap1 = (Bitmap) bundle.get("data");
			
			ByteBitmap byt = new ByteBitmap();
			byte[] bytedata = byt.Bitmap2Bytes(bitmap1);
			SaveImage image = new SaveImage();
			String path = image.save(getApplicationContext(), bytedata);
			
			Intent intent=new Intent(this,ShangLease.class);
			intent.putExtra("imagepath", path);
			startActivity(intent);
			
		}
		
	}
}
