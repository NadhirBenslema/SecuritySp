package com.example.usermanagement.Service;

import com.example.usermanagement.Entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    User updateUser(User user,Integer id);
    String deleteUser(Integer id);

}
