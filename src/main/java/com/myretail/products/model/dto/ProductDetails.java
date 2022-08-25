package com.myretail.products.model.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Data
@NoArgsConstructor
public class ProductDetails {

    @Id
    @NonNull
    private String id;
    private String productName;
    private Price current_price;
}
