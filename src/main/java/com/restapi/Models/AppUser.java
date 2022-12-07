package com.restapi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.DTO.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,length = 45)
    private String email;

    @Column(length = 100)
    private String password;
    @Embedded
    private Address address;
    @OneToMany(orphanRemoval = true,fetch = FetchType.EAGER,mappedBy = "user")
    List<Product>userProducts=new ArrayList<>();
}
