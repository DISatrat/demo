package com.example.demo.DAO;

import com.example.demo.Account.Person;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;

import java.util.Optional;

public interface  PersonDAO extends CrudRepository<Person, Long> {
Iterable<Person> findAll();
Optional<Person> findById(Long id);
Person save(Person person);
}
