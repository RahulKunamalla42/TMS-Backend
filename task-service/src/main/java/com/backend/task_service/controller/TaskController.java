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
    public ResponseEntity<?> getTaskById(@PathVariable Long id) throws Exception {
        TaskDTO taskDTO = taskService.getTaskById(id);
        return taskDTO==null?
                new ResponseEntity<>(new Response("task not found"), HttpStatus.NOT_FOUND):
                new ResponseEntity<>(taskDTO,HttpStatus.OK);
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createTask(@RequestBody Task task,
                                         @RequestHeader("Authorization") String token) throws Exception {
        TaskDTO task1 = taskService.createTask(task, token);
        return new ResponseEntity<>(task1,HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllTasks(@RequestParam(required = false) Status status){
        List<TaskDTO> allTasks = taskService.getAllTasks(status);
        return new ResponseEntity<>(allTasks,HttpStatus.OK);
    }
    @DeleteMapping("/deletetask")
    public ResponseEntity<?> deleteTask(@RequestParam Long id) throws Exception {
        taskService.deleteTask(id);
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
                                         @RequestParam Long taskid) throws Exception {
        return new ResponseEntity<>(taskService.asigningToUser(userid,taskid),HttpStatus.OK);
    }

    @PutMapping("/completetask")
    public ResponseEntity<?> completetask(@RequestParam Long taskid) throws Exception {
        return new ResponseEntity<>(taskService.completeTask(taskid),HttpStatus.OK);
    }

    @GetMapping("/tasksasigndtouser")
    public ResponseEntity<?> getAllTasksByUserId(@RequestParam Long userid,@RequestParam(required = false) Status status) throws MyException {
        return new ResponseEntity<>(taskService.getAllTasksByUserId(userid,status),HttpStatus.OK);
    }

}
