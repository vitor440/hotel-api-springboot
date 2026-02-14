package com.hotelapi.hotelapi.configuration;

import com.hotelapi.hotelapi.security.CustomUserDetailsService;
import com.hotelapi.hotelapi.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/usuarios/**").permitAll()
                                .anyRequest().authenticated())

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    //@Bean
    public UserDetailsService userDetailsService(UsuarioService service) {
//        UserDetails user1 = User
//                .builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User
//                .builder()
//                .username("usuario")
//                .password(passwordEncoder().encode("usuario321"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);

        return new CustomUserDetailsService(service);
    }


}
