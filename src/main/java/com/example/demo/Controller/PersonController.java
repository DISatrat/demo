package com.example.demo.Controller;

import com.example.demo.Account.Person;


import com.example.demo.Account.Role;
import com.example.demo.DAO.PersonDAO;
import com.example.demo.Service.PersonService;
import com.sun.xml.bind.v2.model.core.ID;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonDAO service;


    @Autowired
    public PersonController(PersonDAO service) {
        this.service = service;
    }


    @GetMapping("")
    public String index(Model model){
        model.addAttribute("person", service.findAll());
        return "PersonList";

    }
//    @GetMapping("/{id}")
//    public String showPerson(@PathVariable("id") Long id,Model model){
//        model.addAttribute("personShow",service.findById(id));
//        return "ShowPerson";
//    }

    @GetMapping("/registration")
    public String newPerson( Model model){
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String savePerson(@ModelAttribute("person") Person person, Model model) {

//        if(!PersonService.saveUser(person)){
//            model.addAttribute("usernameError","User exists!");
//            return "registration";
//        }
//
        person.setRoles(Collections.singleton(Role.ROLE_USER));
        service.save(person);
        return "redirect:/persons";

    }

    @GetMapping("/login")
    public String LoginPage(Model model) {
        //model.addAttribute("personLog");
        return "LogIn";
    }
//    @GetMapping("/login")
//    public String Login(@ModelAttribute("personLog") Person person) {
////        model.addAttribute("person",new Person());
//        PersonService.loadUserByUsername();
//        return "LogIn";
//    }

//    public String UpdatePerson(Model model){
//        return "update";
//    }

    @PostMapping ("/{id}/delete")
    public String DeletePerson(@PathVariable(value = "id") long id){
        Person person=service.findById(id).orElseThrow();
        service.delete(person);
        return "redirect:/persons";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/";
    }

    }



