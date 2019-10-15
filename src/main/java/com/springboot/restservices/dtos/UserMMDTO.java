package com.springboot.restservices.dtos;

public class UserMMDTO {
	
	private Long userid;
	private String email;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
