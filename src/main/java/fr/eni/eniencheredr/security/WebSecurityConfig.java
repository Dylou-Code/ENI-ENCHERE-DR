package fr.eni.eniencheredr.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.jdbcAuthentication()
                .dataSource( dataSource )
                .passwordEncoder( passwordEncoder )
                .usersByUsernameQuery( " SELECT pseudo, mot_de_passe, 1 FROM Utilisateurs WHERE ? IN  ( pseudo , email ) " )
                .authoritiesByUsernameQuery( " SELECT pseudo, ( CASE WHEN administrateur = 1 THEN 'admin' ELSE  'user' END ) FROM Utilisateurs WHERE ? IN  ( pseudo , email ) " )
                //.usersByUsernameQuery( " SELECT pseudo, mot_de_passe, 1 FROM Utilisateurs WHERE ? IN  ( pseudo , email ) " )
                //.authoritiesByUsernameQuery( " SELECT pseudo, 'admin' FROM Utilisateurs WHERE ? IN  ( pseudo , email ) " )
        ;
    }
    private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                //.logout(Customizer.withDefaults())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/encheres", "/register", "registerUser").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("admin")
                        .requestMatchers("/favicon.ico", "/resources/**", "/error","/search","/js/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling().accessDeniedPage("/403")
                ;

        return http.build();
    }
}
