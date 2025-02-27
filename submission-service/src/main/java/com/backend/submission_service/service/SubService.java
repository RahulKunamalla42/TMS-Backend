package com.backend.submission_service.service;


import com.backend.submission_service.dto.SubDto;
import com.backend.submission_service.exception.MyException;

import java.util.List;

public interface SubService {

    SubDto submitTask(Long userid, Long taskid, String githublink,String jwt)throws MyException;

    SubDto getSubmissionbyid(Long subid) throws MyException;

    List<SubDto> getAllSubmissions();

    List<SubDto> getSubmissionsByTaskId(Long taskid);

    List<SubDto> getSubmissionsByUserId(Long userid);

}
