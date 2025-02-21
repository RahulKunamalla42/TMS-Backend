package com.backend.user_service.util;

import com.backend.user_service.dto.UserDTO;
import com.backend.user_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public UserDTO userToUserDto(User user){
        return new UserDTO(user.getUserId(), user.getUsername(), user.getRole());
    }
}
