package com.coforge.ars.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tbl_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@NotBlank(message = "Name is required")
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Enter a valid email")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 2, message = "Password must be at least 6 characters")
	@Column(nullable = false)
	private String password;

	@NotBlank(message = "Role is required")
	@Column(nullable = false)
	private String role;

	@NotNull(message = "Wallet Balance is required")
	@Min(value = 0, message = "Wallet Balance cannot be negative")
	@Column(nullable = false)
	private Double walletBalance;

	public User() {
	}

	public User(Long userId, String name, String email, String password, String role, Double walletBalance) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.walletBalance = walletBalance;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", walletBalance=" + walletBalance + "]";
	}

}