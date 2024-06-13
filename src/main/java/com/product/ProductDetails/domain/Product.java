package com.product.ProductDetails.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("product")
public class Product {

    @Id
    private String productId;
    private String title;
    private String category;
    private String description;
    private List<String> images;
    private int price;
    private double discountPercentage;
    private int quantity;

}
