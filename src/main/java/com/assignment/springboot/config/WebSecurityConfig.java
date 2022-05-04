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
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        String admin="ADMIN";
        String users="USER";
        http.authorizeRequests()
                .antMatchers("/students").hasAnyRole(admin,users)
                .antMatchers("/students/showFormForAdd").hasRole(admin)
                .antMatchers("/students/showFormForUpdate").hasRole(admin)
                .antMatchers("/students/save").hasRole(admin)
                .antMatchers("/students/delete").hasRole(admin)
                .antMatchers("/college").hasAnyRole(users,admin)
                .antMatchers("/college/showFormForAdd").hasRole(admin)
                .antMatchers("/college/showFormForUpdate").hasRole(admin)
                .antMatchers("/college/save").hasRole(admin)
                .antMatchers("/college/delete").hasRole(admin)
                .antMatchers("/clubs").hasAnyRole(admin,users)
                .antMatchers("/clubs/showFormForAdd").hasRole(admin)
                .antMatchers("/clubs/showFormForUpdate").hasRole(admin)
                .antMatchers("/clubs/save").hasRole(admin)
                .antMatchers("/clubs/delete").hasRole(admin)
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
