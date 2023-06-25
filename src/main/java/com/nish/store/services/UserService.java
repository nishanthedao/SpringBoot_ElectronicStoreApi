package com.nish.store.services;

import com.nish.store.dtos.PageableResponse;
import com.nish.store.dtos.UserDto;
import com.nish.store.entities.User;

import java.util.List;

public interface UserService {

    // Create
    UserDto createUser(UserDto user);

    // Update
    UserDto updateUser(UserDto userDetails, String id);

    // Delete
    void deleteUser(String id);

    // Get all user
    List<UserDto> getAllUsers();

    // Get single user by id
    UserDto getUserById(String userId);

    // Get single user by email
    UserDto getUserByEmail(String email);

    // Search user
    List<UserDto> searchUser(String byName);

    // Paginator
    PageableResponse<UserDto> getAllUsersPaginator(int pageNumber, int pageSize, String sortBy, String sortDir);
}
