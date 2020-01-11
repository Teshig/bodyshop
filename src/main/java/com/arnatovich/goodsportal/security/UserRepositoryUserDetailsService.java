package com.arnatovich.goodsportal.security;

import com.arnatovich.goodsportal.User;
import com.arnatovich.goodsportal.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
  
  private UserRepository userRepo;

  @Autowired
  public UserRepositoryUserDetailsService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  /**
   * Should never return null. 
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' isn't found.");
    }
    return user;
  }
}