package com.example.test.request;

import jakarta.validation.constraints.NotEmpty;

public class PasswordChangeRequestDto {
    @NotEmpty
    private String userName;

    @NotEmpty
    private String currentPassword;

    @NotEmpty
    private String newPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}