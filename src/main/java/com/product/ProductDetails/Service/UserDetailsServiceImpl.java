package com.product.ProductDetails.Service;

import com.product.ProductDetails.domain.CustomUserDetails;
import com.product.ProductDetails.domain.User;
import com.product.ProductDetails.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    private static final Logger logger =  LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUserName Method.");
        System.out.println("-1");
        System.out.println(username);
        Optional<User> user=userRepository.findByUserName(username);
        System.out.println(user.isEmpty());
        if(user.isEmpty()){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..");
        }
        logger.info("User Authenticated Successfully..");
        System.out.println("-success");

        return new CustomUserDetails(user.get());
  }
}