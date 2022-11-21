package com.example.demo.DAO;

import com.example.demo.Account.Person;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface  PersonDAO extends CrudRepository<Person, Long> {
//Iterable<Person> findAll();
//Optional<Person> findById(Long id);
//Person save(Person person);
//void deleteById(Long id);
}
