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
	@ApiOperation(value="Lista os produtos do carrinho")
	public List<Cart> listCart() {
		return myCart;
	}
	
	@GetMapping("/cartValue")
	@ApiOperation(value="Retorna o valor total do carrinho")
	public Float cartValue() {
		totalCartValue = 0;
		
		// Calcula o valor total do carrinho
		for (Cart cart: myCart) {
			totalCartValue += cart.getTotal_value();
		}
		
		// Verifica a existência de cupons que são aplicáveis ao valor total do carrinho
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
	@ApiOperation(value="Cadastra um novo produto no carrinho")
	public Cart insertCartItem(@RequestBody Cart cart) throws Exception {
		
		// Checa a existência do produto pelo id informado
		Product product = productRepository.findByid(cart.getProduct_id());
		if (product == null) {
			throw new Exception("Product not found");
		}
		
		// Declaração das variáveis
		boolean sameProduct = false;
		boolean discountAplied = false;
		float discount = 0;
		
		// Calcula o valor total do produto inserido
		cart.setTotal_value(product.getValue() * cart.getQuantity());
		
		// Checa se já existe o produto no carrinho, se sim, irá atualizar os dados adicionando o que foi informado
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
		
		// Se o produto não foi atualizado ou se foi mas não havia cupom aplicado, realiza o calculo para aplicar um novo
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
		
		// Caso seja um produto novo, adiciona ele no carrinho
		if (sameProduct == false) {
			myCart.add(cart);
		}
		
		// Retorno do método
		return cart;
	}
	
    @PutMapping("/cart")
    @ApiOperation("Atualiza os dados do carrinho")
    public Cart updateCart(@RequestBody Cart cart) throws Exception {
    	
    	float discount = 0;
    	
		// Checa a existência do produto pelo id informado
		Product product = productRepository.findByid(cart.getProduct_id());
		if (product == null) {
			throw new Exception("Product not found");
		}
		
		// Verifica se existe produtos no carrinho e então atualiza eles
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
		
		// Retorno do método
        return cart;
    }
}
