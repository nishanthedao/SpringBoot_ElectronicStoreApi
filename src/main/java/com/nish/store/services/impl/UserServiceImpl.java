package com.nish.store.services.impl;

import com.nish.store.dtos.PageableResponse;
import com.nish.store.dtos.UserDto;
import com.nish.store.entities.User;
import com.nish.store.exceptions.ResourceNotFoundException;
import com.nish.store.helpers.Helper;
import com.nish.store.repositories.UserRepository;
import com.nish.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Generate unique id in string format
        String userID = UUID.randomUUID().toString();
        userDto.setUserId(userID);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = dtoToEntity(userDto);
        User saveUser = userRepository.save(user);
        UserDto saveUserDto = entityToDto(saveUser);
        return saveUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDetails, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given Id"));
        user.setName(userDetails.getName());
        user.setGender(userDetails.getGender());
        user.setProfile(userDetails.getProfile());
        user.setAbout(userDetails.getAbout());
        User updateUser = userRepository.save(user);

        return entityToDto(updateUser);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given Id"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given Id"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with Given email"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String byName) {
        List<User> users = userRepository.findByNameContaining(byName);
        return users.stream().map(u -> entityToDto(u)).collect(Collectors.toList());
    }

    @Override
    public PageableResponse<UserDto> getAllUsersPaginator(int pageNumber, int pageSize, String sortBy, String sortDir) {
//        Sort sort = Sort.by(sortBy);
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        PageableResponse<UserDto> pageableResponse = Helper.getVPageableResponse(page, UserDto.class);
        return pageableResponse;
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = new User();
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setGender(userDto.getGender());
//        user.setAbout(userDto.getAbout());
//        user.setProfile(userDto.getProfile());
//        return user;
        return modelMapper.map(userDto, User.class);
    }

    private UserDto entityToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setUserId(user.getUserId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setGender(user.getGender());
//        userDto.setAbout(user.getAbout());
//        userDto.setProfile(user.getProfile());
//        return userDto;
        return modelMapper.map(user, UserDto.class);
    }
}
