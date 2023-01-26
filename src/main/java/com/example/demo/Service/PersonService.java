package com.example.demo.Service;

import com.example.demo.Account.Person;
import com.example.demo.Account.Role;
import com.example.demo.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonDAO personDAO;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Override
    public Person loadUserByUsername(String name) throws UsernameNotFoundException {
        Person person = personDAO.findByName(name);

        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return person;
    }

    public Person findUserById(Long userId) {
        Optional<Person> userFromDb = personDAO.findById(userId);
        return userFromDb.orElse(new Person());
    }



//    public boolean saveUser(Person user) {
//        Person userFromDB = personDAO.findByName(user.getUsername());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setRoles(Collections.singleton(Role.USER));
//        user.getPassword();
//        user.getUsername();
//        personDAO.save(user);
//        return true;
//    }


//    public List<Person> usergtList(Long idMin) {
//        return personDAO.createQuery("SELECT u FROM User u WHERE u.id > :paramId", Person.class)
//                .setParameter("paramId", idMin).getResultList();
//    }
}

