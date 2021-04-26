package com.produtos.apirest;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.produtos.apirest.models.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private int id = 24;
	
	@Test
	public void productResourceTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/products")).andExpect(status().isOk());
	}
	
	@Test
	public void productResourceTestGetById() throws Exception {
		
		mockMvc.perform(get("/api/product/"+ this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void productResourceTestSave() throws Exception {
		
		Product product = new Product();
		product.setId(this.id);
		product.setCategory_id(1);
		product.setName("Blue t-shirt");
		product.setValue((float) 119.99);

		this.mockMvc.perform(post("/api/product")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(product)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void productResourceTestUpdate() throws Exception {
		
		Product product = new Product();
		product.setId(this.id);
		product.setCategory_id(1);
		product.setName("Black underpant");
		product.setValue((float) 19.99);
		
		this.mockMvc.perform(put("/api/product")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void productResourceTestDelete() throws Exception {
		
		Product product = new Product();
		product.setId(this.id);
		
		this.mockMvc.perform(delete("/api/product")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
	}
}
