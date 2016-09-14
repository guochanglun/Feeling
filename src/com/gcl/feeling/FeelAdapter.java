package com.gcl.feeling;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcl.bean.Feel;
import com.gcl.bean.Sex;
import com.gcl.util.MyCache;

public class FeelAdapter extends BaseAdapter {
	private Context context;
	private List<Feel> feels;

	public FeelAdapter(Context context, List<Feel> arrayList) {
		this.context = context;
		this.feels = arrayList;
	}

	@Override
	public int getCount() {
		return feels.size();
	}

	@Override
	public Object getItem(int arg0) {
		return feels.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		ViewHolder holder = null;
		if (view == null) {
			view = LayoutInflater.from(context)
					.inflate(R.layout.msg_item, null);
			holder = new ViewHolder();
			holder.avatar = (ImageView) view.findViewById(R.id.avatar);
			holder.name = (TextView) view.findViewById(R.id.name);
			holder.time = (TextView) view.findViewById(R.id.time);
			holder.content = (TextView) view.findViewById(R.id.content);
			holder.phone = (TextView) view.findViewById(R.id.phone);
			holder.count = (TextView) view.findViewById(R.id.count);
			holder.zan = (ImageView) view.findViewById(R.id.zan);

			holder.zan.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					String tag = (String) arg0.getTag();
					if(tag == null || !tag.equals("zan")){
						((ImageView)arg0).setImageBitmap(MyCache.getBtmap(R.drawable.zan_ok));
						arg0.setTag("zan");
					} else {
						((ImageView)arg0).setImageBitmap(MyCache.getBtmap(R.drawable.zan));
						arg0.setTag(null);
					}
					
				}
			});
			view.setTag(holder);
		}

		holder = (ViewHolder) view.getTag();
		Feel feel = feels.get(arg0);

		if(feel.getSex() == Sex.MAN){
			holder.avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_man));
		}else {
			holder.avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_woman));
		}
		holder.name.setText(feel.getName());
		holder.time.setText(feel.getTime());
		holder.content.setText(feel.getContent());
		holder.phone.setText(feel.getPhone());
		holder.count.setText("浏览" + feel.getCount() + "次");
		return view;
	}

	class ViewHolder {
		ImageView avatar;
		TextView name;
		TextView time;
		TextView content;
		TextView phone;
		TextView count;
		ImageView zan;
	}

	public void setList(List<Feel> arg0) {
		this.feels = arg0;
	}

}
