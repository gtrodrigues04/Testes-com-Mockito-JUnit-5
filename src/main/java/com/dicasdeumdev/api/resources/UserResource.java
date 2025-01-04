package com.dicasdeumdev.api.resources;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.dto.UserDTO;
import com.dicasdeumdev.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {

        return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
    }
}
