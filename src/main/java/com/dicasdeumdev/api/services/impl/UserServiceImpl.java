package com.dicasdeumdev.api.services.impl;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.repositories.UserRepository;
import com.dicasdeumdev.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id) {
        Optional<User> user = repository.findById(id);
        return user.orElse(null);
    }
}
