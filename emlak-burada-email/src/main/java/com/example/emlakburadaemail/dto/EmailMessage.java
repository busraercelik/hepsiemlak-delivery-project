package com.example.emlakburadaemail.dto;

public class EmailMessage {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmailMessage [email=" + email + "]";
	}

}
