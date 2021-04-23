package com.produtos.apirest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="coupon")
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String code;
    private float discount;
    private float total_value;
    private int discount_type_id;
    private int category_id;
    
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public float getDiscount() {
		return discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public float getTotal_value() {
		return total_value;
	}
	
	public void setTotal_value(float total_value) {
		this.total_value = total_value;
	}
	
	public int getDiscount_type_id() {
		return discount_type_id;
	}
	
	public void setDiscount_type_id(int discount_type_id) {
		this.discount_type_id = discount_type_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
}
