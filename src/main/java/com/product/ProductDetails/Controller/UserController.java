package com.product.ProductDetails.Controller;

import com.product.ProductDetails.Service.UserServiceImpl;
import com.product.ProductDetails.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

 /*   @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return userServiceImpl.addUser(user);
    }
*/
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String userId){
        return userServiceImpl.getUserId(userId);
    }

    @GetMapping("/allUsers")
    public List<User> allUser(){
        return userServiceImpl.getAll();
    }

    @PutMapping("/update/user/{id}")
    public String updateUsers(@PathVariable String userId, @RequestBody User user){
        return userServiceImpl.updateUser(userId, user);
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUsers(@PathVariable String UserId){
        return userServiceImpl.deleteUser(UserId);
    }
}
