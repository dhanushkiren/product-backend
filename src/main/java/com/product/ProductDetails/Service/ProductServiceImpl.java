package com.product.ProductDetails.Service;

import com.product.ProductDetails.domain.Product;
import com.product.ProductDetails.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductDetails(String productId){
        Optional<Product> findById= productRepository.findById(productId);
        boolean present=findById.isPresent();
        if (present){
            return findById.get();
        }
        return null;
    }

    @Override
    public List<Product> allDetails(){
        return productRepository.findAll();
    }

    @Override
    public  Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public String updateProduct(String productId, Product product){
        Optional<Product> findById = productRepository.findById(productId);
        if (findById.isPresent()){
            Product product1 = findById.get();

            String productId1 = product.getProductId();
            String name =product.getTitle();
            String category =product.getCategory();
            int price = product.getPrice();
            double discount =product.getDiscountPercentage();
            int quantity = product.getQuantity();

            product1.setProductId(productId1);
            product1.setTitle(name);
            product1.setCategory(category);
            product1.setPrice(price);
            product1.setDiscountPercentage(discount);
            product.setQuantity(quantity);

            productRepository.save(product1);
            return "Update Successfully";
        }
        return "not found";
    }

    @Override
    public String delete(String productId){
        Optional<Product> findById = productRepository.findById(productId);
        if (findById.isPresent()){
            productRepository.deleteById(productId);
            return "Successfully Delete";
        }
        return "not found";
    }

}
