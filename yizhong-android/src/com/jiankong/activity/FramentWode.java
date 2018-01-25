package com.jiankong.activity;

import com.jiankong.local.SharedPrefer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FramentWode extends Fragment {

	private TextView name;
	private TextView id;

	private LinearLayout devices;
	private LinearLayout users;
	private LinearLayout publish;
	private LinearLayout collect;
	private LinearLayout safe;
	private LinearLayout setup;

	@Override
	public View onCreateView(android.view.LayoutInflater inflater,
			android.view.ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frament_wode, null);

		name = (TextView) view.findViewById(R.id.tv_wode_name);
		id = (TextView) view.findViewById(R.id.tv_wode_id);

		devices = (LinearLayout) view.findViewById(R.id.wode_devices);
		users = (LinearLayout) view.findViewById(R.id.wode_users);
		publish = (LinearLayout) view.findViewById(R.id.wode_publish);
		collect = (LinearLayout) view.findViewById(R.id.wode_collect);
		safe = (LinearLayout) view.findViewById(R.id.wode_safe);
		setup = (LinearLayout) view.findViewById(R.id.wode_setup);

		String name1 = SharedPrefer.Readname(getActivity());
		name.setText(name1);
		String id1 = SharedPrefer.Readid(getActivity());
		id.setText(id1);

		devices.setOnClickListener(listener);
		users.setOnClickListener(listener);
		publish.setOnClickListener(listener);
		collect.setOnClickListener(listener);
		safe.setOnClickListener(listener);
		setup.setOnClickListener(listener);

		return view;
	}

	OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			if (v == devices) {
				Intent intent = new Intent(getActivity(), WodeMydevice.class);
				startActivity(intent);
			} else if (v == users) {
				Intent intent = new Intent(getActivity(), WodeUser.class);
				startActivity(intent);
			} else if (v == publish) {
				Toast.makeText(getActivity(), "此功能开发中，敬请期待", 1).show();
//				Intent intent = new Intent(getActivity(), WodeUser.class);
//				startActivity(intent);
			} else if (v == collect) {
				Toast.makeText(getActivity(), "此功能开发中，敬请期待", 1).show();
//				Intent intent = new Intent(getActivity(), WodeUser.class);
//				startActivity(intent);
			} else if (v == safe) {
				Intent intent = new Intent(getActivity(), WodeModify.class);
				startActivity(intent);
			} else if (v == setup) {
				Intent intent = new Intent(getActivity(), WodeSetup.class);
				startActivity(intent);
			}
		}

	};

}