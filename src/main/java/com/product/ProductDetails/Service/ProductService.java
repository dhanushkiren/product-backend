package com.product.ProductDetails.Service;


import com.product.ProductDetails.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProductDetails(String id);

    List<Product> allDetails();

    Product createProduct(Product product);

    String updateProduct(String id, Product product);

    String delete(String id);
}
