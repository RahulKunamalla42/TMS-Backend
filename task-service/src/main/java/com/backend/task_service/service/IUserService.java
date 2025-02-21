package com.backend.task_service.service;

import com.backend.task_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service",url = "http://user-service:9001")
public interface IUserService {
    @GetMapping("/getuser")
    UserDTO getUser(@RequestHeader String token);
    @GetMapping("/getuserbyid/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
