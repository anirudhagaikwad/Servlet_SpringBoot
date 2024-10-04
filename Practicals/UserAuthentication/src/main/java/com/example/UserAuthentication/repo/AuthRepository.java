package com.example.UserAuthentication.repo;



import com.example.UserAuthentication.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
    // You can add custom query methods if needed
    Optional<Auth> findByUserId(Long userId);
}
