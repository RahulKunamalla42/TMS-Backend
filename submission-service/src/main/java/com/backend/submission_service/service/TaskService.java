package com.backend.submission_service.service;


import com.backend.submission_service.exception.MyException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "task-service")
public interface TaskService {
    @GetMapping("/task/gettask/{id}")
    ResponseEntity<?> getTaskById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws MyException;

//    @PutMapping("/task/completetask")
//    ResponseEntity<?> completetask(@RequestParam Long taskid) throws MyException;
}
