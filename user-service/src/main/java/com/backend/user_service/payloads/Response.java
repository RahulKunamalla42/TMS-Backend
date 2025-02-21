package com.backend.user_service.payloads;

import com.backend.user_service.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String token;
    private String message;
    private Role role;
    private UserDTO user;
    private List<UserDTO> users;

    public Response(String message) {
        this.message = message;
    }
}
