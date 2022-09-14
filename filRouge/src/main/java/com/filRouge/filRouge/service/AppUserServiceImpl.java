package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.AppUser;
import com.filRouge.filRouge.model.Role;
import com.filRouge.filRouge.repository.AppUserRepository;
import com.filRouge.filRouge.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @Transactional @Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    AppUserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;


    public AppUserServiceImpl(AppUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser username) {
        log.info("Saving user {} into database", username.getUsername());
        username.setPassword(passwordEncoder.encode(username.getPassword()));
        return userRepository.save(username);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role {} into database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("add role {} to user {}", rolename, username);
        AppUser user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("fetching  user : {} ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("fetching all users");
        return userRepository.findAll();
    }
    @Override
    public List<Role> getRoles(){
        log.info("fetching all roles");
     return roleRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if(user == null){
            log.error("user not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("user {} found in the database", username);
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // We are link our customs roles to the spring roles named GrantedAuthority and give it in the userDetailsService return
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        // We need to return an User from the spring who are an instance of UserDetails
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

}
