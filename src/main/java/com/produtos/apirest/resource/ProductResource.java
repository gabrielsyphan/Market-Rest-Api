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
    @ApiOperation(value="Retorna uma lista de produtos")
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/product/{id}")
    @ApiOperation(value="Retorna um único produto pelo id informado")
    public Product selectProduct(@PathVariable(value="id") int id) {
        return productRepository.findByid(id);
    }
    
    @PostMapping("/product")
    @ApiOperation("Cadastra um novo produto")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
    
    @DeleteMapping("/product")
    @ApiOperation("Deleta um produto")
    public void deleteProduct(@RequestBody Product product) {
    	productRepository.delete(product);
    }
    
    @PutMapping("/product")
    @ApiOperation("Atualiza os dados de um produto")
    public Product updateProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
