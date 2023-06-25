package com.nish.store.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String categoryId;
    @NotBlank(message = "Tile is mandatory !!")
    @Min(value = 5, message = "Title must be minimum of 5 character !!")
    private String title;
    @NotBlank(message = "Description is mandatory !!")
    private String description;
//    @NotBlank(message = "Cover image is mandatory !!")
    private String coverImage;
}
