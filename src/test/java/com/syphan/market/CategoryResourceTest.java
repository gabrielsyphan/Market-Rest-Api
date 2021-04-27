package com.syphan.market;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syphan.market.models.Category;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private int id = 8;
	
	@Test
	public void categoryTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/categories")).andExpect(status().isOk());
	}
	
	@Test
	public void categoryTestGetById() throws Exception {
		
		mockMvc.perform(get("/api/category/"+ this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void categoryTestSave() throws Exception {
		
		Category category = new Category();
		category.setId(this.id);
		category.setName("Teste save");
		
		this.mockMvc.perform(post("/api/category")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(category)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void categoryTestUpdate() throws Exception {
		
		Category category = new Category();
		category.setId(this.id);
		category.setName("Teste updadte");
		
		this.mockMvc.perform(put("/api/category")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(category)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void categoryTestDelete() throws Exception {
		
		Category category = new Category();
		category.setId(this.id);
		
		this.mockMvc.perform(delete("/api/category")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(category)))
			.andExpect(status().isOk());
	}
}
