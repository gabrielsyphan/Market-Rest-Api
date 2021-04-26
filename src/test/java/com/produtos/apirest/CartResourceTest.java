package com.produtos.apirest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.produtos.apirest.models.Cart;
import com.produtos.apirest.models.Product;
import com.produtos.apirest.repository.ProductRepository;
import com.produtos.apirest.resource.CartResource;

@SpringBootTest
@AutoConfigureMockMvc
public class CartResourceTest {
	
	@TestConfiguration
	static class CartResourceTestConfiguration {
		
		@Bean
		public CartResource cartResource() {
			return new CartResource();
		}
	}
	
	@Autowired
	CartResource cartResource;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	ProductRepository productRepository;
	
	private int id = 22;
	
	@Test
	public void cartResourceTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/cart")).andExpect(status().isOk());
	}
	
	@Test
	public void cartResourceInsertItem() throws Exception {
		
		Cart cart = new Cart();
		cart.setProduct_id(this.id);
		cart.setQuantity(2);
		cart.setTotal_value(0);
		cart.setDiscount(0);
		
		this.mockMvc.perform(put("/api/cart")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void cartResourceTestUpdate() throws Exception {
		
		Cart cart = new Cart();
		cart.setProduct_id(this.id);
		cart.setQuantity(4);
		cart.setTotal_value(0);
		cart.setDiscount(0);
		
		this.mockMvc.perform(put("/api/product")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteResourceTestUpdate() throws Exception {
		
		Cart cart = new Cart();
		cart.setProduct_id(this.id);
		
		this.mockMvc.perform(put("/api/product")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(cart)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void couponApplyTest() {
		
		Cart cart = new Cart();
		cart.setProduct_id(this.id);
		cart.setQuantity(3);
		cart.setTotal_value(6300);
		cart.setDiscount(0);
		
		Product product = productRepository.findByid(this.id);
		
		Assertions.assertEquals(cartResource.couponApply(cart, product).getDiscount(), 50);
	}
}
