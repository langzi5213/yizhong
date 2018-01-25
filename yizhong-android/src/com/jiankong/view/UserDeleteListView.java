package com.jiankong.view;

import com.jiankong.activity.WodeUser.MessageBean;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class UserDeleteListView extends ListView {

	private static final String TAG = "ListViewCompat";

	private SlideView mFocusedItemView;

	public UserDeleteListView(Context context) {
		super(context);
	}

	public UserDeleteListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UserDeleteListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
	}

	public void shrinkListItem(int position) {
		View item = getChildAt(position);

		if (item != null) {
			try {
				((SlideView) item).shrink();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			int x = (int) event.getX();
			int y = (int) event.getY();
			Log.d("listView x==y:", x + ":" + y);
			int position = pointToPosition(x, y);
			Log.e(TAG, "postion=" + position);
			if (position != INVALID_POSITION) {
				MessageBean data = (MessageBean) getItemAtPosition(position);
				mFocusedItemView = data.slideView;
			}
		}
		default:
			break;
		}

		if (mFocusedItemView != null) {
			mFocusedItemView.onRequireTouchEvent(event);
		}

		return super.onTouchEvent(event);

	}

}