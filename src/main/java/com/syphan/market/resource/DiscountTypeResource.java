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

import com.syphan.market.models.DiscountType;
import com.syphan.market.repository.DiscountTypeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="DiscountType API Rest")
@CrossOrigin(origins="*")
public class DiscountTypeResource {

	@Autowired
	DiscountTypeRepository discountTypeRepository;
	
	@GetMapping("/discounts")
	@ApiOperation(value="Lists all types of discounts")
	public List<DiscountType> listDiscountTypes() {
		return discountTypeRepository.findAll();
	}
	
    @GetMapping("/discount/{id}")
    @ApiOperation(value="Returns a single discount by the given id")
    public DiscountType selectCategory(@PathVariable(value="id") int id) {
        return discountTypeRepository.findByid(id);
    }
	
    @PostMapping("/discount")
    @ApiOperation(value="Register a new discount")
    public DiscountType createCategory(@RequestBody DiscountType discountType) {
        return discountTypeRepository.save(discountType);
    }
    
    @DeleteMapping("/discount")
    @ApiOperation("Deletes a discount")
    public void deleteCategory(@RequestBody DiscountType discountType) {
    	discountTypeRepository.delete(discountType);
    }
    
    @PutMapping("/discount")
    @ApiOperation("Updates a discount data")
    public DiscountType updateCategory(@RequestBody DiscountType discountType) {
        return discountTypeRepository.save(discountType);
    }
}
