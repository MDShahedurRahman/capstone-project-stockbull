package com.mdrahman.stockbull.config;

import com.mdrahman.stockbull.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    // We will create UserService class in the upcoming step
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configuring URL-based authorization rules for different roles
        http.authorizeHttpRequests()
                .requestMatchers("/userlist**").hasRole("ADMIN")
                .requestMatchers("/stocks**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/create**").hasRole("ADMIN")
                .requestMatchers("/placeOrder**").hasRole("USER")
                .requestMatchers("/orderConfirmation**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/profile**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/editprofile**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/showAllOrders**").hasRole("ADMIN")
                .requestMatchers("/orderConfirmation**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/trade**").hasRole("USER")
                .requestMatchers("/uploadFile**").hasAnyRole("ADMIN", "USER")
                .requestMatchers(
                        "/signup**",
                        "/dashboard**",
                        "/pricing**",
                        "/scripts/**",
                        "/styles/**",
                        "/images/**",
                        "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .and().logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        // Configuring the DaoAuthenticationProvider to use the UserService and passwordEncoder
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configuring the AuthenticationManagerBuilder to use the authenticationProvider
        auth.authenticationProvider(authenticationProvider());
    }

}
