package com.example.demo.DAO;

import com.example.demo.Account.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface  PersonDAO extends CrudRepository<Person, Long> {

Person findByName(String name);
//Person save(Person person);
//Iterable<Person> findAll();
//Optional<Person> findById(Long id);
//void deleteById(Long id);
}
