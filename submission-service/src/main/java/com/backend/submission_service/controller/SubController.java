package com.backend.submission_service.controller;


import com.backend.submission_service.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submission")
public class SubController{


    private final SubService submissonService;

    public SubController(SubService submissonService) {
        this.submissonService = submissonService;
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitTask(@RequestParam Long userid,
                                        @RequestParam Long taskid,
                                        @RequestParam String githublink) throws Exception {
        return new ResponseEntity<>( submissonService.submitTask(userid,taskid,githublink), HttpStatus.OK);
    }

    @GetMapping("/getSub")
    public ResponseEntity<?> getSubmissionbyid(@RequestParam Long subid)throws Exception{
        return new ResponseEntity<>(submissonService.getSubmissionbyid(subid),HttpStatus.OK);
    }

    @GetMapping("/getallsubs")
    public ResponseEntity<?> getAllSubmissions()throws Exception{
        return new ResponseEntity<>(submissonService.getAllSubmissions(),HttpStatus.OK);
    }

    @GetMapping("/getsubsbytaskid")
    public ResponseEntity<?> getSubmissionsByTaskId(@RequestParam Long taskid)throws Exception{
        return new ResponseEntity<>(submissonService.getSubmissionsByTaskId(taskid),HttpStatus.OK);
    }
}
