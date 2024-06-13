package com.product.ProductDetails.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("user")
public class
User {
    @Id
    private String userId;
    @Field("user_name")
    private String userName;
    @Field("user_password")
    private String password;
    private String emailId;
    private Roles role;
}

