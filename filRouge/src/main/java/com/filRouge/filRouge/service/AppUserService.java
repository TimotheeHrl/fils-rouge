package com.filRouge.filRouge.service;

import com.filRouge.filRouge.model.AppUser;
import com.filRouge.filRouge.model.Role;

import java.util.List;

public interface AppUserService {
    AppUser saveUser(AppUser username);

    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    List<Role> getRoles();

}
