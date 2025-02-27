package com.backend.task_service.controller;

import com.backend.task_service.dto.TaskDTO;
import com.backend.task_service.entity.Task;
import com.backend.task_service.payload.Response;
import com.backend.task_service.payload.Status;
import com.backend.task_service.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task/admin")
public class AdminTaskController {

    private final ITaskService taskService;

    public AdminTaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("/createtask")
    public ResponseEntity<?> createTask(@RequestBody Task task,
                                        @RequestHeader("Authorization") String token) throws Exception {
        TaskDTO task1 = taskService.createTask(task, token);
        return new ResponseEntity<>(task1, HttpStatus.CREATED);
    }
    @GetMapping("/getalltasks")
    public ResponseEntity<?> getAllTasks(@RequestParam(required = false) Status status){
        List<TaskDTO> allTasks = taskService.getAllTasks(status);
        return new ResponseEntity<>(allTasks,HttpStatus.OK);
    }
    @DeleteMapping("/deletetask")
    public ResponseEntity<?> deleteTask(@RequestParam Long id,
                                        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        taskService.deleteTask(id,jwt);
        return new ResponseEntity<>(new Response("task deleted"+id),HttpStatus.OK);
    }

    @PutMapping("/updatetask")
    public ResponseEntity<?> updateTask(@RequestBody Task task,
                                        @RequestParam(required = false) Long userid,
                                        @RequestParam Long taskid) throws Exception {
        return new ResponseEntity<>(taskService.updateTask(taskid,userid,task),HttpStatus.OK);
    }
    @PutMapping("/asigntouser")
    public ResponseEntity<?> asigntouser(@RequestParam Long userid,
                                         @RequestParam Long taskid,
                                         @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return new ResponseEntity<>(taskService.asigningToUser(userid,taskid,jwt),HttpStatus.OK);
    }

}
