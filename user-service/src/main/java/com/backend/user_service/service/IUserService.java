package com.backend.user_service.service;

import com.backend.user_service.dto.UserDTO;
import com.backend.user_service.entity.User;
import com.backend.user_service.exception.MyException;
import com.backend.user_service.payloads.LoginRequest;
import com.backend.user_service.payloads.Response;

import java.util.List;

public interface IUserService {
    UserDTO getUser(String token);
    User getUserById(Long id);
    List<UserDTO> getAllUsers();
    Response LoginUser(LoginRequest loginRequest) throws MyException;
    UserDTO RegisterUser(User user) throws MyException;
}
