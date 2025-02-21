package com.backend.task_service.util;

import com.backend.task_service.dto.TaskDTO;
import com.backend.task_service.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public TaskDTO taskToTaskDto(Task task){
        return new TaskDTO(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getDeadlilne(),
                task.getCreatedAt(),
                task.getAsignedUserId(),
                task.getStatus()
        );
    }
}
