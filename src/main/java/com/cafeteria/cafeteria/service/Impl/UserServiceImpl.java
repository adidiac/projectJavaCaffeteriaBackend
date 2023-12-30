package com.cafeteria.cafeteria.service.Impl;
import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.DbModels.User;
import com.cafeteria.cafeteria.ViewModels.UserLoginModel;
import com.cafeteria.cafeteria.ViewModels.UserModel;
import com.cafeteria.cafeteria.ViewModels.UserRegisterModel;
import com.cafeteria.cafeteria.repository.UserRepository;
import com.cafeteria.cafeteria.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final String userRole = "CUSTOMER";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserModel register(UserRegisterModel userRegisterModel) {
        // register user
        boolean userExist = this.userRepository.findAll().stream().anyMatch(user -> user.getUsername().equals(userRegisterModel.username));

        if (userExist) {
            return null;
        }

        User user = new User();

        user.setUsername(userRegisterModel.username);
        user.setPassword(userRegisterModel.password);
        user.setFullName(userRegisterModel.firstName + " " + userRegisterModel.lastName);
        user.setRole(this.userRole);
        user.setEmail(userRegisterModel.email);

        var userSaved = this.userRepository.save(user);
        
        if (userSaved == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.username = userSaved.getUsername();
        userModel.email = userSaved.getEmail();
        userModel.fullName = userSaved.getFullName();
        userModel.role = userSaved.getRole();

        return userModel;
    }

    @Override
    public UserModel login(UserLoginModel userLoginModel) {
        var user = this.userRepository.findAll().stream().filter(u -> u.getUsername().equals(userLoginModel.username) && u.getPassword().equals(userLoginModel.password)).findFirst().orElse(null);
        if (user == null) {
            return null;
        }

        UserModel userModel = new UserModel();
        userModel.username = user.getUsername();
        userModel.email = user.getEmail();
        userModel.fullName = user.getFullName();
        userModel.role = user.getRole();
        return userModel;
    }

    @Override
    public Long getUserIdByUsername(String username) {
        var user = this.userRepository.findAll().stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
        if (user == null) {
            return null;
        }
        return user.getUserId();
    }
    
}
