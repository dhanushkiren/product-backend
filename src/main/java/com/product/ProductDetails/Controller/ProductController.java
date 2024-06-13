package com.product.ProductDetails.Controller;

import com.product.ProductDetails.Service.ProductServiceImpl;
import com.product.ProductDetails.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/product/{id}")
    public Product getSingleProduct(@PathVariable("id") String productId){
        return productServiceImpl.getProductDetails(productId);
    }

    @GetMapping("/product/allProduct")
    public List<Product> alldeatils(){
        return productServiceImpl.allDetails();
    }

    @PostMapping("/product/post")
    public Product createProduct(@RequestBody Product product){
        return productServiceImpl.createProduct(product);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") String productId,@RequestBody Product product){
        return productServiceImpl.updateProduct(productId,product);
    }

    @DeleteMapping("/delete/product/{id}")
    public String delete(@PathVariable("id") String productId){
        return productServiceImpl.delete(productId);
    }

}
