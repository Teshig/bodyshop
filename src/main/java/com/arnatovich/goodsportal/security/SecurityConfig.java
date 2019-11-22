package com.arnatovich.goodsportal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configurable
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  DataSource dataSource;
  
  /**
   * By default would be enough just auth.jdbcAuthentication().dataSource(dataSource);
   * But in that case Spring Security will be expecting certain tables in your DB, and 
   * will perform next queries:
   * 
   * public static final String DEF_USERS_BY_USERNAME_QUERY =
   *  "select username,password,enabled " +
   *  "from users " +
   *  "where username = ?";
   * 
   * public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
   *  "select username,authority " +
   *  "from authorities " +
   *  "where username = ?";
   * 
   * public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
   *  "select g.id, g.group_name, ga.authority " +
   *  "from groups g, group_members gm, group_authorities ga " +
   *  "where gm.username = ? " +
   *  "and g.id = ga.group_id " +
   *  "and g.id = gm.group_id";
   *  
   *  ####################################################################################
   *  
   *  You could customize your requests, as shown below, but you should adhere to the basic 
   *  contract of the queries:
   *  - all queries take the username as their only parameter;
   *  - the authentication query selects the username, password and enabled status;
   *  - the authorities query selects zero or more rows containing the username and a granted authority;
   *  - the group authorities query selects zero or more rows, each with a group ID, a group name and an authority;
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "SELECT username, password, enabled FROM Users WHERE username=?")
        .authoritiesByUsernameQuery(
            "SELECT username, authority FROM UserAuthorities WHERE username=?")
        .passwordEncoder(new BCryptPasswordEncoder(4));
  }
}
