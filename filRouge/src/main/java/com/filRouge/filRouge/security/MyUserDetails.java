package com.filRouge.filRouge.security;

import lombok.RequiredArgsConstructor;
import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

  private final CustomerRepository customerRepository;

  @Override
  public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
    final Customer customer = customerRepository.findByMail(mail);

    if (customer == null) {
      throw new UsernameNotFoundException("User '" + mail + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withUsername(mail)//
        .password(customer.getPassword())//
        .authorities(customer.getAppUserRoles())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
