package com.gcl.bean;


import cn.bmob.v3.BmobObject;

public class User extends BmobObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8972244959331758275L;
	
	private String name;
	private String pwd;
	private String sign;
	// 性别：man，woman
	private Sex sex;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", pwd=" + pwd + ", sign=" + sign
				+ ", sex=" + sex + "]";
	}
}
