package com.dicasdeumdev.api.services.impl;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.dto.UserDTO;
import com.dicasdeumdev.api.exceptions.DataIntegratyViolationException;
import com.dicasdeumdev.api.exceptions.ObjectNotFoundException;
import com.dicasdeumdev.api.repositories.UserRepository;
import com.dicasdeumdev.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO findById(Integer id) {
        Optional<User> user = repository.findById(id);

        if (user.isPresent()) {
             return mapper.map(user.get(), UserDTO.class);
        }

        throw new ObjectNotFoundException("Objeto não encontrado");
    }

    @Override
    public List<UserDTO> findAll() {
        return repository.findAll().stream()
                .map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public User create(UserDTO dto) {
        findByEmail(dto);
        return repository.save(mapper.map(dto, User.class));
    }

    private void findByEmail(UserDTO dto) {
        Optional<User> user = repository.findByEmail(dto.getEmail());

        if (user.isPresent()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }
    }

    @Override
    public UserDTO update(Integer id,UserDTO dto) {
        dto.setId(id);
        if (dto.getEmail() != null) {
            findByEmail(dto);
        }
        return mapper.map(repository.save(mapper.map(dto, User.class)), UserDTO.class);
    }
}
