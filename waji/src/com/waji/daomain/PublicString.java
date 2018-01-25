package com.waji.daomain;

import android.os.Environment;

public interface PublicString {
	String UpdateUrl = "http://119.23.111.186/update/wajiversion.aspx";
	String SendImgaeUrl="http://119.23.111.186:8080/deviceJpg/UploadFile";
	String SendLocationUrl="http://119.23.111.186:8080/deviceLocation/save";
	String SendStateUrl="http://119.23.111.186:8080/deviceState/save";
	String SavePath = Environment.getExternalStorageDirectory()+ "/waji.apk";
}
