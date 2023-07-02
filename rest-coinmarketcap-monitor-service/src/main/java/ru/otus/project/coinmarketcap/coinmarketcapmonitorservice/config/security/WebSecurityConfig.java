package ru.otus.project.coinmarketcap.coinmarketcapmonitorservice.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> {
            authz.requestMatchers(HttpMethod.GET, "/test/anonymous", "/test/anonymous/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/test/admin", "/test/admin/**")
                    .hasRole(ADMIN).requestMatchers(HttpMethod.GET, "/test/user")
                    .hasAnyRole(ADMIN, USER)
                    .anyRequest().authenticated();
        });

        http.cors(Customizer.withDefaults());

        http.oauth2ResourceServer(ors -> ors.jwt(jwt -> {
            jwt.jwtAuthenticationConverter(jwtAuthConverter);
        }));

        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}