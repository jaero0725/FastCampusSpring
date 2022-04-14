package kr.ac.zeroco.hs_springsecurity.service;

import kr.ac.zeroco.hs_springsecurity.entity.Role;
import kr.ac.zeroco.hs_springsecurity.entity.User;

import java.util.List;

public interface RegistrationService {
    User createUser(User user, List<Role> userRoles);

    boolean checkEmailExists(String email);

    Role findByRolename(String rolename);
}
