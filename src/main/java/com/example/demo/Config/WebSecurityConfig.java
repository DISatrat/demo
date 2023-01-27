package com.example.demo.Config;


import com.example.demo.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.persistence.Enumerated;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
//                .antMatchers("").authenticated()
                .antMatchers("/persons").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/persons/login").defaultSuccessUrl("/")

                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }

    @Autowired
     private PersonService personService;


//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Autowired
//    PasswordEncoder passwordEncoder;
//

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

//    @Bean
////    public UserDetailsService user(){
////        UserDetails user= User.builder()
////                .username("user")
////                .password("{bcrypt}$2a$12$kGvZ9mqdwhygM/NqGBSIj.iDgeq1smtKk88tYFQiNl5Pwd0g1B3xu")  //111
////                .roles("ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }


//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
    }
}