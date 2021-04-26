package com.produtos.apirest.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.produtos.apirest.models.User;
import com.produtos.apirest.repository.UserRepository;

@Repository
@Transactional
public class ImplementsUserDatailService implements UserDetailsService{
	
	@Autowired
	private UserRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = ur.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}
}
