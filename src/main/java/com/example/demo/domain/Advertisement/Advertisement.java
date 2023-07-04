package com.example.demo.domain.Advertisement;

import com.example.demo.domain.Account.Person;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String Kind;
    private String description;
    private String tag;

    @ManyToOne
    @JoinColumn(name = "perosn_id")
    private Person person;
}
