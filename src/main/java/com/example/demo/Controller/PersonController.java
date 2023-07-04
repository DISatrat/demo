package com.example.demo.Controller;

import com.example.demo.Service.PersonService;
import com.example.demo.domain.Account.Person;


import com.example.demo.domain.Account.Role;
import com.example.demo.DAO.AdvertisementsDAO;
import com.example.demo.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonDAO service;
    private final AdvertisementsDAO advertisementsDAO;
    private final PasswordEncoder encoder;

    private final PersonService personService;
    @Autowired
    public PersonController(PersonDAO service, AdvertisementsDAO advertisementsDAO, PasswordEncoder encoder, PersonService personService) {
        this.service = service;
        this.advertisementsDAO = advertisementsDAO;
        this.encoder = encoder;
        this.personService = personService;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("person", service.findAll());
        return "PersonList";

    }

    @GetMapping("/registration")
    public String newPerson( Model model){
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String savePerson(@ModelAttribute("person") Person person) {
        person.setRoles(Collections.singleton(Role.ROLE_USER));
        service.save(person);
        return "redirect:/persons";

    }

    @GetMapping("/login")
    public String LoginPage(Model model) {
        model.addAttribute("personLog",new Person());
        return "LogIn";
    }


    @PostMapping ("/{id}/delete")
    public String DeletePerson(@PathVariable(value = "id") long id){
        Person person=service.findById(id).orElseThrow();
        advertisementsDAO.deleteAllByPersonId(id);
        service.delete(person);
        return "redirect:/persons";
    }
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request,@AuthenticationPrincipal Person person) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            request.getSession().invalidate();
//            System.out.println("ggdgdgdgasfsfs");
//        }
//        if(person!=null){
//            person.setOnline(false);
//            service.save(person);
//        }
//        return "redirect:/";
//    }

}



