package com.example.demo.Service;

import com.example.demo.domain.Account.Person;
import com.example.demo.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class PersonService implements UserDetailsService {

    @Autowired
    private PersonDAO personDAO;

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @Override
    @Cacheable(cacheNames = {"userCache"}, key = "#name")
    public Person loadUserByUsername(String name) throws UsernameNotFoundException {
        Person person = personDAO.findByName(name);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }
        try {
            TimeUnit.SECONDS.sleep(1);//задержка при авторизации
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return person;
    }

    public Person findUserById(Long userId) {
        Optional<Person> userFromDb = personDAO.findById(userId);
        return userFromDb.orElse(new Person());
    }

    public void UpdateIsOnline(String name, boolean isOnline)
    {
        Person person=personDAO.findByName(name);
        person.setOnline(isOnline);
        personDAO.save(person);
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

