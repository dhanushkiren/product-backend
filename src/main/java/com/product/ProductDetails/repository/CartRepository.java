package com.product.ProductDetails.repository;

import com.product.ProductDetails.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findByUserId(String userId);
    Optional<Cart> findByCartId(String cartId);
}
