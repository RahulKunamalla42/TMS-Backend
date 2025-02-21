package com.backend.submission_service.payload;

import com.backend.submission_service.dto.SubDto;

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
    private SubDto sub;
    private List<?> subs;

    public Response(String message) {
        this.message = message;
    }
}