package com.product.ProductDetails.repository;

import com.product.ProductDetails.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableMongoRepositories
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{ 'email':?0 }")
    User findByEmail(String email);
//    Optional<User> findByEmail(String email);

    @Query("{'user_name': ?0}")
    Optional<User> findByUserName(String userName);
}
