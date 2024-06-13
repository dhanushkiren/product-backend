package com.product.ProductDetails.Controller;

import com.product.ProductDetails.Service.CartServiceImpl;

import com.product.ProductDetails.domain.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

     @Autowired
     CartServiceImpl cartServiceImpl;

    @PostMapping("/post/{userId}")
    public void addToCart(@PathVariable("userId") String userId, @RequestBody List<String> productIds){
        cartServiceImpl.addToCart(userId,productIds);
    }

    @GetMapping("/get/{cartId}")
    public Cart getCart (@PathVariable String cartId){
        return cartServiceImpl.getCart(cartId);
    }

    @PutMapping("/update/{userId}/{productId}/{quantity}")
    public void updateProduct(@PathVariable("userId") String userId, @PathVariable String productId, @PathVariable int quantity, @RequestBody List<String> productIds){
        cartServiceImpl.updateProduct(userId,productId,quantity,productIds);
    }

    @DeleteMapping("/delete/{userId}/{productId}")
        public void deleteProductCart(@PathVariable("userId") String userId, @PathVariable String productId){
        cartServiceImpl.deleteProductCart(userId,productId);
    }

    @DeleteMapping("/{userId}/products/{productId}")
    public ResponseEntity<?> deleteProductFromCart(@PathVariable String userId, @PathVariable String productId) {
        try {
            cartServiceImpl.deleteProductFromCart(userId, productId);
            return ResponseEntity.ok().body("Product removed from cart successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
