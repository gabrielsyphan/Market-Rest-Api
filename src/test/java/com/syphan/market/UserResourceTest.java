package com.syphan.market;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.market.models.Role;
import com.syphan.market.models.User;

@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	private String email = "teste@teste.com";
	
	@Test
	public void userResourceTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/users")).andExpect(status().isOk());
	}
	
	@Test
	public void userTestGetById() throws Exception {
		
		mockMvc.perform(get("/api/user/"+ this.email)).andExpect(status().isOk());
	}
	
	@Test
	public void userResourceTestSave() throws Exception {
		
		List<Role> roleList = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("ROLE_ADMIN");
		roleList.add(role);
		
		User user = new User();
		user.setEmail(this.email);
		user.setPassword("test");
		user.setRoles(roleList);
		
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
		user.setEmail(this.email);
		user.setPassword("testUpdate");
		user.setRoles(roleList);
		
		this.mockMvc.perform(put("/api/user")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)))
			.andExpect(status().isOk());
	}
}
