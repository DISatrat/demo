package com.example.demo.Controller;

import com.example.demo.DAO.PersonDAO;
import com.example.demo.domain.Account.Person;
import com.example.demo.DAO.AdvertisementsDAO;
import com.example.demo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    private final AdvertisementsDAO advertisementsDAO;
    private final PersonService personService;

    private final PersonDAO service;

    @Autowired
    public MainController(AdvertisementsDAO advertisementsDAO, PasswordEncoder encoder, PersonService personService, PersonDAO service) {
        this.advertisementsDAO = advertisementsDAO;
        this.personService = personService;
        this.service = service;
    }

    @GetMapping("")
    public String findAll(Model model, @AuthenticationPrincipal Person person) {
        if(person!=null){
            person.setOnline(true);
            service.save(person);
        }
            model.addAttribute("advertisement", advertisementsDAO.findAll());
            return "index";
    }


    @GetMapping("/myAccount")
    public String findPerson(Model model) {
        String name = personService.getCurrentUsername();
        Person person = personService.loadUserByUsername(name);
        model.addAttribute("person", person);
        return "myAccount";
    }

    @PostMapping("/")
    public String findByTag(@RequestParam( value = "tag",required = false) String tag,
                            @RequestParam(value = "showAll",required = false)String showALl,
                            Model model) {

        if(showALl!=null || tag.isEmpty()){
            model.addAttribute("advertisement", advertisementsDAO.findAll());
        }
        else {
        model.addAttribute("advertisement", advertisementsDAO.findAdvertisementsByTag(tag));
        }

        return "index";
    }

//    @PostMapping("/showAll")
//    public String showAllAdvr(Model model) {
//        model.addAttribute("advertisement", advertisementsDAO.findAll());
//        return "index";
//    }
}
