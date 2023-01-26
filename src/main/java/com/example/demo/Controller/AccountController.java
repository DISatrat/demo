package com.example.demo.Controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;

        import java.security.Principal;

@Controller
@RequestMapping("/{name}")
public class AccountController{

    @GetMapping("")
    public String Account(Principal principal, @PathVariable String name, long id){

        return principal.getName();
    }
}
