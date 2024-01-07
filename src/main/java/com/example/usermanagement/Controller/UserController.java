package com.example.usermanagement.Controller;

import com.example.usermanagement.Entity.User;
import com.example.usermanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(
            @ModelAttribute User user,
            @RequestParam("photoFile") MultipartFile photoFile) {
        try {
            if (photoFile != null && !photoFile.isEmpty()) {
                String photoFileName = savePhotoFile(photoFile); // Save the file and get the filename
                user.setPhoto(photoFileName);
            }

            // Assuming addUser method returns the saved user
            User savedUser = userService.addUser(user);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private String savePhotoFile(MultipartFile photoFile) throws IOException {
        // Implement logic to save the file to a designated location
        // Return the filename or a unique identifier for the saved file
        // For simplicity, you can use the original filename
        return photoFile.getOriginalFilename();
    }


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> getUsersById(@PathVariable("id")Integer id){
        return userService.getUserById(id);
    }

    @PutMapping( value = "/update/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.updateUser(user,id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<String> deleteRecette(@PathVariable("id") Integer id){
            return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

}
