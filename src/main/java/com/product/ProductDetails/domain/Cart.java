package com.product.ProductDetails.domain;


import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document("Cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart{

    @Id
    private String cartId;

    private int totalPrice;

    private List<Product> products;

    private String userId;

    private String productId;

}
