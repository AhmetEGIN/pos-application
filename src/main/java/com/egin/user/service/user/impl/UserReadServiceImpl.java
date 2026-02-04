package com.egin.user.service.user.impl;

import com.egin.user.exception.UserNotFoundException;
import com.egin.user.model.User;
import com.egin.user.model.entity.UserEntity;
import com.egin.user.model.mapper.ListUserEntityToListUserMapper;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import com.egin.user.repository.UserRepository;
import com.egin.user.service.user.UserReadService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReadServiceImpl implements UserReadService {

    private final UserRepository userRepository;

    public UserReadServiceImpl(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(String id) {

        final UserEntity userEntityFromDb = this.userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserEntityToUserMapper.toUser(userEntityFromDb);
    }

    @Override
    public List<User> getAllUsers() {

        final List<UserEntity> userEntityList = this.userRepository.findAll();


        return ListUserEntityToListUserMapper.toListUser(userEntityList);
    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println("Fetching user by email: " + email);
        final UserEntity userEntityFromDb = this.userRepository.findUserEntityByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        System.out.println("User found: " + userEntityFromDb.getId());
        return UserEntityToUserMapper.toUser(userEntityFromDb);
    }

    @Override
    public User getCurrentUser() {

        final Jwt jwt = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        final String email = jwt.getClaimAsString("userEmail");
        System.out.println("Current user email: " + email);
        return this.getUserByEmail(email);
    }
}
