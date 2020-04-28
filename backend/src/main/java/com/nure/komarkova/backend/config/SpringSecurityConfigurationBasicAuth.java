package com.nure.komarkova.backend.config;

import com.nure.komarkova.backend.config.jwt.JwtConfigurer;
import com.nure.komarkova.backend.config.jwt.JwtTokenFilter;
import com.nure.komarkova.backend.config.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private static final String ADMIN_ENDPOINT = "";
    private static final String LOGIN_ENDPOINT = "";

    public SpringSecurityConfigurationBasicAuth(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/logIn", "/createUser", "/getUser/{email}")
                .permitAll()
                .antMatchers("/findAllUsers")
                .hasRole("ADMIN")
                .antMatchers("/createEmployee",
                        "/findAllWorkflows", "/findWorkFlow/{id}", "/findTotalProductivity",
                        "/findTotalProductivity/{date}", "/findWorkflows/{date}", "/findGenderProductivity")
                .hasRole("MANAGER")
                .antMatchers("/viewAllEmployees", "/viewAllEmployees/{id}", "/allPositions","/createPosition",
                        "/findWorkflowsByDates")
                .hasAnyRole("ADMIN", "MANAGER")
                .anyRequest().authenticated();
        // .and().apply(new JwtConfigurer(jwtTokenProvider));
    }

}
