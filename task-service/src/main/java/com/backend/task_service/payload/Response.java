package com.backend.task_service.payload;

import com.backend.task_service.entity.Task;
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
    private Task task;
    private List<?> tasks;

    public Response(String message) {
        this.message = message;
    }
}