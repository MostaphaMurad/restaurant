package com.restapi.Services;

import com.restapi.Models.AppUser;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserServices {
    AppUser login(AppUser user);

    AppUser register(AppUser user);
}
