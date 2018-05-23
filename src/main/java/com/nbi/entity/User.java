package com.nbi.entity;

public class User {

	private Integer id;// ID
	private String name;// 用户名
	private String psw;// 密码

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", psw=" + psw + "]";
	}

	public User(Integer id, String name, String psw) {
		super();
		this.id = id;
		this.name = name;
		this.psw = psw;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
