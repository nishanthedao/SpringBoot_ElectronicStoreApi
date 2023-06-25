package com.nish.store.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "category_name", length = 200, nullable = false, unique = true)
    private String title;
    @Column(name = "category_desc", length = 1000)
    private String description;
    @Column(name = "category_cover_image")
    private String coverImage;
}
