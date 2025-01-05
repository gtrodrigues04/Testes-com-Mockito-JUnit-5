package com.dicasdeumdev.api.services.impl;

import com.dicasdeumdev.api.domain.User;
import com.dicasdeumdev.api.dto.UserDTO;
import com.dicasdeumdev.api.services.exceptions.DataIntegratyViolationException;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final String NAME = "teste";
    public static final int ID = 1;
    public static final String EMAIL = "teste55@gmail.com";
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
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(User.class), Mockito.eq(UserDTO.class)))
                .thenReturn(userDTO);
        List<UserDTO> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(UserDTO.class, response.get(0).getClass());
        assertEquals(ID, response.get(0).getId());
    }

    @Test
    void whenCreateUserThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(any(User.class), Mockito.eq(UserDTO.class)))
                .thenReturn(userDTO);
        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
    }
    @Test
    void whenCreateUserThenAnDataIntegratyViolationExcepetion() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
//           optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema", ex.getMessage());
        }
    }


    @Test
    void whenUpdateUserThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        when(mapper.map(any(User.class), Mockito.eq(UserDTO.class)))
                .thenReturn(userDTO);
        UserDTO response = service.update(ID, userDTO);

        assertNotNull(response);
        assertEquals(UserDTO.class, response.getClass());
        assertEquals(ID, response.getId());
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