package com.dicasdeumdev.api.services;

import com.dicasdeumdev.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
