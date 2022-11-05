package com.example.demo.Controller;

        import com.example.demo.DAO.AdvertisementsDAO;
        import com.example.demo.domain.Advertisement;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.Banner;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Advertisement")
public class AdvertisementController {

    private AdvertisementsDAO advertisementsDAO;

    @Autowired
    public AdvertisementController(AdvertisementsDAO advertisementsDAO){this.advertisementsDAO=advertisementsDAO;}

    @GetMapping("/")
    public String findAll(Model model){
        model.addAttribute("advertisement",advertisementsDAO.findAll());
        return "index";
    }

    @GetMapping("/Add")
    public String newAdvertisement(Model model){
        model.addAttribute("advertisement",new Advertisement());
        return "add";
    }
    @PostMapping()
    public String saveAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement){
        advertisementsDAO.save(advertisement);
        return "redirect:/";

    }

}

