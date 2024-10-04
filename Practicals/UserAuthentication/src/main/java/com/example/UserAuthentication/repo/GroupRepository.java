package com.example.UserAuthentication.repo;


import com.example.UserAuthentication.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
