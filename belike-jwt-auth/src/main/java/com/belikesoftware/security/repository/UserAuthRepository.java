package com.belikesoftware.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.belikesoftware.security.model.AuthUser;

/**
 * Created by stephan on 20.03.16.
 */
@RepositoryRestResource(exported = false)
public interface UserAuthRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);
}