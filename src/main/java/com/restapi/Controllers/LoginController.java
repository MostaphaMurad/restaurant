package com.restapi.Controllers;

import com.restapi.DTO.JwtResponse;
import com.restapi.Models.AppUser;
import com.restapi.Secutiry.JwtUtil;
import com.restapi.Secutiry.MyUserDetails;
import com.restapi.Secutiry.UserService;
import com.restapi.ServicesImp.UserServicesImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/rest/user/v1/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class LoginController {
    private final UserServicesImp userServicesImp;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse>login(@RequestBody AppUser user){
      //  System.out.println("im working ");
        final Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MyUserDetails userDetails=userService.loadUserByUsername(user.getEmail());
        String token=jwtUtil.generateToken(userDetails);
        AppUser resp= userServicesImp.login(user);
        if(resp!=null){
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.internalServerError().body(new JwtResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<AppUser>register(@RequestBody AppUser user){
        return new ResponseEntity<>( userServicesImp.register(user),CREATED);
    }
}
