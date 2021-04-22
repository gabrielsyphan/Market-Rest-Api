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

import com.produtos.apirest.models.DiscountType;
import com.produtos.apirest.repository.DiscountTypeRepository;

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
	@ApiOperation(value="Lista todos os tipos de discontos")
	public List<DiscountType> listDiscountTypes() {
		return discountTypeRepository.findAll();
	}
	
    @GetMapping("/discount/{id}")
    @ApiOperation(value="Retorna um único disconto pelo id informado")
    public DiscountType selectCategory(@PathVariable(value="id") int id) {
        return discountTypeRepository.findByid(id);
    }
	
    @PostMapping("/discount")
    @ApiOperation(value="Cadastra um novo disconto")
    public DiscountType createCategory(@RequestBody DiscountType discountType) {
        return discountTypeRepository.save(discountType);
    }
    
    @DeleteMapping("/discount")
    @ApiOperation("Deleta um disconto")
    public void deleteCategory(@RequestBody DiscountType discountType) {
    	discountTypeRepository.delete(discountType);
    }
    
    @PutMapping("/discount")
    @ApiOperation("Atualiza os dados de um disconto")
    public DiscountType updateCategory(@RequestBody DiscountType discountType) {
        return discountTypeRepository.save(discountType);
    }
}
