package com.gcl.feeling;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gcl.bean.Feel;
import com.gcl.bean.Sex;
import com.gcl.util.BmobUtil;
import com.gcl.util.Const;
import com.gcl.util.MyCache;
import com.gcl.view.SlideMenu;

public class MainActivity extends Activity {

	private ImageView menu_avatar;
	private ImageView c_avatar;
	private TextView add;
	private Button logout;
	private ListView feelListView;
	private SlideMenu slideMenu;
	private TextView menu_name;
	private TextView menu_sign;
	private FeelAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		init_component();
		// 根据登录信息获取user数据

		menu_name.setText(Const.CUR_USER.getName());
		menu_sign.setText(Const.CUR_USER.getSign());
		if (Const.CUR_USER.getSex() == Sex.MAN) {
			menu_avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_man));
			c_avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_man));
		} else {
			menu_avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_woman));
			c_avatar.setImageBitmap(MyCache.getBtmap(R.drawable.avatar_woman));
		}

		c_avatar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				slideMenu.togger();
			}
		});

		// add listener
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				MainActivity.this.startActivity(new Intent(MainActivity.this, AddActivity.class));
			}
		});

		// logout listener
		logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, LoginActivity.class));
				MainActivity.this.finish();
			}
		});

		List<Feel> list = new ArrayList<Feel>();
		adapter = new FeelAdapter(MainActivity.this, list);
		BmobUtil.with(this).getFeelList(adapter);
		feelListView.setAdapter(adapter);

	}

	private void init_component() {
		slideMenu = (SlideMenu) findViewById(R.id.slideMenu);
		menu_avatar = (ImageView) findViewById(R.id.menu_avatar);
		c_avatar = (ImageView) findViewById(R.id.c_avatar);
		add = (TextView) findViewById(R.id.c_add);
		logout = (Button) findViewById(R.id.menu_logout);
		feelListView = (ListView) findViewById(R.id.c_feelList);
		menu_name = (TextView) findViewById(R.id.menu_name);
		menu_sign = (TextView) findViewById(R.id.menu_sign);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		BmobUtil.with(this).getFeelList(adapter);
	}
}
