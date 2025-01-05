package com.dicasdeumdev.api.resources;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.dto.UserDTO;
import com.dicasdeumdev.api.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    public static final String NAME = "teste";
    public static final int ID = 1;
    public static final String EMAIL = "teste55@gmail.com";
    public static final String PASSWORD = "123";

    @Mock
    private User user;

    @Mock
    private UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void whenFindAllThenReturnListOfUserDTO() {
        when(service.findAll()).thenReturn(new ArrayList<>(List.of(userDTO)));

        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());
    }

    @Test
    void wheCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = resource.create(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(ID, userDTO)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.update(ID, userDTO);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}