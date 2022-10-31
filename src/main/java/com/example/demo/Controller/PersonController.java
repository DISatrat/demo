package com.example.demo.Controller;

import com.example.demo.Account.Person;


import com.example.demo.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("persons")
public class PersonController {

    private PersonDAO service;
    @Autowired
    public PersonController(PersonDAO service) {
        this.service = service;
    }


    @GetMapping()
    public String index(Model model){
        model.addAttribute("person",service.findAll());
        return "PersonList";

    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id,Model model){
        model.addAttribute("personShow",service.findAll());
        return "ShowPerson";
    }

    @GetMapping("/registration")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "registration";
    }

    @PostMapping()
    public String savePerson(@ModelAttribute("person") Person person){
        service.save(person);
        return "redirect:/persons";

    }

    @GetMapping("/login")
    public String Log(Model model) {
        model.addAttribute("person",new Person());
        return "LogIn";
    }

    public String UpdatePerson(Model model){
        return "update";
    }

    public String Delete(int id){
        return null;
    }


    }



