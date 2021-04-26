package com.produtos.apirest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.produtos.apirest.models.Role;
import com.produtos.apirest.models.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	private int id = 24;
	
	@Test
	public void userResourceTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/users")).andExpect(status().isOk());
	}
	
	@Test
	public void userTestGetById() throws Exception {
		
		mockMvc.perform(get("/api/user/"+ this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void userResourceTestSave() throws Exception {
		
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
		roleList.add(role);
		
		User user = new User();
		user.setId(this.id);
		user.setEmail("teste@teste.com");
		user.setPassword("test");
		user.setRole(roleList);
		
		this.mockMvc.perform(post("/api/user")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void userResourceTestUpdate() throws Exception {
		
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("ROLE_USER");
		roleList.add(role);
		
		User user = new User();
		user.setId(this.id);
		user.setEmail("teste@teste.com");
		user.setPassword("testUpdate");
		user.setRole(roleList);
		
		this.mockMvc.perform(put("/api/user")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)))
			.andExpect(status().isOk());
	}
}
