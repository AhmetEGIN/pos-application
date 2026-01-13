package com.egin.user.service;

import com.egin.user.model.User;

import java.util.List;

public interface UserReadService {

    User getUserById(String id);
    List<User> getAllUsers();
    User getUserByEmail(String email);
    User getCurrentUser();

}
