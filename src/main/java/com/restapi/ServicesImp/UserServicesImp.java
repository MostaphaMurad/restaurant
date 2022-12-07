package com.restapi.ServicesImp;

import com.restapi.Models.AppUser;
import com.restapi.Repository.UserRepository;
import com.restapi.RestaurantException.CustomException;
import com.restapi.Services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServicesImp implements UserServices {
    private final UserRepository userRepository;
    @Override
    public AppUser login(AppUser user) {
        try{
            AppUser userLogin=userRepository.findByEmail(user.getEmail());
            if(userLogin.getPassword().equals(user.getPassword())){
                return userLogin;
            }else{
                return new AppUser();
            }
        }catch (CustomException e){
            throw new CustomException("Login Failed");
        }

    }

    @Override
    public AppUser register(AppUser user) {
        try{
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
            String encPassword=encoder.encode(user.getPassword());

            user.setPassword(encPassword);
            System.out.println("user "+user.toString());
            return userRepository.saveAndFlush(user);
        }catch (CustomException e){
            throw new CustomException("Registration Failed");
        }
    }
}
