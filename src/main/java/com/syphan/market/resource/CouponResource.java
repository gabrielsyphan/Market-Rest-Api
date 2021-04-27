package com.syphan.market.resource;

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

import com.syphan.market.models.Coupon;
import com.syphan.market.repository.CouponRepository;

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
	@ApiOperation(value="List all coupons")
	public List<Coupon> listCoupon() {
		return couponRepository.findAll();
	}
	
    @GetMapping("/coupon/{id}")
    @ApiOperation(value="Returns a single coupon by the given id")
    public Coupon selectCoupon(@PathVariable(value="id") int id) {
        return couponRepository.findByid(id);
    }
	
    @PostMapping("/coupon")
    @ApiOperation(value="Register a new coupon")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }
    
    @DeleteMapping("/coupon")
    @ApiOperation("Delete a coupon")
    public void deleteCoupon(@RequestBody Coupon coupon) {
    	couponRepository.delete(coupon);
    }
    
    @PutMapping("/coupon")
    @ApiOperation("Updates a coupon data")
    public Coupon updateCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }
}
