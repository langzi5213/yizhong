package com.jiankong.httpservice;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.jiankong.utils.StreamUtils;

public class SubmitIdea {
	 
		 public static String login(String cookie) throws Exception {
				HttpURLConnection conn = null;
				try {
					final String url = "http://61.138.125.109/menu/top.jsp";
		            System.out.println("验证码1:" + cookie);
					conn = (HttpURLConnection) new URL(url).openConnection();
					conn.setRequestProperty("Cookie", cookie);
					conn.setRequestMethod("GET");
					conn.setConnectTimeout(10000);
					conn.setReadTimeout(10000);
					String cookie2 = conn.getHeaderField("set-cookie");
					System.out.println("验证码3:" + cookie2);
					int responseCode = conn.getResponseCode();
					if (responseCode == 200) {
						InputStream is = conn.getInputStream();
						String result = StreamUtils.readStream(is);
						return result;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

	}
 
