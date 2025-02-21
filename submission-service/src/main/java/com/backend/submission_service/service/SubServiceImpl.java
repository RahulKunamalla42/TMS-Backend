package com.backend.submission_service.service;

import com.backend.submission_service.dto.SubDto;
import com.backend.submission_service.entity.Submission;
import com.backend.submission_service.exception.MyException;
import com.backend.submission_service.repo.SubRepo;
import com.backend.submission_service.util.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubServiceImpl implements SubService {

    private final SubRepo subRepo;
    private final TaskService taskService;
    private final ModelMapper modelMapper;

    public SubServiceImpl(SubRepo subRepo, TaskService taskService, ModelMapper modelMapper) {
        this.subRepo = subRepo;
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    public SubDto submitTask(Long userid, Long taskid, String githublink) throws MyException {
        ResponseEntity<?> task = taskService.getTaskById(taskid);
        if(task!=null){
            Submission sub=new Submission();
            sub.setGithublink(githublink);
            sub.setTaskId(taskid);
            sub.setUserId(userid);
            sub.setSubmissiontime(LocalDateTime.now());
            Submission save = subRepo.save(sub);
            SubDto subDto = modelMapper.SubmissionToSubDto(save);
            return subDto;
        }else{
            throw new MyException("TASK NOT EXITS"+ userid);
        }
    }

    @Override
    public SubDto getSubmissionbyid(Long subid) throws MyException {
        Submission submission = subRepo.findById(subid)
                .orElseThrow(() -> new MyException("SUBMISSION NOT FOUND" + subid));
        return modelMapper.SubmissionToSubDto(submission);
    }

    @Override
    public List<SubDto> getAllSubmissions() {
        List<Submission> all = subRepo.findAll();
        List<SubDto> list = all.stream().map(sub -> modelMapper.SubmissionToSubDto(sub)).toList();
        return list;
    }

    @Override
    public List<SubDto> getSubmissionsByTaskId(Long taskid) {
        List<Submission> list = subRepo.findByTaskId(taskid);
        List<SubDto> all = list.stream().map(sub -> modelMapper.SubmissionToSubDto(sub)).toList();
        return all;
    }

}
