package com.assignment.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        String password="lavanya@123";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);

        System.out.println("Encoded Password is : " + encodedPassword);

        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/students").hasAnyRole("ADMIN","USER")
                .antMatchers("/students/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/students/showFormForUpdate").hasRole("ADMIN")
                .antMatchers("/students/save").hasRole("ADMIN")
                .antMatchers("/students/delete").hasRole("ADMIN")
                .antMatchers("/college").hasAnyRole("USER","ADMIN")
                .antMatchers("/college/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/college/showFormForUpdate").hasRole("ADMIN")
                .antMatchers("/college/save").hasRole("ADMIN")
                .antMatchers("/college/delete").hasRole("ADMIN")
                .antMatchers("/clubs").hasAnyRole("ADMIN","USER")
                .antMatchers("/clubs/showFormForAdd").hasRole("ADMIN")
                .antMatchers("/clubs/showFormForUpdate").hasRole("ADMIN")
                .antMatchers("/clubs/save").hasRole("ADMIN")
                .antMatchers("/clubs/delete").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

}
