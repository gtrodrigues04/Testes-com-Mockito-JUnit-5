package com.dicasdeumdev.api.services.impl;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.dto.UserDTO;
import com.dicasdeumdev.api.services.exceptions.ObjectNotFoundException;
import com.dicasdeumdev.api.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final String NAME = "teste";
    public static final int ID = 1;
    public static final String EMAIL = "teste@gmail.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private User user;

    @Mock
    private UserDTO userDTO;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnUserIntance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        when(mapper.map(any(User.class), Mockito.eq(UserDTO.class)))
                .thenReturn(userDTO);

        UserDTO response = service.findById(ID);

        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(ID, response.getId());
    }

    @Test
    void whenFindByIdAnObjectNotFoundException() {
        when(repository.findById(anyInt()))
                .thenThrow(new ObjectNotFoundException("Objeto não encontrado"));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado", ex.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}