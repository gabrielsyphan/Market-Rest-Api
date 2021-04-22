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

import com.produtos.apirest.models.User;
import com.produtos.apirest.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="User API Rest")
@CrossOrigin(origins="*")
public class UserResource {
	
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/users")
    @ApiOperation(value="Retorna uma lista de usuários")
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/user/{id}")
    @ApiOperation(value="Retorna um único usuário pelo id informado")
    public User selectUser(@PathVariable(value="id") int id) {
        return userRepository.findByid(id);
    }
    
    @PostMapping("/user")
    @ApiOperation("Cadastra um novo usuário")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    @DeleteMapping("/user")
    @ApiOperation("Deleta um usuário")
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }
    
    @PutMapping("/user")
    @ApiOperation("Atualiza os dados de um usuário")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
