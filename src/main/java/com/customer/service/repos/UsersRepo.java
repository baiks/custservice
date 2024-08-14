package com.customer.service.repos;


import com.customer.service.entities.Users;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String name);

    boolean existsByUsername(String name);

    @Cacheable("user")
    Optional<Users> findById(long id);

}