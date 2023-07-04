package com.example.demo.Controller;

import com.example.demo.domain.Account.Person;
import com.example.demo.DAO.AdvertisementsDAO;
import com.example.demo.Service.PersonService;
import com.example.demo.domain.Advertisement.Advertisement;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Advertisement")
public class AdvertisementController {

    private final AdvertisementsDAO advertisementsDAO;
    private final PersonService personService;
    @Autowired
    public AdvertisementController(AdvertisementsDAO advertisementsDAO, PersonService personService){this.advertisementsDAO=advertisementsDAO;
        this.personService = personService;
    }

    @GetMapping("")
    public String findAll(Model model){
        model.addAttribute("advertisement",advertisementsDAO.findAll());
        return "ShowAdvertisement";
    }

    @GetMapping("/Add")
    public String newAdvertisement(Model model){
        model.addAttribute("advertisement",new Advertisement());
        return "add";
    }
    @PostMapping()
    public String saveAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement){
        String name = personService.getCurrentUsername();
        Person person = personService.loadUserByUsername(name);
        advertisement.setPerson(person);
        advertisementsDAO.save(advertisement);
        return "redirect:/";

    }

}

