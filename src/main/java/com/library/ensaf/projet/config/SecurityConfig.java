package com.library.ensaf.projet.config;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import static org.springframework.security.config.Customizer.withDefaults;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

 
    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/user/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
        
                return httpSecurity.build(); }
// @Bean
//     public WebSecurityCustomizer webSecurityCustomizer(){
       
       
       
//         return null;
//     }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
