package com.example.demo.Account;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Person  {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;


//    public enum Role{
//        ADMIN,USER
//    }


}
