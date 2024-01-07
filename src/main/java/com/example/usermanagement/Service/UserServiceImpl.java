package com.example.usermanagement.Service;

import com.example.usermanagement.Entity.User;
import com.example.usermanagement.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user, Integer id) {
        if (userRepository.findById(id).isPresent()) {
            User existingUser = userRepository.findById(id).get();
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setBirthDate(user.getBirthDate());

            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public String deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()){
            User existingU = userRepository.findById(id).get();
            userRepository.delete(existingU);
            return "User deleted successfully !";
        }
        userRepository.deleteById(id);
        return "Introuvable !";
    }

}
