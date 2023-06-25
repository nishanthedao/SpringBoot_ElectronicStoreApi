package com.nish.store.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Product {
    @Id
    private String productId;
    @Column(unique = true)
    private String productName;
    @Column(length = 10000)
    private String productDesc;
    @Column(precision = 14, scale = 4)
    private Double price;
    @Column(precision = 14, scale = 4)
    private Double discountPrice;
    private String productImageName;
    private Character defunct;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedBy;
}
