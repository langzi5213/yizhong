package com.jiankong.httpservice;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
 
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
 

import com.jiankong.utils.StreamUtils; 
 

public class LeaseSell {
	
	public static String sell(String brand,String model,String machine,String vin ,String price ,String phone, String phone1,byte[] imagedata) throws Exception {
		String path = "http://10.129.55.180:8080/admin/AddStudent";
	    String result= SendPath(path,brand, model, machine, vin, price, phone, phone1,  imagedata);
		return result;
	    }
 

	public static String lease(String brand,String model,String machine,String vin ,String price ,String phone, String phone1,byte[] iamgedata) throws Exception {
		 String path = "http://10.129.55.180:8080/admin/AddTeacher";
			String result= SendPath(path,brand, model, machine, vin, price, phone, phone1, iamgedata);
			return result;
	}
	
	
	 
		
	public static  String SendPath(String path,String brand,String model,String machine,String vin ,String price ,String phone, String phone1,byte[] iamgedata)throws Exception{
		HttpClient client = new DefaultHttpClient();
	     HttpPost httpPost = new  HttpPost(path);
	     List<NameValuePair> parameters=new ArrayList<NameValuePair>();
	     parameters.add(new BasicNameValuePair("brand", brand));
	     parameters.add(new BasicNameValuePair("model", model));
	     parameters.add(new BasicNameValuePair("machine",machine ));
	     parameters.add(new BasicNameValuePair("vin",vin ));
	     parameters.add(new BasicNameValuePair("price",price ));
	     parameters.add(new BasicNameValuePair("phone",phone ));
	     parameters.add(new BasicNameValuePair("phone1",phone1 ));
	     
	     UrlEncodedFormEntity entity =	new UrlEncodedFormEntity(parameters,"UTF-8");
	     httpPost.setEntity(entity);
	     HttpResponse response = client.execute(httpPost);
	     int code = response.getStatusLine().getStatusCode();
	     if (code == 200) {
		      InputStream is = response.getEntity().getContent();
	          String result = StreamUtils.readStream(is);
		      return result;
    	 }else{
		  return null;
	   }
}
}
