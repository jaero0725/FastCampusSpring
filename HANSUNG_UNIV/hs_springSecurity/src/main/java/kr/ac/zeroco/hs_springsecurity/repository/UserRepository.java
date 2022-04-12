package kr.ac.zeroco.hs_springsecurity.repository;

import kr.ac.zeroco.hs_springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}
