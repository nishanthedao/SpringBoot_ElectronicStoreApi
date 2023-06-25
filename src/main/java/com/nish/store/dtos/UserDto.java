package com.nish.store.dtos;

import com.nish.store.customValidation.ImageValidation;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String userId;
    @NotBlank(message = "Name is mandatory")
//    @Size(min = 2, max = 50)
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email
//    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    private String gender;
    private String about;
//    @ImageValidation
    private String profile;
}
