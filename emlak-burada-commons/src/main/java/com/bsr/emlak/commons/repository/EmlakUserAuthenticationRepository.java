package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.EmlakUserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmlakUserAuthenticationRepository extends JpaRepository<EmlakUserAuthentication, Long> {
    Optional<EmlakUserAuthentication> findByEmlakUser_EmailAndPasswordHash(String email, String passwordHash);
}
