package com.nish.store.controllers;

import com.nish.store.dtos.ApiResponseMessage;
import com.nish.store.dtos.ImageResponse;
import com.nish.store.dtos.PageableResponse;
import com.nish.store.dtos.UserDto;
import com.nish.store.services.FileService;
import com.nish.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Value("${user.profile.image.path}")
    private String imageUploadPath;
    // create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto dto = userService.createUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody UserDto userDto){
        UserDto dto = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        ApiResponseMessage apiResponseMessage = ApiResponseMessage
                .builder()
                .message("User is deleted successfully")
                .success(true).status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
    }

    // get all
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    // get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    // get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    //Search user
    @GetMapping("/search/{name}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String name){
        return new ResponseEntity<>(userService.searchUser(name), HttpStatus.OK);
    }

    @GetMapping("/paginator")
    public ResponseEntity<PageableResponse<UserDto>> getAllUserByPaginator(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return new ResponseEntity<>(userService.getAllUsersPaginator(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }


    // Upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(
            @RequestParam("userImage")MultipartFile image, @PathVariable String userId
            ) throws IOException {
        System.err.println("Upload user image");
        String imageName = fileService.uploadFile(image, imageUploadPath);
        UserDto userDto = userService.getUserById(userId);
        System.err.println("imageName===>"+imageName);
        userDto.setProfile(imageName);
        System.err.println("=============dsdsds=================");
        UserDto user = userService.updateUser(userDto, userId);
        System.err.println("=============below=================");
        ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    // Serve image
}
