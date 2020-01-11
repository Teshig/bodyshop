package com.arnatovich.goodsportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Qualifier("userRepositoryUserDetailsService")
  @Autowired
  private UserDetailsService userDetailsService;
  
  /**
   * As it is annotated with @Bean any calls to encoder() will be intercepted to return the bean
   * instance from the application context, not the original object.
   */
  @Bean
  public PasswordEncoder encoder() {
    return new SCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder());
  }

  /**
   * The authorizeRequests() method returns an object ExpressionInterceptUrlRegistry on which you
   * could specify URLs and patterns.
   * The order of these rules is important! Security rules declared first take precedence over those
   * declared lower down.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
        authorizeRequests()
        .antMatchers("/design", "/orders").hasRole("USER")
        .antMatchers("/", "/**").permitAll()
        .and().formLogin().loginPage("/login").defaultSuccessUrl("/design", true)
        .and().logout().logoutSuccessUrl("/");
  }
}
