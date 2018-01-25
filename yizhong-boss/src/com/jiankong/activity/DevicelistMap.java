package com.jiankong.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.model.LatLng;
import com.jiankong.interfac.MyLeanCloudApp;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DetailsDao;
import com.jiankong.local.SQLite.LocationDao;
import com.jiankong.local.SQLite.StateDao;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.service.LocationService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class DevicelistMap extends Activity {
	private MapView mMapView = null;
	private BaiduMap mBaiduMap;
	private LocationService locService;
	private TextView tv_marker_name;
	private TextView tv_marker_id;
	private View view1;
	private Marker mMarker;
	private InfoWindow mInfoWindow;
	private Button dingwei;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devicelist_map);

		view1 = LayoutInflater.from(getApplicationContext()).inflate(
				R.layout.marker_work, null);
		tv_marker_name = (TextView) view1.findViewById(R.id.tv_marker_name);
		mMapView = (MapView) findViewById(R.id.bmapView);

		locService = ((MyLeanCloudApp) this.getApplication()).locationService;
		LocationClientOption mOption = locService
				.getDefaultLocationClientOption();
		mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
		mOption.setCoorType("bd09ll");
		locService.setLocationOption(mOption);
		locService.registerListener(listener);
		locService.start();

		dingwei = (Button) findViewById(R.id.dingwei);
		dingwei.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(DevicelistMap.this,
						DevicelistMap.class);
				startActivity(intent);
				finish();
			}
		});

		init();
		showMyLocation();
	}

	public void init() {

		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));

		mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(final Marker marker) {
				LatLng ll1 = marker.getPosition();
				double latitude1 = ll1.latitude;
				double longitude1 = ll1.longitude;
				String latitude = latitude1 + "";
				String longitude = longitude1 + "";
				System.out.println("点击地图坐标1" + longitude + latitude);
				try {
					GetInfo(latitude, longitude);
				} catch (Exception e) {
					e.printStackTrace();
					return true;
				}
				return true;
			}
		});

		double latitude = SharedPrefer.ReadLatitude(getApplicationContext());
		double longitude = SharedPrefer.ReadLongitude(getApplicationContext());
		LatLng point2 = new LatLng(latitude, longitude);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point2));
		showlist();
	}

	public void showlist() {
String id=SharedPrefer.Readid(getApplicationContext());
		BitmapDescriptor bitmap = null;
		LocationDao dao = new LocationDao(this);
		List<Map<String, Object>> statelist = dao.findAllData(id);
		int s = statelist.size();
		for (int i = 0; i < s; i++) {
			Map<String, Object> statemap = statelist.get(i);
			Object latitude1 = statemap.get("latitude");
			Object longitude1 = statemap.get("longitude");
			double latitude2 = Double.parseDouble(latitude1.toString());
			double longitude2 = Double.parseDouble(longitude1.toString());
			String devicename = (String) statemap.get("devicename");
			tv_marker_name.setText(devicename);
			// System.out.println("地图坐标0" + longitude2 + latitude2);
			bitmap = BitmapDescriptorFactory.fromView(view1);
			LatLng point1 = new LatLng(latitude2, longitude2);
			MarkerOptions option1 = new MarkerOptions().position(point1).icon(
					bitmap);
			mMarker = (Marker) (mBaiduMap.addOverlay(option1));
		}

	}

	public void GetInfo(String latitude, String longitude) throws Exception {
		LocationDao location = new LocationDao(getApplicationContext());
		DeviceDao user = new DeviceDao(getApplicationContext());
		StateDao statedao = new StateDao(getApplicationContext());
		DetailsDao details = new DetailsDao(getApplicationContext());
		String id = SharedPrefer.Readid(getApplicationContext());
		final String deviceId = location.findid(latitude, longitude);
		final String name = user.findname(id, deviceId);
		
		List<Map<String, Object>> numberlist = details.findNumber(id, deviceId);
		Map<String, Object> numbermap = numberlist.get(0);
		final String number = (String) numbermap.get("number");
		 
		List<Map<String, Object>> statelist = statedao.findState(id, deviceId);
		Map<String, Object> statemap = statelist.get(0);
		final String state = (String) statemap.get("state");
		final String date = (String) statemap.get("date");
		System.out.println("获取提示框数据" + name + number + deviceId + state + date);
		show(name, number, deviceId, state, date);
	}

	public void show(final String name, final String number, final String deviceId,
			final String state, final String date) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提醒");
		String info1 = "用户: " + name + "\n" + "装车数: " + number + "\n"
				+ "工作状态: " + state + "\n" + "更新日期: " + date;
		builder.setMessage(info1);
		builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.setNegativeButton("查看", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				loadMain(name, number, deviceId, state, date);
			}
		});
		builder.show();
	}

	public void loadMain(String name, String number, String deviceId, String state,
			String date) {
		Intent intent = new Intent(getApplicationContext(),
				DevicelistNumberlist.class);
		intent.putExtra("name", name);
		intent.putExtra("number", number);
		intent.putExtra("deviceId", deviceId);
		intent.putExtra("date", date);
		startActivity(intent);
	}

	BDLocationListener listener = new BDLocationListener() {

		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location != null
					&& (location.getLocType() == 161 || location.getLocType() == 66)) {
				double latitude = location.getLatitude();
				double longitude = location.getLongitude();
				// System.out.println("得到的自己位置" + latitude + "\n" + longitude);
				try {
					SharedPrefer
							.SaveLatitude(getApplicationContext(), latitude);
					SharedPrefer.SaveLongitude(getApplicationContext(),
							longitude);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void onConnectHotSpotMessage(String s, int i) {

		}
	};

	public void showMyLocation() {

		double latitude = SharedPrefer.ReadLatitude(getApplicationContext());
		double longitude = SharedPrefer.ReadLongitude(getApplicationContext());
		LatLng point = new LatLng(latitude, longitude);
		BitmapDescriptor bitmap = null;
		bitmap = BitmapDescriptorFactory.fromResource(R.drawable.stop);
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap);
		mBaiduMap.addOverlay(option);

	}
}
