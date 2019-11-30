package com.arnatovich.goodsportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  DataSource dataSource;
  
  /**
   * LDAP - Lightweight Directory Access Protocol.
   * userSearchFilter() and groupSearchFilter() methods are used to provide filters for the base
   * LDAP queries.
   * 
   * Default strategy for authenticating against LDAP is to perform a bind operation, authenticating 
   * the user directly to the LDAP server, and then the actual password will remains secured. 
   * 
   * If for some reason you'd rather authenticate by doing a password comparison, you can declare so
   * with the passwordCompare().
   * By default, the password given in the login form will be compared with the value of the
   * userPassword attribute in the user's LDAP entry. If the password is kept in a different attribute,
   * you can specify the password attribute's name with passwordAttribute().
   * 
   * By default, Spring Security's LDAP authentication assumes that the LDAP server is listening on 
   * port 33389 on localhost. But if your LDAP server is on another machine, you can use
   * the contextSource().url("ldap://goodcloud.com:389/dc=goodcloud,dc=com").
   * 
   * If you would like to use embedded LDAP server, you can specify the root suffix for the embedded
   * server via .contextSource().root("dc=goodcloud,dc=com").
   * 
   * When the LDAP server starts, it will attempt to load data from any LDIF(LDAP Data Interchange Format)
   * files that it can find in the classpath. If you would rather explicit point which LDIF files should be
   * loaded, you can use .ldif() method.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .ldapAuthentication()
          .userSearchBase("ou=people")
          .userSearchFilter("(uid={0})")
          .groupSearchBase("ou=groups")
          .groupSearchFilter("member={0}")
          .contextSource().root("dc=goodcloud,dc=com")
          .ldif("classpath:users.ldif")
          .and()
          .passwordCompare()
          .passwordEncoder(new BCryptPasswordEncoder())
          .passwordAttribute("passcode");
  }
}
