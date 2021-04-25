package com.produtos.apirest.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Cart;
import com.produtos.apirest.models.Coupon;
import com.produtos.apirest.models.Product;
import com.produtos.apirest.repository.CouponRepository;
import com.produtos.apirest.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Cart API Rest")
@CrossOrigin(origins="*")
public class CartResource {

	List<Cart> myCart = new ArrayList<>();
	
	private float totalCartValue;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CouponRepository couponRepository;;
	
	@GetMapping("/cart")
	@ApiOperation(value="List the products in the cart")
	public List<Cart> listCart() {
		return myCart;
	}
	
	@GetMapping("/cartValue")
	@ApiOperation(value="Returns the total cart value")
	public Float cartValue() {
		totalCartValue = 0;
		
		// Calculates the total value of the cart
		for (Cart cart: myCart) {
			totalCartValue += cart.getTotal_value();
		}
		
		// Checks for coupons that are applicable to the total cart amount
		for(Coupon coupon: couponRepository.findAll()) {
			if (coupon.getCategory_id() == 0 && totalCartValue > coupon.getTotal_value()) {
				if (coupon.getDiscount_type_id() == 5) {
					totalCartValue -= (totalCartValue * coupon.getDiscount());
				} else {
					totalCartValue -= coupon.getDiscount();
				}
			}
		}
		
		return totalCartValue;
	}
	
	@PostMapping("/cart")
	@ApiOperation(value="Register a new product in the cart")
	public Cart insertCartItem(@RequestBody Cart cart) throws Exception {
		
		// Checks the existence of the product by the given id
		Product product = productRepository.findByid(cart.getProduct_id());
		if (product == null) {
			throw new Exception("Product not found");
		}
		
		// Declaration of variables
		boolean sameProduct = false;
		boolean discountAplied = false;
		float discount = 0;
		
		// Calculates the total value of the inserted product
		cart.setTotal_value(product.getValue() * cart.getQuantity());
		
		// Checks if the product already exists in the cart, if so, it will update the data by adding what was informed
		if (myCart.size() > 0) {
			for (Cart shopCart: myCart) {
				if (shopCart.getProduct_id() == cart.getProduct_id()) {
					shopCart.setQuantity(shopCart.getQuantity() + cart.getQuantity());
					shopCart.setTotal_value(shopCart.getTotal_value() + cart.getTotal_value());
					
					sameProduct = true;
					
					if (shopCart.getDiscount() != 0) {
						discountAplied = true;
					}
					
					cart = shopCart;
				}
			}	
		}
		
		// If the product has not been updated or is gone but there was no coupon applied, perform the calculation to apply a new one
		if (discountAplied == false) {
			float cartValue = cart.getTotal_value();
			
			for(Coupon coupon: couponRepository.findAll()) {
				float aux = cart.getTotal_value();
				
				if ((coupon.getCategory_id() == product.getCategory_id()) && aux > coupon.getTotal_value()) {
					if (coupon.getDiscount_type_id() == 5) {
						discount = aux - (aux * coupon.getDiscount());
						aux = discount;
					} else {
						discount = coupon.getDiscount();
						aux -= discount;
					}

					if (aux < cartValue) {
						cartValue = aux;
						cart.setDiscount(discount);
					}
					
					cart.setTotal_value(cartValue);
				}
			}
		}
		
		// If it is a new product, add it to the cart
		if (sameProduct == false) {
			myCart.add(cart);
		}
		
		// Cart return
		return cart;
	}
	
    @PutMapping("/cart")
    @ApiOperation("Updates the cart data")
    public Cart updateCart(@RequestBody Cart cart) throws Exception {
    	
    	float discount = 0;
    	
		// Checks the existence of the product by the given id
		Product product = productRepository.findByid(cart.getProduct_id());
		if (product == null) {
			throw new Exception("Product not found");
		}
		
		// Checks products in the cart and then updates them
		if (myCart.size() > 0) {
			for (Cart shopCart: myCart) {
				if (shopCart.getProduct_id() == cart.getProduct_id()) {
					shopCart.setQuantity(cart.getQuantity());
					shopCart.setTotal_value(product.getValue() * cart.getQuantity());
					shopCart.setDiscount(0);

					cart = shopCart;
					
					float cartValue = cart.getTotal_value();
					
					for(Coupon coupon: couponRepository.findAll()) {
						float aux = cart.getTotal_value();
						
						if ((coupon.getCategory_id() == product.getCategory_id()) && aux > coupon.getTotal_value()) {
							if (coupon.getDiscount_type_id() == 5) {
								discount = aux - (aux * coupon.getDiscount());
								aux = discount;
							} else {
								discount = coupon.getDiscount();
								aux -= discount;
							}

							if (aux < cartValue) {
								cartValue = aux;
								cart.setDiscount(discount);
							}
							
							cart.setTotal_value(cartValue);
						}
					}
				}
			}	
		} else {
			throw new Exception("Cart is empty.");
		}
		
		
		// Cart return
        return cart;
    }
}
