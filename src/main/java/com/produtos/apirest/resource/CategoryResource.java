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

import com.produtos.apirest.models.Category;
import com.produtos.apirest.repository.CategoryRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Category API Rest")
@CrossOrigin(origins="*")
public class CategoryResource {

	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/categories")
	@ApiOperation(value="Lista todas as categorias")
	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}
	
    @GetMapping("/category/{id}")
    @ApiOperation(value="Retorna uma única categoria pelo id informado")
    public Category selectCategory(@PathVariable(value="id") int id) {
        return categoryRepository.findByid(id);
    }
	
    @PostMapping("/category")
    @ApiOperation(value="Cadastra uma nova categoria")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
    
    @DeleteMapping("/category")
    @ApiOperation("Deleta uma categoria")
    public void deleteCategory(@RequestBody Category category) {
    	categoryRepository.delete(category);
    }
    
    @PutMapping("/category")
    @ApiOperation("Atualiza os dados de uma categoria")
    public Category updateCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
