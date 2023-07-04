package com.example.demo.DAO;

import com.example.demo.domain.Account.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  PersonDAO extends JpaRepository<Person, Long> {
Person findByName(String name);
Person save(Person person);
Long findPersonById(Long id);

//Long findByIdAndAuthorities();
//Iterable<Person> findAll();
//Optional<Person> findById(Long id);
//void deleteById(Long id);
}
