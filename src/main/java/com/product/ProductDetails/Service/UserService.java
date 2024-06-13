package com.product.ProductDetails.Service;

import com.product.ProductDetails.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    String addUser(User user);

    User getUserId(String userId);

    List<User> getAll();

    String updateUser(String userId, User user);

    String deleteUser(String UserId);


}
