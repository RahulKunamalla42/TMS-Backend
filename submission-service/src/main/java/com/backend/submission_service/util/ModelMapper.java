package com.backend.submission_service.util;

import com.backend.submission_service.dto.SubDto;
import com.backend.submission_service.entity.Submission;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public SubDto SubmissionToSubDto(Submission sub){
     return new SubDto(sub.getSubId(),
             sub.getUserId(),
             sub.getTaskId(),
             sub.getGithublink(),
             sub.getStatus(),
             sub.getSubmissiontime());
    }
}
