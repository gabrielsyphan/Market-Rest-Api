package com.produtos.apirest.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.produtos.apirest.models.Coupon;
import com.produtos.apirest.repository.CouponRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Category API Rest")
@CrossOrigin(origins="*")
public class CouponResource {

	@Autowired
	CouponRepository couponRepository;
	
	@GetMapping("/coupons")
	@ApiOperation(value="Lista todos os cupons")
	public List<Coupon> listCoupon() {
		return couponRepository.findAll();
	}
	
    @GetMapping("/coupon/{id}")
    @ApiOperation(value="Retorna um único cupom pelo id informado")
    public Coupon selectCoupon(@PathVariable(value="id") int id) {
        return couponRepository.findByid(id);
    }
	
    @PostMapping("/coupon")
    @ApiOperation(value="Cadastra um novo cupom")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }
    
    @DeleteMapping("/coupon")
    @ApiOperation("Deleta um cupom")
    public void deleteCoupon(@RequestBody Coupon coupon) {
    	couponRepository.delete(coupon);
    }
    
    @PutMapping("/coupon")
    @ApiOperation("Atualiza os dados de um cupom")
    public Coupon updateCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }
}
