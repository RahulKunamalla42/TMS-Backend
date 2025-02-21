package com.backend.task_service.service;

import com.backend.task_service.dto.TaskDTO;
import com.backend.task_service.dto.UserDTO;
import com.backend.task_service.entity.Task;
import com.backend.task_service.exception.MyException;
import com.backend.task_service.payload.Status;
import com.backend.task_service.repo.TaskRepo;
import com.backend.task_service.util.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements ITaskService{

    private final TaskRepo taskRepo;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepo taskRepo, IUserService userService, ModelMapper modelMapper) {
        this.taskRepo = taskRepo;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TaskDTO createTask(Task task, String jwt) throws MyException {
        UserDTO user = userService.getUser(jwt);
        if (!user.getRole().name().equals("ADMIN")) {
            throw new MyException("ADMIN ONLY CREATE TASKS");
        }
        return modelMapper.taskToTaskDto(taskRepo.save(task));
    }

    @Override
    public TaskDTO getTaskById(Long id) throws MyException {
        Task found = taskRepo.findById(id)
                .orElseThrow(() -> new MyException("Task not found"));
        return modelMapper.taskToTaskDto(found);
    }


    @Override
    public List<TaskDTO> getAllTasks(Status status) {
        List<Task> all = taskRepo.findAll();
        return all.stream()
                .filter(task -> status == null || (task.getStatus() != null &&
                        task.getStatus().name().equalsIgnoreCase(status.name())))
                .map(modelMapper::taskToTaskDto)
                .toList();
    }

    public TaskDTO updateTask(Long taskid, Long userid, Task updatedTask) throws MyException {
        Task existingTask = taskRepo.findById(taskid)
                .orElseThrow(() -> new MyException("Task not found"));

        // Update only non-null fields
        if (updatedTask.getTitle() != null) existingTask.setTitle(updatedTask.getTitle());
        if (updatedTask.getDescription() != null) existingTask.setDescription(updatedTask.getDescription());
        if (updatedTask.getDeadlilne() != null) existingTask.setDeadlilne(updatedTask.getDeadlilne());
        if (updatedTask.getCreatedAt() != null) existingTask.setCreatedAt(updatedTask.getCreatedAt());
        if (updatedTask.getStatus() != null) existingTask.setStatus(updatedTask.getStatus());

        // Save updated task in DB
        Task savedTask = taskRepo.save(existingTask);

        // Convert to DTO & return
        return new TaskDTO(
                savedTask.getTaskId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getDeadlilne(),
                savedTask.getCreatedAt(),
                savedTask.getAsignedUserId(),
                savedTask.getStatus()
        );
    }

    @Override
    public void deleteTask(Long taskid) throws MyException {
        if (!taskRepo.existsById(taskid)) {
            throw new MyException("Task not found");
        }
        taskRepo.deleteById(taskid);
    }

    @Override
    public TaskDTO asigningToUser(Long userid, Long taskid) throws MyException {
        Task task = taskRepo.findById(taskid)
                .orElseThrow(() -> new MyException("Task not found with ID: " + taskid));

        UserDTO userDTO = userService.getUserById(userid);

        if (userDTO == null) {
            throw new MyException("User not found with ID: " + userid);
        }
        task.setAsignedUserId(userid);

        Task updatedTask = taskRepo.save(task);

        return modelMapper.taskToTaskDto(updatedTask);
    }

    @Override
    public List<TaskDTO> getAllTasksByUserId(Long userid, Status status) throws MyException {
        List<Task> found = taskRepo.findByAsignedUserId(userid).get();
        if (found.isEmpty()) {
            throw new MyException("No tasks found for user ID: " + userid);
        }
        return found.stream()
                .filter(task -> status == null || task.getStatus().name().equalsIgnoreCase(status.name()))
                .map(modelMapper::taskToTaskDto)
                .toList();
    }


    @Override
    public TaskDTO completeTask(Long taskid) throws MyException {
        Task found = taskRepo.findById(taskid)
                .orElseThrow(() -> new MyException("not found"));
        found.setStatus(Status.DONE);
        return modelMapper.taskToTaskDto(taskRepo.save(found));
    }
}
