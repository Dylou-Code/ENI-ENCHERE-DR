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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

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
        ;
    }
    private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/encheres", "/register", "/registerUser", "/delete").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("admin")
                        .requestMatchers("/favicon.ico", "/resources/**", "/error", "/search", "/js/**", "/searchFilter").permitAll()
                        .anyRequest().authenticated()
                )
                .rememberMe(withDefaults())
                /*.tokenRepository(tokenRepository())
                .key("AppKey")
                .alwaysRemember(true)
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("javasampleapproach-remember-me")
                .tokenValiditySeconds(24 * 60 * 60)*/
                .exceptionHandling().accessDeniedPage("/403")
                ;

        return http.build();
    }
}
