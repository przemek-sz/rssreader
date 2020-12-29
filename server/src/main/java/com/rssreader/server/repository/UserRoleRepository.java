package com.rssreader.server.repository;


import com.rssreader.server.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    public UserRole getByRole(String role);
}
