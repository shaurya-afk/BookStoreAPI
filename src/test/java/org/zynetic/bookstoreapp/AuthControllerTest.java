package org.zynetic.bookstoreapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.zynetic.bookstoreapp.Controller.AuthController;
import org.zynetic.bookstoreapp.Dto.UserDto;
import org.zynetic.bookstoreapp.Service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @Test
    void registerUserReturnsSuccessResponse() {
        UserDto userDto = new UserDto("username", "password");
        when(userService.register(userDto)).thenReturn("User registered successfully");

        ResponseEntity<?> response = authController.registerUser(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User registered successfully", response.getBody());
    }

    @Test
    void registerUserHandlesServiceException() {
        UserDto userDto = new UserDto("username", "password");
        when(userService.register(userDto)).thenThrow(new RuntimeException("Registration failed"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authController.registerUser(userDto));

        assertEquals("Registration failed", exception.getMessage());
    }

    @Test
    void loginUserReturnsSuccessResponse() {
        UserDto userDto = new UserDto("username", "password");
        when(userService.login(userDto)).thenReturn("Login successful");

        ResponseEntity<?> response = authController.loginUser(userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    void loginUserHandlesServiceException() {
        UserDto userDto = new UserDto("username", "password");
        when(userService.login(userDto)).thenThrow(new RuntimeException("Login failed"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> authController.loginUser(userDto));

        assertEquals("Login failed", exception.getMessage());
    }
}