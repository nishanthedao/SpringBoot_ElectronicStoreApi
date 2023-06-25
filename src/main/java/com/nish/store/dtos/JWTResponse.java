package com.nish.store.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JWTResponse {
    private String jwtToken;
    private UserDto userDto;
}
