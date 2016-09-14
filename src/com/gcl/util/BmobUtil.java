package com.gcl.util;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.gcl.bean.Feel;
import com.gcl.bean.Sex;
import com.gcl.bean.User;
import com.gcl.feeling.FeelAdapter;
import com.gcl.feeling.MainActivity;
import com.gcl.feeling.R;

public class BmobUtil {

	private static Context context;

	/**
	 * 单例类
	 */
	private static BmobUtil util = new BmobUtil();

	public static BmobUtil with(Context context) {
		BmobUtil.context = context;
		return util;
	}

	public void getUserByName(String name, final ImageView avatar) {

		// 检测网络状态
		if (!NetUtil.isConn(context)) {
			Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
			return;
		}

		BmobQuery<User> query = new BmobQuery<User>("User");
		query.addWhereEqualTo("name", name);
		query.findObjects(new FindListener<User>() {

			@Override
			public void done(List<User> arg0, BmobException arg1) {
				if (arg1 == null && arg0.size() != 0) {
					User user = arg0.get(0);
					if (user.getSex() == Sex.MAN) {
						avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_man));
					} else {
						avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_woman));
					}
				}
			}
		});
	}

	public void saveUser(User user) {

		// 检测网络状态
		if (!NetUtil.isConn(context)) {
			Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
			return;
		}

		user.save(new SaveListener<String>() {

			@Override
			public void done(String arg0, BmobException arg1) {
				if (arg1 == null) {
					Toast.makeText(context, "注冊成功,请登录", Toast.LENGTH_SHORT).show();
					((Activity) context).finish();
				} else {
					Toast.makeText(context, "注冊失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void saveFeel(Feel feel) {

		// 检测网络状态
		if (!NetUtil.isConn(context)) {
			Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
			return;
		}

		feel.save(new SaveListener<String>() {

			@Override
			public void done(String arg0, BmobException arg1) {
				if (arg1 == null) {
					((Activity) context).finish();
				} else {
					Toast.makeText(context, "发表失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void getFeelList(final FeelAdapter adapter) {

		// 检测网络状态
		if (!NetUtil.isConn(context)) {
			Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
			return;
		}

		BmobQuery<Feel> query = new BmobQuery<Feel>("Feel");
		query.order("-time");
		query.findObjects(new FindListener<Feel>() {

			@Override
			public void done(List<Feel> arg0, BmobException arg1) {
				if (arg1 == null || arg0.size() != 0) {
					for (Feel feel : arg0) {
						Random random = new Random();
						feel.setCount(random.nextInt(60));
					}
					adapter.setList(arg0);
					adapter.notifyDataSetChanged();

				} else {
					Toast.makeText(context, "errro", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void login(String nameString, final String pwdString) {
		// 检测网络状态
		if (!NetUtil.isConn(context)) {
			Toast.makeText(context, "网络未连接", Toast.LENGTH_SHORT).show();
			return;
		}

		BmobQuery<User> query = new BmobQuery<User>("User");
		query.addWhereEqualTo("name", nameString);
		query.findObjects(new FindListener<User>() {

			@Override
			public void done(List<User> arg0, BmobException arg1) {
				if (arg1 == null && arg0.size() != 0) {
					if (pwdString.equals(arg0.get(0).getPwd())) {
						// 保存当前user的值
						Const.CUR_USER = arg0.get(0);
						context.startActivity(new Intent(context, MainActivity.class));
						((Activity) context).finish();
					} else {
						Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(context, "用戶不存在", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
