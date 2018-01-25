    package com.jiankong.view;  
      
    import java.util.ArrayList;  
import java.util.List;  

 
import com.jiankong.activity.R;
import com.jiankong.activity.WodeMydevice.MessageBean;
import com.jiankong.local.SharedPrefer;
import com.jiankong.local.SQLite.DeviceDao;
import com.jiankong.view.SlideView.OnSlideListener;
      
    import android.content.Context;  
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.ViewGroup;  
import android.widget.BaseAdapter;  
import android.widget.ImageView;  
import android.widget.TextView;  
  
      
    public class ShebeiDeleteAdapter extends BaseAdapter implements  
            OnSlideListener {  
        private static final String TAG = "SlideAdapter";  
      
        private Context mContext;  
        private LayoutInflater mInflater;  
        private List<MessageBean> mMessageItems = new ArrayList<MessageBean>();  
        // 上次显示了删除按钮的itemView；  
        private SlideView mLastSlideViewWithStatusOn;  
      
        public ShebeiDeleteAdapter(Context context) {  
            mContext = context;  
            mInflater = LayoutInflater.from(mContext);  
        }  
      
        public void setmMessageItems(List<MessageBean> mMessageItems) {  
            this.mMessageItems = mMessageItems;  
        }  
      
        @Override  
        public int getCount() {  
            if (mMessageItems == null) {  
                mMessageItems = new ArrayList<MessageBean>();  
            }  
            return mMessageItems.size();  
        }  
      
        @Override  
        public Object getItem(int position) {  
            return mMessageItems.get(position);  
        }  
      
        @Override  
        public long getItemId(int position) {  
            return position;  
        }  
      
        @Override  
        public View getView(final int position, View convertView, ViewGroup parent) {  
            ViewHolder holder;  
            // 自定义ItemView  
            SlideView slideView = (SlideView) convertView;  
            if (slideView == null) {  
                View itemView = mInflater.inflate(R.layout.wode_item_shebei, null);  
      
                slideView = new SlideView(mContext);  
                slideView.setContentView(itemView);  
                holder = new ViewHolder(slideView);  
                slideView.setOnSlideListener(this);  
                slideView.setTag(holder);  
            } else {  
                holder = (ViewHolder) slideView.getTag();  
            }  
            final MessageBean item = mMessageItems.get(position);  
            item.slideView = slideView;  
            item.slideView.shrink();  
      
            holder.icon.setImageResource(item.iconRes);  
            holder.devicename.setText(item.devicename);  
            holder.deiviceId.setText(item.deiviceId);
            holder.username.setText(item.username);
            holder.phone.setText(item.phone);
            holder.number.setText(item.number);
            holder.state.setText(item.state);
            
            holder.deleteHolder.setOnClickListener(new OnClickListener() {  
                @Override  
                public void onClick(View v) {  
                    mMessageItems.remove(position);  
                    DeviceDao user=new DeviceDao(mContext);
                    String id=SharedPrefer.Readid(mContext);
                    user.delete(id, item.deiviceId);
                    notifyDataSetChanged();  
                }  
            });  
      
            return slideView;  
        }  
      
        private static class ViewHolder {  
            public ImageView icon;  
            public TextView devicename;  
            public TextView deiviceId;  
            public TextView state;  
            public TextView number;  
            public TextView username;  
            public TextView phone;  
             public ViewGroup deleteHolder;  
      
            ViewHolder(View view) {  
                icon = (ImageView) view.findViewById(R.id.iv_shebei);  
                devicename = (TextView) view.findViewById(R.id.delete_shebei_devicesname);  
                deiviceId = (TextView) view.findViewById(R.id.delete_shebei_id);   
                state = (TextView) view.findViewById(R.id.delete_shebei_state);
                number = (TextView) view.findViewById(R.id.delete_shebei_number);
                username = (TextView) view.findViewById(R.id.delete_shebei_username);
                phone = (TextView) view.findViewById(R.id.delete_shebei_phone);
                
                deleteHolder = (ViewGroup) view.findViewById(R.id.holder);  
            }  
        }  
      
        /** 
         * 自定义view接口回调， 更新上一次正在滑动的View 
         */  
        @Override  
        public void onSlide(View view, int status) {  
            if (mLastSlideViewWithStatusOn != null  
                    && mLastSlideViewWithStatusOn != view) {  
                mLastSlideViewWithStatusOn.shrink();  
            }  
      
            if (status == SLIDE_STATUS_ON) {  
                mLastSlideViewWithStatusOn = (SlideView) view;  
            }  
        }  
    }  