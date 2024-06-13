package com.product.ProductDetails.Service;

import com.product.ProductDetails.domain.Cart;
import com.product.ProductDetails.domain.Product;
import com.product.ProductDetails.domain.User;
import com.product.ProductDetails.repository.CartRepository;
import com.product.ProductDetails.repository.ProductRepository;
import com.product.ProductDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CartServiceImpl implements CartService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    public void addToCart(@PathVariable String userId, List<String> productIds) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            User user2 = userOptional.get();
            Cart cart =  cartRepository.findByUserId(userId).orElse(new Cart());
            List<Product> products = cart.getProducts();
            if(products == null){
                products = new ArrayList<>();
                cart.setProducts(products);
            }
            for(String productId:productIds ){
                Optional<Product> productOptional = productRepository.findById(productId);
                if(productOptional.isPresent()){
                    Product product = productOptional.get();
                    products.add(product);
                    cart.setTotalPrice((int) (cart.getTotalPrice() + product.getPrice() * product.getQuantity() - product.getDiscountPercentage()));
                }
            }
            cart.setUserId(userId);
            cartRepository.save(cart);
        }else {
            throw new RuntimeException("User not found");
        }
    }
    public Cart getCart(String cartId) {
        Optional<Cart> findByCartId = cartRepository.findByCartId(cartId);
        boolean present = findByCartId.isPresent();
        if(present){
            return findByCartId.get();
        }
        return null;
    }
    public void updateProduct(String userId, String productId, int quantity ,List<String> productIds) {
        System.out.println(userId + " " + productId + " " + quantity);
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<Product> products = cart.getProducts();
            if (products != null) {
                for (Product product : products) {
                    if (product.getProductId().equals(productId)) {
                        product.setQuantity(quantity);
                        double totalPrice = calculateTotalPrice(products);
                        cart.setTotalPrice((int) totalPrice);
                        cartRepository.save(cart);
                        return;
                    }

                    Optional<Cart> cartOptional1 = cartRepository.findByUserId(userId);
                    if (cartOptional1.isPresent()) {
                        Cart cart1 = cartOptional1.get();
                        List<Product> productss = cart1.getProducts();
                        if (productss == null) {
                            productss = new ArrayList<>();
                            cart.setProducts(productss);
                        }

                        for (String productId2 : productIds) {
                            Optional<Product> productOptional = productRepository.findById(productId2);
                            if (productOptional.isPresent()) {
                                Product product1 = productOptional.get();
                                productss.add(product1);
                                cart.setTotalPrice((int) (cart.getTotalPrice() + product1.getPrice() * product1.getQuantity() - product1.getDiscountPercentage()));
                            }
                        }
                        cart.setUserId(userId);
                        cartRepository.save(cart);
                    }
                }
            } else {
                throw new RuntimeException("cart not found for userId" + userId);
            }
        }
    }

    @Override
    public void deleteProductCart(String userId, String productId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if(cartOptional.isPresent()){
            Cart cart = cartOptional.get();
            List<Product> products = cart.getProducts();
            if(products != null){
                boolean removed = products.removeIf(product -> product.getProductId().equals(productId));
                if(removed){
                    double totalPrice = 0;
                    for (Product product : products){
                        totalPrice += product.getPrice() - product.getDiscountPercentage();
                    }
                    cart.setTotalPrice((int) totalPrice);
                    cartRepository.save(cart);
                }else{
                    throw new RuntimeException("product with productId" + productId + "not found in cart");
                }
            }else {
                throw new RuntimeException("cart is empty");
            }
        }else {
            throw new RuntimeException("cart not found for userId"+userId);
        }
    }

    @Override
    public void deleteProductFromCart(String userId, String productId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<Product> products = cart.getProducts();
            if (products != null) {
                Product productToRemove = null;
                for (Product product : products) {
                    if (product.getProductId().equals(productId)) {
                        product.setQuantity(product.getQuantity() - 1);
                        if (product.getQuantity() <= 0) {
                            productToRemove = product;
                        }
                        break;
                    }
                }

                if (productToRemove != null) {
                    products.remove(productToRemove);
                }

                double totalPrice = calculateTotalPrice(products);
                cart.setTotalPrice((int) totalPrice);
                cartRepository.save(cart);
            } else {
                throw new RuntimeException("Cart is empty");
            }
        } else {
            throw new RuntimeException("Cart not found for userId " + userId);
        }
    }

    public double calculateTotalPrice(List<Product> products) {
        double totalPrice = 0;
        for (Product product : products) {
            double discountAmount = product.getPrice() * (product.getDiscountPercentage() / 100.0);
            double discountedPrice = product.getPrice() - discountAmount;
            totalPrice += discountedPrice * product.getQuantity();
        }
        return totalPrice;
    }

}



