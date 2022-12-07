package com.restapi.Secutiry;

import com.restapi.Models.AppUser;
import com.restapi.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    public final UserRepository userRepository;
    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user=userRepository.findByEmail(email);
        System.out.println("userService "+email);
        return new MyUserDetails(user);
    }
}
