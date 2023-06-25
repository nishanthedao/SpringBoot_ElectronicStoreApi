package com.nish.store.dtos;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProductDto {
    private String productId;
    private String productName;
    private String productDesc;
    private Double price;
    private Double discountPrice;
    private String productImageName;
    private Character defunct;
    private Date createdBy;
    private Date updatedBy;
}
