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

import com.produtos.apirest.models.Product;
import com.produtos.apirest.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Product API Rest")
@CrossOrigin(origins="*")
public class ProductResource {

    @Autowired
    ProductRepository productRepository;
    
    @GetMapping("/products")
    @ApiOperation(value="Returns a list of products")
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/product/{id}")
    @ApiOperation(value="Returns a single product by the given id")
    public Product selectProduct(@PathVariable(value="id") int id) {
        return productRepository.findByid(id);
    }
    
    @PostMapping("/product")
    @ApiOperation("Register a new product")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    
    @DeleteMapping("/product")
    @ApiOperation("Deletes a product")
    public void deleteProduct(@RequestBody Product product) {
    	productRepository.delete(product);
    }
    
    @PutMapping("/product")
    @ApiOperation("Updates product data")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
