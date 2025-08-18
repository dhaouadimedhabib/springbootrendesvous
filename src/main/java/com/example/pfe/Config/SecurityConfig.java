package com.example.pfe.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // désactive CSRF pour API REST
            .cors()           // active CORS
            .and()
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()        // signup / signin / google-signin
                .antMatchers("/api/RendezVous/**").permitAll()
                .antMatchers("/api/Professionnel/**").permitAll()
                .antMatchers("/api/reclamation/**").permitAll()
                .antMatchers("/api/Disponibilite/**").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/paiement/payer/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()                   // toutes les autres requêtes doivent être authentifiées
            .and()
            .formLogin().disable()      // désactive le formulaire HTML
            .httpBasic().disable();     // désactive HTTP Basic
    }

    // Bean pour encoder les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean pour AuthenticationManager
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
