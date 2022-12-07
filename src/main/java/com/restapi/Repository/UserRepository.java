package com.restapi.Repository;

import com.restapi.Models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {
    AppUser findByEmail(String email);
}
