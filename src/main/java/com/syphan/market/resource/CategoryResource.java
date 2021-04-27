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

import com.syphan.market.models.Category;
import com.syphan.market.repository.CategoryRepository;

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
	@ApiOperation(value="List all categories")
	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}
	
    @GetMapping("/category/{id}")
    @ApiOperation(value="Returns a single category by the given id")
    public Category selectCategory(@PathVariable(value="id") int id) {
        return categoryRepository.findByid(id);
    }
	
    @PostMapping("/category")
    @ApiOperation(value="Register a new category")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
    
    @DeleteMapping("/category")
    @ApiOperation("Deletes a category")
    public void deleteCategory(@RequestBody Category category) {
    	categoryRepository.delete(category);
    }
    
    @PutMapping("/category")
    @ApiOperation("Updates data for a category")
    public Category updateCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
