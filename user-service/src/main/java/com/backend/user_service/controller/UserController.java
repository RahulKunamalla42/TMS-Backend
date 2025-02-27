package com.backend.user_service.controller;


import com.backend.user_service.dto.UserDTO;
import com.backend.user_service.entity.User;
import com.backend.user_service.exception.MyException;
import com.backend.user_service.payloads.LoginRequest;
import com.backend.user_service.payloads.Response;
import com.backend.user_service.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<Response> home(){
        log.info("hello");
        return ResponseEntity.ok(new Response("Hello"));
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) throws MyException {
        Response res=new Response();
        res.setUser(userService.RegisterUser(user));
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) throws MyException {
        System.out.println(loginRequest);
        return ResponseEntity.ok(userService.LoginUser(loginRequest));
    }

    @GetMapping("/getprofilebyid/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/getprofile")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token){
       Response res=new Response();
        System.out.println(token);
        UserDTO user = userService.getUser(token);
        log.info("{}",user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/addadmin")
    public ResponseEntity<?> addAdmin(){
        return ResponseEntity.ok(userService.addAdmin());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getallprofiles")
    public ResponseEntity<?> getAllUsers() throws MyException {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
