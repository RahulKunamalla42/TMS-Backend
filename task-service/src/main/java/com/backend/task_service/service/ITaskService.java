package com.backend.task_service.service;

import com.backend.task_service.dto.TaskDTO;
import com.backend.task_service.entity.Task;
import com.backend.task_service.exception.MyException;
import com.backend.task_service.payload.Status;

import java.util.List;

public interface ITaskService {
    boolean isAdmin(String jwt) throws MyException;
    TaskDTO createTask(Task task, String jwt) throws Exception;
    TaskDTO getTaskById(Long id) throws Exception;
    List<TaskDTO> getAllTasks(Status status);
    TaskDTO updateTask(Long taskid,Long userid,Task updatedtask) throws Exception;
    void deleteTask(Long taskid,String jwt) throws Exception;
    TaskDTO asigningToUser(Long userid,Long taskid,String jwt) throws Exception;
    List<TaskDTO> getAllTasksByUserId(Long userid, Status status) throws MyException;
    TaskDTO completeTask(Long taskid) throws Exception;
}