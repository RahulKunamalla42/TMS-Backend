package com.backend.user_service.service;

import com.backend.user_service.dto.UserDTO;
import com.backend.user_service.entity.User;
import com.backend.user_service.exception.MyException;
import com.backend.user_service.payloads.LoginRequest;
import com.backend.user_service.payloads.Response;
import com.backend.user_service.payloads.Role;
import com.backend.user_service.repo.UserRepo;
import com.backend.user_service.security.JwtService;
import com.backend.user_service.util.ModelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService{

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public List<UserDTO> getAllUsers() throws MyException{
        List<User> all = userRepo.findAll();
        List<UserDTO> collect = all.stream()
                .map(user -> modelMapper.userToUserDto(user)).collect(Collectors.toList());
        return collect;
    }
    @Override
    public UserDTO getUser(String token) {
        String username = jwtService.extractusername(token.replace("Bearer ", ""));
        log.info("user name is: ",username);
        User user = userRepo.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found using token"));
        return modelMapper.userToUserDto(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(()->new RuntimeException("not found"));
    }

    @Override
    public Response LoginUser(LoginRequest loginRequest) throws MyException {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.userName(),
                        loginRequest.password()
                ));
        if(authenticate.isAuthenticated()){
            User principal = (User) authenticate.getPrincipal();
            log.info("principal:= {}",principal);
            String token = jwtService.GenarateToken(principal);
            log.info("token:= {}",token);
            Response res=new Response();
            res.setToken(token);
            res.setRole(principal.getRole());
            return res;
        }else{
            throw new MyException("unauthenticated register first");
        }
    }
    @Override
    public UserDTO RegisterUser(User user) throws MyException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        User save = userRepo.save(user);
        if(save!=null){
            return modelMapper.userToUserDto(userRepo.save(user));
        }else{
            throw new MyException("registration failed ");
        }
    }

    @Override
    public boolean isExistUser(String username) {
        return userRepo.existsByUserName(username);
    }

    @Override
    public UserDTO addAdmin() {
        if(isExistUser("sonu")){
            return null;
        }else {
            User admin=new User();
            admin.setUserName("sonu");
            admin.setPassword(passwordEncoder.encode("sonu"));
            admin.setRole(Role.ADMIN);
            userRepo.save(admin);
            return modelMapper.userToUserDto(admin);
        }
    }

}
