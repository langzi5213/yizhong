package com.jiankong.local;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class GetPhone {

	public List<Map<String, Object>> getPhoneContacts(Context context) {
		Uri rawContactsUri = Uri
				.parse("content://com.android.contacts/raw_contacts");
		Uri dataUri = Uri.parse("content://com.android.contacts/data");
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Cursor rawContactsCursor = context.getContentResolver()
				.query(rawContactsUri, new String[] { "contact_id" }, null,
						null, null);
		 
		rawContactsCursor.moveToFirst();
		if (rawContactsCursor != null) {
			while (rawContactsCursor.moveToNext()) {
				String contactId = rawContactsCursor.getString(0);
				 
				if (contactId == null) {
					return null;
				}
				Cursor dataCursor = context.getContentResolver().query(dataUri,
						new String[] { "data1", "mimetype" }, "contact_id=?",
						new String[] { contactId }, null);
//				System.out.println("得到的手机号码dataCursor" + dataCursor);
				Map<String, Object> map = new HashMap<String, Object>();
				if (dataCursor != null) {
					while (dataCursor.moveToNext()) {
						String data1 = dataCursor.getString(0);
						String mimetype = dataCursor.getString(1);

//						System.out.println("得到的手机号码" + data1 + mimetype);
						if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
							map.put("phone", data1);
						} else if ("vnd.android.cursor.item/name"
								.equals(mimetype)) {
							map.put("username", data1);
						}
					}
					dataCursor.close();
				}
				data.add(map);
			}
			rawContactsCursor.close();
		}
		return data;
	}

}
