package com.example.demo.Config;

import com.example.demo.DAO.PersonDAO;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.Date;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                //.antMatchers("").authenticated()
                .antMatchers("/persons").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public UserDetailsService user(){
        UserDetails user= User.builder()
                .username("user")
                .password("{bcrypt}$2a$12$wXSm0EeY7Yy//LZk1prRo.KBN5u3LMekSwCw1U0VO830RdiuRIzWW")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);

    }

    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        UserDetails admin=User.builder()
                .username("admin")
                .password("111")
                .roles("ADMIN")
                .build();
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
            jdbcUserDetailsManager.deleteUser(admin.getUsername());
        }

         jdbcUserDetailsManager.createUser(admin);

        return jdbcUserDetailsManager;
    }


}
