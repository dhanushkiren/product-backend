package com.product.ProductDetails.Service;

import com.product.ProductDetails.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    void deleteProductCart(String userId, String productId);

    void deleteProductFromCart(String userId, String productId);
}
