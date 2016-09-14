package com.gcl.feeling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gcl.bean.Sex;
import com.gcl.bean.User;
import com.gcl.util.BmobUtil;

public class RegisterActivity extends Activity {

	private EditText name;
	private EditText pwd;
	private EditText sign;
	private RadioGroup sex;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);

		name = (EditText) findViewById(R.id.re_name);
		pwd = (EditText) findViewById(R.id.re_pwd);
		sign = (EditText) findViewById(R.id.re_sign);
		sex = (RadioGroup) findViewById(R.id.re_sex);
		button = (Button) findViewById(R.id.re_btn);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String nameString = name.getText().toString();
				String pwdString = pwd.getText().toString();
				String signString = sign.getText().toString();
				RadioButton man = (RadioButton) sex.getChildAt(0);
				
				if(nameString.trim().length() == 0 || pwdString.trim().length() == 0){
					return;
				}
				
				User user = new User();
				user.setName(nameString);
				user.setPwd(pwdString);
				user.setSign(signString);
				if (man.isChecked()) {
					user.setSex(Sex.MAN);
				} else {
					user.setSex(Sex.WOMAN);
				}
				
				BmobUtil.with(RegisterActivity.this).saveUser(user);
			}
		});
	}

}
