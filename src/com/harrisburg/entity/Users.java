package com.harrisburg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
		@NamedQuery(name="Users.findAll", query="select u from Users u ORDER BY u.fullName"),
		@NamedQuery(name="Users.findByEmail", query="select u from Users u where u.email= :email"),
		@NamedQuery(name="Users.countAll", query = "select Count(*) from Users u"),
		@NamedQuery(name="Users.checkLogin", query = "select u from Users u where u.email= :email and password = :password")
})
public class Users {
	private Integer userId;
	private String email;
	private String fullName;
	private String password;
	
	public Users() {
		
	}
	
	public Users(String email, String fullName, String password) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}
	
	public Users(Integer userId, String email, String fullName, String password) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}
	
	@Column(name="user_id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="full_name")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
