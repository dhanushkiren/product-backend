package com.product.ProductDetails.Service;

import com.product.ProductDetails.domain.User;
import com.product.ProductDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public String addUser(User user){
        Optional<User> checkEmailExist= null;
//                userRepository.findByEmail(user.getEmail());
        if(checkEmailExist.isPresent()){
            return "email already exist";
        }
        return String.valueOf(userRepository.save(user));
    }

    @Override
    public User getUserId(String userId){
        Optional<User> findById =  userRepository.findById(userId);
        boolean present = findById.isPresent();
        if(present){
            return findById.get();
        }
        return null;
    }
    @Override
    public List<User> getAll(){
        return userRepository.findAll();
    }
    @Override
    public String updateUser(String userId, User user){
        Optional<User> findById = userRepository.findById(userId);
        if(findById.isPresent()){
            User user1 = findById.get();

            String id1 = user.getUserId();
            String userName = user.getUserName();
            String password = user.getPassword();
            String email = user.getEmailId();
            user1.setUserId(id1);
            user1.setUserName(userName);
            user1.setPassword(password);
            user1.setEmailId(email);
            userRepository.save(user1);

            return "Update Successfully";
        }

        return "User not found";

    }
    @Override
    public String deleteUser(String userId){
        Optional<User> findById = userRepository.findById(userId);
        if (findById.isPresent()){
            userRepository.deleteById(userId);
            return "Delete Successfully";
        }
        return "User not found";
    }

}
