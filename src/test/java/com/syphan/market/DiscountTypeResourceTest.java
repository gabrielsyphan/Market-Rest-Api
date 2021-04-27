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
import com.syphan.market.models.DiscountType;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscountTypeResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private int id = 8;
	
	@Test
	public void discountResourceTypeTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/discounts")).andExpect(status().isOk());
	}
	
	@Test
	public void categoryTestGetById() throws Exception {
		
		mockMvc.perform(get("/api/discount/"+ this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void discountResourceTypeTestSave() throws Exception {
		
		DiscountType discountType = new DiscountType();
		discountType.setId(this.id);
		discountType.setName("Teste save");
		
		this.mockMvc.perform(post("/api/discount")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(discountType)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void discountResourceTypeTestUpdate() throws Exception {
		
		DiscountType discountType = new DiscountType();
		discountType.setId(this.id);
		discountType.setName("Teste update");
				
		this.mockMvc.perform(put("/api/discount")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(discountType)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void discountResourceTypeTestDelete() throws Exception {
		
		DiscountType discountType = new DiscountType();
		discountType.setId(this.id);
		
		this.mockMvc.perform(delete("/api/discount")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(discountType)))
			.andExpect(status().isOk());
	}
}
