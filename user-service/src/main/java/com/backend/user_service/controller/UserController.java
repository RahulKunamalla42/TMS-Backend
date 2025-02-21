package com.backend.user_service.controller;


import com.backend.user_service.entity.User;
import com.backend.user_service.exception.MyException;
import com.backend.user_service.payloads.LoginRequest;
import com.backend.user_service.payloads.Response;
import com.backend.user_service.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ResponseEntity<Response> home(){
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

    @GetMapping("/getuser")
    public ResponseEntity<Response> getUser(@RequestHeader String token){
       Response res=new Response();
        System.out.println(token);
       res.setUser(userService.getUser(token));
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getallusers")
    public ResponseEntity<Response> getAllUsers(){
        Response res=new Response();
        res.setUsers(userService.getAllUsers());
        return ResponseEntity.ok(res);
    }
}
