package com.gcl.feeling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bmob.v3.Bmob;

import com.gcl.util.BmobUtil;
import com.gcl.util.MyCache;

public class LoginActivity extends Activity {

	private ImageView avatar;
	private EditText name;
	private EditText pwd;
	private Button login;
	private TextView register;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		// 初始化LruCache
		MyCache.init(this);

		// 初始化Bomb
		Bmob.initialize(this, "09d385fd8fc9c6c8bc35a5598ba5c5df");

		// init
		avatar = (ImageView) findViewById(R.id.login_avatar);
		name = (EditText) findViewById(R.id.login_name);
		pwd = (EditText) findViewById(R.id.login_pwd);
		login = (Button) findViewById(R.id.login_btn);
		register = (TextView) findViewById(R.id.login_register);

		// listener
		name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				String nameString = name.getText().toString();
				if (nameString.trim().length() == 0) {
					return;
				}
				BmobUtil.with(LoginActivity.this).getUserByName(nameString, avatar);
			}
		});

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String nameString = name.getText().toString();
				String pwdString = pwd.getText().toString();

				if (nameString.trim().length() == 0 || pwdString.trim().length() == 0) {
					return;
				}

				BmobUtil.with(LoginActivity.this).login(nameString, pwdString);
			}

		});

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			}
		});
	}
}
