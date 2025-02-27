package com.backend.task_service.controller;

import com.backend.task_service.dto.TaskDTO;
import com.backend.task_service.entity.Task;
import com.backend.task_service.exception.MyException;
import com.backend.task_service.payload.Response;
import com.backend.task_service.payload.Status;
import com.backend.task_service.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/public/home")
    public String home(){
        return "hello";
    }

    @GetMapping("/gettask/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception {
        TaskDTO taskDTO = taskService.getTaskById(id);
        return taskDTO==null?
                new ResponseEntity<>(new Response("task not found"), HttpStatus.NOT_FOUND):
                new ResponseEntity<>(taskDTO,HttpStatus.OK);
    }

    @PutMapping("/completetask")
    public ResponseEntity<?> completetask(@RequestParam Long taskid) throws Exception {
        return new ResponseEntity<>(taskService.completeTask(taskid),HttpStatus.OK);
    }

    @GetMapping("/tasksasigndtouser")
    public ResponseEntity<?> getAllTasksByUserId(
            @RequestParam Long userid,
            @RequestParam(required = false) Status status
    ) throws MyException {
        System.out.println("Received Request: userid=" + userid + ", status=" + status);

        try {
            return new ResponseEntity<>(taskService.getAllTasksByUserId(userid, status), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // 🔍 Log the error in the console
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }




}
