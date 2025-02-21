package com.backend.task_service.dto;

import com.backend.task_service.payload.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO{
    private Long TaskId;
    private String title;
    private String description;
    private String deadlilne;
    private LocalDateTime createdAt;
    private Long asignedUserId;
    private Status status;
}

