package com.dicasdeumdev.api.services;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Integer id);

    List<UserDTO> findAll();

    User create(UserDTO dto);

    UserDTO update(Integer id, UserDTO dto);

    void delete(Integer id);
}
