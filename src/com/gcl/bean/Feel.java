package com.gcl.bean;

import cn.bmob.v3.BmobObject;

public class Feel extends BmobObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9179827653399624704L;

	private String name;
	private Sex sex;
	private String content;
	private String phone;
	private String time;
	private int count;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
