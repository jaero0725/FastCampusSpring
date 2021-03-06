package kr.ac.zeroco.hs_springsecurity.repository;

import kr.ac.zeroco.hs_springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
   Optional<Role> findByRolename(String rolename);
}
