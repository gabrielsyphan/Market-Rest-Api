package com.syphan.market.resource;

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

import com.syphan.market.models.Cart;
import com.syphan.market.models.Coupon;
import com.syphan.market.models.Product;
import com.syphan.market.repository.CouponRepository;
import com.syphan.market.repository.ProductRepository;

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
			cart = couponApply(cart, product);
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
    	
		// Checks the existence of the product by the given id
		Product product = productRepository.findByid(cart.getProduct_id());
		if (product == null) {
			throw new Exception("Product not found");
		}
		
		// Checks products in the cart and then updates them
		if (myCart.size() > 0) {
			for (Cart shopCart: myCart) {
				if (shopCart.getProduct_id() == cart.getProduct_id()) {
					// If the item quantity is updated to 0, the item will be removed
					if (cart.getQuantity() == 0) {
						myCart.remove(shopCart);
					} else {
						shopCart.setQuantity(cart.getQuantity());
						shopCart.setTotal_value(product.getValue() * cart.getQuantity());
						shopCart.setDiscount(0);

						cart = shopCart;						
						cart = couponApply(cart, product);
					}
				}
			}	
		} else {
			throw new Exception("Cart is empty.");
		}
		
		// Cart return
        return cart;
    }
    
    // Method to apply coupons
    public Cart couponApply(Cart cart, Product product) {
		float discount = 0;
		float cartValue = cart.getTotal_value();
		
		// Get all coupons
		for(Coupon coupon: couponRepository.findAll()) {
			float aux = cart.getTotal_value();
			
			// Checks if the product category is the same as the coupon and if the minimum value has been reached and then applies
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
		
		// Cart return
		return cart;
    }
}
