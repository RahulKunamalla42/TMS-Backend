package com.backend.submission_service.dto;

import java.time.LocalDateTime;

public record SubDto(
         Long subId,
         Long userId,
         Long taskId,
         String githublink,
         String status,
         LocalDateTime submissiontime
) {
}
