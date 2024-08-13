package com.customer.service.configs;


import com.customer.service.impl.UserDetailsServiceImpl;
import com.customer.service.jwt.JwtAuthEntryPoint;
import com.customer.service.jwt.JwtAuthTokenFilter;
import com.customer.service.repos.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] SKIP_URLS = new String[]{"/swagger-resources/**", "/swagger-ui.html", "/swagger-ui/**", "/auth/signup", "/auth/login", "/v3/api-docs/**", "/v2/api-docs/**", "/security/**", "/resources/**"};
    private final UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtAuthTokenFilter jwtAuthTokenFilter;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable).exceptionHandling(httpSecurityExceptionHandlingConfigurer -> new JwtAuthEntryPoint()).sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests(requestMatchers -> requestMatchers.requestMatchers(SKIP_URLS)).addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
