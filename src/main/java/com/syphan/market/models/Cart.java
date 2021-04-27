package com.syphan.market.models;

public class Cart {

	private int product_id;
	private int quantity;
	private float total_value;
	private float discount;

	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int id_product) {
		this.product_id = id_product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public float getTotal_value() {
		return total_value;
	}
	
	public void setTotal_value(float total_value) {
		this.total_value = total_value;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
}
