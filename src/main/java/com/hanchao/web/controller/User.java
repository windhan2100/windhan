package com.hanchao.web.controller;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * t_user表对应实体类
 * @author hanchao
 * 2012-02-24
 */
@Component
public class User {

	private int id;				//ID
	private String username;	//用户名
	private String password;	//用户密码
	private Date  regDate ;     //注册日期
	
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}