package com.example.test.request;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserRegistrationRequestDto {
    @NotNull
    private String email;

    @NotEmpty(message = "Please enter a valid first name")
    private String userName;

    private boolean isBlocked;

    private LocalDateTime blockedTime;

    private Integer numberOfAttempts;

    @NotEmpty(message = "Please enter a valid password")
    private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public LocalDateTime getBlockedTime() {
		return blockedTime;
	}

	public void setBlockedTime(LocalDateTime blockedTime) {
		this.blockedTime = blockedTime;
	}

	public Integer getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(Integer numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}