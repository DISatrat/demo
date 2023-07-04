package com.example.demo.Config;


import com.example.demo.DAO.PersonDAO;
import com.example.demo.Service.PersonService;
import com.example.demo.domain.Account.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;


@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDAO service;

    public WebSecurityConfig(PersonDAO service) {
        this.service = service;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/persons").hasRole("ADMIN")
//                .antMatchers("/Advertisement/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/Advertisement/Add").authenticated()
                .and()
                .formLogin().loginPage("/persons/login").defaultSuccessUrl("/")

                .and()

                .logout()

                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true) // Очистить сеанс пользователя
                .clearAuthentication(true) // Очистить данные аутентификации пользователя
                .permitAll()
                .and()
                .sessionManagement()
                .maximumSessions(1)
        ;


    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {

            Person person = (Person) authentication.getPrincipal();
            person.setOnline(false);
            service.save(person);
            response.sendRedirect("/");

        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowSemicolon(true); // Разрешить использование символа ";"
        web.httpFirewall(firewall);
    }
    @Autowired
    private PersonService personService;

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new CookieHttpSessionIdResolver();
    }
    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

}

