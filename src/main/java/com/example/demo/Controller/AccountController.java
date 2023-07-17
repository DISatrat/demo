package com.example.demo.Controller;

import com.example.demo.domain.Account.Person;
import com.example.demo.DAO.AdvertisementsDAO;
import com.example.demo.DAO.PersonDAO;
import com.example.demo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/myAccount")
public class AccountController {

   private final PersonDAO personDAO;
    private final AdvertisementsDAO advertisementsDAO;
    private final PersonService personService;
    @Autowired
    public AccountController(PersonDAO personDAO, AdvertisementsDAO advertisementsDAO, PersonService personService) {
        this.advertisementsDAO=advertisementsDAO;
        this.personDAO = personDAO;
        this.personService = personService;
    }

    @GetMapping("/adv")
    public String myAdvertisements(Model model){
        String name = personService.getCurrentUsername();
        Person person = personService.loadUserByUsername(name);
        System.out.println("1111111111");
        model.addAttribute("advertisement", advertisementsDAO.findByPersonId(person.getId()));
        model.addAttribute("person",person);

        return "myAccount";
    }


}
