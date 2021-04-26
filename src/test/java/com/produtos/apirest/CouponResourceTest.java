package com.produtos.apirest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.produtos.apirest.models.Coupon;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CouponResourceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private int id = 20;
	
	@Test
	public void couponResourceTestGetAll() throws Exception {
		
		mockMvc.perform(get("/api/coupons")).andExpect(status().isOk());
	}
	
	@Test
	public void couponResourceTestGetById() throws Exception {
		
		mockMvc.perform(get("/api/coupon/"+ this.id)).andExpect(status().isOk());
	}
	
	@Test
	public void couponResourceTestSave() throws Exception {
		
		Coupon coupon = new Coupon();
		coupon.setId(this.id);
		coupon.setCode("TST10");
		coupon.setDiscount(10);
		coupon.setTotal_value(60);
		coupon.setDiscount_type_id(6);
		coupon.setCategory_id(1);
		
		this.mockMvc.perform(post("/api/coupon")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(coupon)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void couponResourceTestUpdate() throws Exception {
		
		Coupon coupon = new Coupon();
		coupon.setId(this.id);
		coupon.setCode("TST12");
		coupon.setDiscount(12);
		coupon.setTotal_value(60);
		coupon.setDiscount_type_id(6);
		coupon.setCategory_id(1);
		
		this.mockMvc.perform(put("/api/coupon")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(coupon)))
			.andExpect(status().isOk());
	}
	
	@Test
	public void couponResourceTestDelete() throws Exception {
		
		Coupon coupon = new Coupon();
		coupon.setId(this.id);
		
		this.mockMvc.perform(delete("/api/coupon")
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(coupon)))
			.andExpect(status().isOk());
	}
}
