package com.syphan.market.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syphan.market.models.User;
import com.syphan.market.repository.UserRepository;

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
    @ApiOperation(value="Returns a list of users")
    public List<User> listUsers() {
        return userRepository.findAll();
    }
    
    @GetMapping("/user/{email}")
    @ApiOperation(value="Returns a single user by the given id")
    public User selectUser(@PathVariable(value="email") String email) {
        return userRepository.findByEmail(email);
    }
    
    @PostMapping("/user")
    @ApiOperation("Register a new user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    
    @PutMapping("/user")
    @ApiOperation("Updates a user's data")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
