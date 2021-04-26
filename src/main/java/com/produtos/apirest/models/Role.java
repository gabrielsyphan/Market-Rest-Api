package com.produtos.apirest.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String roleName;
	
	@ManyToMany(mappedBy = "role")
	private List<User> users;
	
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.roleName;
	}
}
