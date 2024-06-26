package com.example.test.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 454423488125934353L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(nullable = false, unique = true)
	 private String email;
	

	@Column(nullable = false, unique = true)
	private String userName;
	
	@Column
	private boolean isBlocked;
	
	@Column
	private LocalDateTime blockedDate;

	@Column
	private Integer numberOfAttempts;
	
	@Column
	private String password;
	

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public LocalDateTime getBlockedDate() {
		return blockedDate;
	}

	public void setBlockedDate(LocalDateTime blockedDate) {
		this.blockedDate = blockedDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(Integer numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	
	

}