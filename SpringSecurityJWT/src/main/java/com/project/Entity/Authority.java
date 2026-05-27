package com.project.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long authorityId;
	
	private String role;

	public Authority(long authorityId, String role) {
		this.authorityId = authorityId;
		this.role = role;
	}

	public Authority() {
	}

	public long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
