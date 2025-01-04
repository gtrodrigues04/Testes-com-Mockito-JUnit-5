package com.dicasdeumdev.api.resources;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.services.UserService;
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
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {

        return ResponseEntity.ok(service.findById(id));
    }
}
