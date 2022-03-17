package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.EmlakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmlakUserRepository extends JpaRepository<EmlakUser, Long> {
}
