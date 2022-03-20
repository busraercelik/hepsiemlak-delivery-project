package com.bsr.emlak.commons.repository;

import com.bsr.emlak.commons.entity.UsageLeft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageLeftRepository extends JpaRepository<UsageLeft, Long> {
}
