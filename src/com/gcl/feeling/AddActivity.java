package com.gcl.feeling;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gcl.bean.Feel;
import com.gcl.util.BmobUtil;
import com.gcl.util.Const;
import com.gcl.util.PhoneUtil;

public class AddActivity extends Activity {
	
	private ImageView back;
	private Button addButton;
	private EditText contentEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add);
		
		back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		addButton = (Button) findViewById(R.id.add_btn);
		contentEditText = (EditText) findViewById(R.id.add_content);
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String content = contentEditText.getText().toString();
				if(content.trim().length() == 0){
					return;
				}
				
				Feel feel = new Feel();
				feel.setContent(content);
				feel.setName(Const.CUR_USER.getName());
				feel.setSex(Const.CUR_USER.getSex());
				Date date = new Date();
				SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
				String dd = format.format(date);
				feel.setTime(dd);
				feel.setPhone(PhoneUtil.getPhoneName());
				feel.setCount(0);
				
				BmobUtil.with(AddActivity.this).saveFeel(feel);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

}
