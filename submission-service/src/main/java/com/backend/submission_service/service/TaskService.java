package com.backend.submission_service.service;


import com.backend.submission_service.exception.MyException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "task-service",url = "http://task-service:9002")
public interface TaskService {
    @GetMapping("/task/gettask/{id}")
    ResponseEntity<?> getTaskById(@PathVariable Long id) throws MyException;
    @PutMapping("/task/completetask")
    ResponseEntity<?> completetask(@RequestParam Long taskid) throws MyException;
}
